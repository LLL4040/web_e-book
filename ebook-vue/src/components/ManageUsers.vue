<template>
  <div class="manage">
    <div id="page" align="center">
            <div id="top">
                <div id="top_left">
                    <img alt="" src="../assets/logo.png" width="50%">
                </div>
            </div>
            <div class="clear"></div>
            <div id="menu">
              <router-link :to="{name:'Home',params:{user: ''}}" >{{ index }}</router-link>
              <router-link :to="{name:'ManageBooks',params:{user: this.user}}" >书籍管理</router-link>
              <a class="now" href="#">用户管理</a>
              <router-link :to="{name:'ManageOrders',params:{user: this.user}}" >订单管理</router-link>
              <router-link :to="{name:'Statistics',params:{user: this.user}}" >统计</router-link>
            </div>
        </div>
        <div id="content">
          <div id="content_bottom">
            <el-card class="box-card">
              <el-table :data="userData.slice((userCurrentPage - 1) * userPageSize, userCurrentPage * userPageSize)" border style="width: 100%; margin-top: 10px;">
                <el-table-column align="center" prop="username" label="username"></el-table-column>
                <el-table-column align="center" prop="email" label="email"></el-table-column>
                <el-table-column align="center" prop="status" label="status">
                  <template slot-scope="scope">
                    <el-button v-if="scope.row.status === 1" type="success" size="small" @click="handleEnabledClick(scope.row.username)">enabled</el-button>
                    <el-button v-else type="danger" size="small" @click="handleDisabledClick(scope.row.username)">disabled</el-button>
                  </template>
                </el-table-column>
              </el-table>
              <el-pagination style="text-align: center; margin-top: 5px;" :current-page="userCurrentPage" :page-sizes="[10, 20, 30, 40, 50]" :page-size="userPageSize" layout="total, sizes, prev, pager, next, jumper" :total="userNumber"></el-pagination>
            </el-card>
            </div>
        </div>
  </div>
</template>

<script>
  import axios from "axios";
  export default {
    name: 'manage',
    data () {
      return {
        index: '退出登录',
        user: '',
        activeIndex: 'manage',
        search: '',
        userData: [],
        userNumber: 3,
        userCurrentPage: 1,
        userPageSize: 10
      }
    },
    mounted () {
      this.loadUsers();
    },
    methods: {
      loadUsers() {
        this.user = this.$route.params.user;
        if(this.user === '') {
          alert("请登录！");
          this.$router.push({name: "Home"});
        }
        axios
          .post('http://localhost:8088/api/user/all', {"name": this.user})
          .then(response => {
            this.userData = response.data;
            this.userNumber = this.userData.length;
          })
      },
      handleEnabledClick (username) {
        let form = {"username": username, "status": 0};
        axios
          .post('http://localhost:8088/api/user/change', form)
          .then(response => {
            if(response.data === 1){
              alert("禁用成功！");
            }else{
              alert("禁用失败！");
            }
            this.loadUsers();
          })
      },
      handleDisabledClick (username) {
        let form = {"username": username, "status": 1};
        axios
          .post('http://localhost:8088/api/user/change', form)
          .then(response => {
            if(response.data === 1){
              alert("解禁成功！");
            }else{
              alert("解禁失败！");
            }
            this.loadUsers();
          })
      }
    }
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
