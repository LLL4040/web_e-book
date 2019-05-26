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
              <div v-for="o in items.slice((itemCurrentPage - 1) * itemPageSize, itemCurrentPage * itemPageSize)" :key="o">
                <el-card class="box-card">
                  <div slot="header" class="clearfix">
                    <span>{{o.time}}</span>
                    <el-button style="float: right; padding: 3px 0; color: red" type="text">删除此项</el-button>
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
    name: 'orders',
    data: function () {
      return {
        user: '',
        activeIndex: 'orders',
        items: [],
        itemCurrentPage: 1,
        itemPageSize: 10,
        itemNumber: 0,
        spanArr: [],
        startdate: '',
        enddate: ''
      };
    },
    mounted () {
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
          /*
          for (let i = 0; i < this.items.length; i++) {
            this.$set(this.items[i],'total',0);
            if (i === 0) {
              this.spanArr.push(1);
              this.pos = 0;
              this.items[i].total = this.items[i].price * this.items[i].number;
            } else {
              // 判断当前元素与上一个元素是否相同
              if (this.items[i].id === this.items[i - 1].id) {
                this.spanArr[this.pos] += 1;
                this.spanArr.push(0);
                this.items[this.pos].total += this.items[i].price * this.items[i].number;
              } else {
                this.spanArr.push(1);
                this.pos = i;
                this.items[this.pos].total = this.items[i].price * this.items[i].number;
              }
            }
          }*/
        })
    },
    methods: {
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
