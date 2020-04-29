<template>
  <div class="statistics">
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
        <router-link :to="{name:'ManageOrders',params:{user: this.user}}" >订单管理</router-link>
        <a class="now" href="#">统计</a>
      </div>
    </div>
    <div id="content">
      <div id="content_bottom">
        <el-card class="box-card" style="text-align: center;">
          <el-row v-if="items.length !== 0" type="flex" justify="center">
            <el-date-picker v-model="dateRange" type="daterange" format="yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒"
                            value-format="yyyy-MM-dd HH:mm:ss" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
            <el-button type="primary" style="margin-left: 5px;" @click="handleCount(dateRange)">提交</el-button>
          </el-row>
          <p>书籍统计</p>
          <el-table :data="countArr" border style="width: 100%; margin-top: 10px">
            <el-table-column align="center" prop="isbn" label="ISBN" width="150px"></el-table-column>
            <el-table-column align="center" prop="bookname" label="书名"></el-table-column>
            <el-table-column align="center" prop="price" label="单价"></el-table-column>
            <el-table-column align="center" prop="num" label="销售量"></el-table-column>
            <el-table-column align="center" prop="sum" label="总成交金额"></el-table-column>
          </el-table>
          <p>用户统计</p>
          <el-table :data="userArr" border style="width: 100%; margin-top: 10px">
            <el-table-column align="center" prop="username" label="用户名"></el-table-column>
            <el-table-column align="center" prop="num" label="总数"></el-table-column>
            <el-table-column align="center" prop="sum" label="总成交金额"></el-table-column>
          </el-table>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from "axios";

  export default {
    name: 'Statistics',
    data: function () {
      return {
        user: '',
        items: [],
        itemNumber: 0,
        countArr: [],
        dateRange: '',
        startDate: '',
        endDate: '',
        userArr:[],
        userStatisticsData: [{
          id: 1,
          username: '1',
          totalNumber: 3,
          totalAmount: 30
        }, {
          id: 2,
          username: '2',
          totalNumber: 5,
          totalAmount: 80
        }],
        userStatisticsNumber: 2,
        userStatisticsCurrentPage: 1,
        userStatisticsPageSize: 10
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
            this.handleUsers();
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
      },
      handleUsers () {
        let flag = 0;
        console.log(this.items);
        for(let a = 0; a < this.items.length; a++) {
          for(let b = 0; b < this.items[a].orderItems.length; b++) {
            for(let i = 0; i < this.userArr.length; i++) {
              if(this.userArr[i].username === this.items[a].user.username) {
                this.userArr[i].num += this.items[a].orderItems[b].number;
                this.userArr[i].sum += this.items[a].orderItems[b].number * this.items[a].orderItems[b].book.price;
                flag = 1;
                break;
              }
            }
            if(flag === 0) {
              let ua = {
                "username": '',
                "num": 0,
                "sum": 0
              };
              ua.username = this.items[a].user.username;
              ua.num = this.items[a].orderItems[b].number;
              ua.sum = this.items[a].orderItems[b].book.price * this.items[a].orderItems[b].number;
              this.userArr.push(ua);
            }
            flag = 0;
          }
        }
        console.log(this.userArr);
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
