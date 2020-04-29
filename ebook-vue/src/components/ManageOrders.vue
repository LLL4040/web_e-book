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
        <router-link :to="{name:'ManageBooks',params:{user: this.user}}" >书籍管理</router-link>
        <router-link :to="{name:'ManageUsers',params:{user: this.user}}" >用户管理</router-link>
        <a class="now" href="#">订单管理</a>
        <router-link :to="{name:'Statistics',params:{user: this.user}}" >统计</router-link>
      </div>
    </div>
    <div id="content">
      <div id="content_bottom">
        <el-card class="box-card" style="text-align: center;">
          <div v-if="items.length === 0">
            暂无订单记录
          </div>
          <el-row v-if="items.length !== 0" type="flex" justify="center">
            <el-date-picker v-model="dateRange" type="daterange" format="yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒"
                            value-format="yyyy-MM-dd HH:mm:ss" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
          </el-row>
        </el-card>
        <div v-for="o in items.slice((itemCurrentPage - 1) * itemPageSize, itemCurrentPage * itemPageSize)
        && items.filter(data => !dateRange || (data.time >= dateRange[0] && data.time <= dateRange[1]))" :key="">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span>订单号：{{o.order_id}}&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
              <span>用户名：{{o.user.username}}&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span>
              <span>日期：{{o.time}}</span>
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
      </div>
    </div>
  </div>
</template>

<script>
  import axios from "axios";

  export default {
    name: 'order_manege',
    data: function () {
      return {
        user: '',
        items: [],
        itemCurrentPage: 1,
        itemPageSize: 10,
        itemNumber: 0,
        countArr: [],
        dateRange: ''
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
          .post('http://localhost:8888/api/order/all', form)
          .then(response => {
            this.items = response.data;
            this.itemNumber = this.items.length;
            let self = this;
            for(let i = 0; i < this.itemNumber; i++) {
              for(let j = 0; j < this.items[i].orderItems.length; j++) {
                axios
                  .post('http://localhost:8888/api/book/isbn/mongo', {"isbn": self.items[i].orderItems[j].book.isbn})
                  .then(response => {
                    self.items[i].orderItems[j].book.cover = "data:image/png;base64," + response.data.cover.toString();
                  })
              }
            }
          })
      },
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
