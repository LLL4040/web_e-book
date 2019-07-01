<template>
    <div class="orders">
        <div id="page" align="center">
            <div id="top">
                <div id="top_left">
                    <img alt="" src="../assets/logo.png" width="50%">
                </div>
            </div>
            <div class="clear"></div>
            <div id="menu">
              <router-link :to="{name:'Home',params:{user: ''}}" >退出登录</router-link>
              <router-link :to="{name:'UserBooks',params:{user: this.user}}" >书籍</router-link>
              <router-link :to="{name:'UserCart',params:{user: this.user}}" >购物车</router-link>
              <a class="now" href="#">我的订单</a>
            </div>
        </div>
        <div id="content">
            <div id="content_bottom">
              <el-card class="box-card" style="text-align: center;">
              <div v-if="items.length === 0">
                暂无订单记录
              </div>
              </el-card>
              <div v-for="o in items.slice((itemCurrentPage - 1) * itemPageSize, itemCurrentPage * itemPageSize)" :key="">
                <el-card class="box-card">
                  <div slot="header" class="clearfix">
                    <span>{{o.time}}</span>
                    <el-button style="float: right; padding: 3px 0; color: red" type="text" @click="handleDelete(o.order_id)">删除此项</el-button>
                  </div>
                  <el-row v-for="(item, index) in o.orderItems" :key="index" style="padding: 10px 0; border-bottom: 1px solid #eff2f6">
                    <el-col :span="4">
                      <img :src="item.book.cover" style="width: 120px;">
                    </el-col>
                    <el-col :span="4" style="line-height: 103.8px; width: 240px;">
                      {{ item.book.bookname }}
                    </el-col>
                    <el-col :span="4" style="height: 103.8px; display: flex; justify-content: center; flex-direction: column;">
                      单价<br><br>{{ item.book.price }}
                    </el-col>
                    <el-col :span="4" style="height: 103.8px; display: flex; justify-content: center; flex-direction: column;">
                      数量<br><br>{{ item.number }}
                    </el-col>
                    <el-col :span="4" style="height: 103.8px; display: flex; justify-content: center; flex-direction: column;">
                      金额<br><br>{{ item.book.price * item.number }}
                    </el-col>
                  </el-row>
                </el-card>
              </div>
              <el-pagination style="text-align: center; margin-top: 5px;" :current-page="itemCurrentPage"
                             :page-sizes="[10, 20, 30, 40, 50]" :page-size="itemPageSize"
                             layout="total, sizes, prev, pager, next, jumper" :total="itemNumber"></el-pagination>
              <br>
              <el-card class="box-card">
                <el-row type="flex" justify="center">
                  <el-date-picker v-model="dateRange" type="daterange" format="yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒"
                                  value-format="yyyy-MM-dd HH:mm:ss" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
                  <el-button type="primary" style="margin-left: 5px;" @click="handleCount(dateRange)">提交</el-button>
                </el-row>
                <el-table :data="countArr" border style="width: 100%; margin-top: 10px">
                  <el-table-column align="center" prop="isbn" label="ISBN" width="150px"></el-table-column>
                  <el-table-column align="center" prop="bookname" label="书名"></el-table-column>
                  <el-table-column align="center" prop="price" label="单价"></el-table-column>
                  <el-table-column align="center" prop="num" label="数量"></el-table-column>
                  <el-table-column align="center" prop="sum" label="总价"></el-table-column>
                </el-table>
              </el-card>
            </div>
        </div>
    </div>
</template>

<script>
  import axios from "axios";

  export default {
    name: 'orders',
    data: function () {
      return {
        user: '',
        items: [],
        itemCurrentPage: 1,
        itemPageSize: 10,
        itemNumber: 0,
        countArr: [],
        dateRange: '',
        startDate: '',
        endDate: ''
      };
    },
    mounted () {
      this.loadOrders();
    },
    methods: {
      loadOrders () {
        this.user = this.$route.params.user;
        if (this.user === '') {
          alert("请登录后查看订单！");
          this.$router.push({name: "Home"});
        }
        let form = {"username": this.user};
        axios
          .post('http://localhost:8088/api/order/all', form)
          .then(response => {
            this.items = response.data;
            this.itemNumber = this.items.length;
            let self = this;
            for(let i = 0; i < this.itemNumber; i++) {
              for(let j = 0; j < this.items[i].orderItems.length; j++) {
                axios
                  .post('http://localhost:8088/api/book/isbn/mongo', {"isbn": self.items[i].orderItems[j].book.isbn})
                  .then(response => {
                    self.items[i].orderItems[j].book.cover = "data:image/png;base64," + response.data.cover.toString();
                  })
              }
            }
          })
      },
      handleDelete (id) {
        let form = {"id": id};
        axios
          .post('http://localhost:8088/api/order/delete', form)
          .then(response => {
            if(response.data) {
              alert("删除此订单成功！");
              this.loadOrders();
            }
            else {
              alert("删除失败，该订单不在数据库中！");
            }
          })
      },
      handleCount (dateRange) {
        this.startDate = dateRange[0];
        this.endDate = dateRange[1];
        this.countArr = [];
        let flag = 0;
        for(let a = 0; a < this.items.length; a++) {
          if(this.items[a].time >= this.startDate && this.items[a].time <= this.endDate) {
            for(let b = 0; b < this.items[a].orderItems.length; b++) {
              for(let i = 0; i < this.countArr.length; i++) {
                if(this.countArr[i].isbn === this.items[a].orderItems[b].book.isbn) {
                  this.countArr[i].num += this.items[a].orderItems[b].number;
                  this.countArr[i].sum += this.items[a].orderItems[b].number * this.countArr[i].price;
                  flag = 1;
                  break;
                }
              }
              if(flag === 0) {
                let ca = {
                  "isbn": '',
                  "bookname": '',
                  "price": 0,
                  "num": 0,
                  "sum": 0
                };
                ca.isbn = this.items[a].orderItems[b].book.isbn;
                ca.bookname = this.items[a].orderItems[b].book.bookname;
                ca.price = this.items[a].orderItems[b].book.price;
                ca.num = this.items[a].orderItems[b].number;
                ca.sum = ca.price * ca.num;
                this.countArr.push(ca);
              }
              flag = 0;
            }
          }
        }
      }
    }
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.cover {
  width: 150px;
}
.el-menu-item {
  font-size: 18px;
}
</style>
