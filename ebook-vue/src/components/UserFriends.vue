<template>
  <div class="friends">
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
        <router-link :to="{name:'UserOrder',params:{user: this.user}}" >我的订单</router-link>
        <a class="now" href="#">我的好友</a>
        <router-link :to="{name:'ChatRoom',params:{user: this.user}}" >聊天室</router-link>
      </div>
    </div>
    <div id="content">
      <div id="content_bottom">
        <el-card class="box-card">
          <el-row type="flex" justify="center">
            <el-input v-model="search" size="mini" placeholder="输入用户名进行搜索"/>
            <el-button size="mini" @click="handleSearch()">search</el-button>
          </el-row>
          <el-table :data="findUser" border style="width: 100%; margin-top: 10px;">
            <el-table-column align="center" prop="username" label="username"></el-table-column>
            <el-table-column align="center" label="操作">
              <template slot-scope="scope">
                <el-button type="success" size="small" @click="handleAdd(scope.row.username)">添加好友</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-table :data="userData.slice((userCurrentPage - 1) * userPageSize, userCurrentPage * userPageSize)" border style="width: 100%; margin-top: 10px;">
            <el-table-column align="center" prop="username" label="username"></el-table-column>
            <el-table-column align="center" label="操作">
              <template slot-scope="scope">
                <el-button type="danger" size="small" @click="handleDelete(scope.row.username)">删除好友</el-button>
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
    name: 'Friends',
    data () {
      return {
        user: '',
        search: '',
        findUser: [],
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
          .post('http://localhost:8888/api/friend/findAll', {"name": this.user})
          .then(response => {
            this.userData = response.data;
            this.userNumber = this.userData.length;
          })
      },
      handleSearch() {
        if(this.search === '') {
          alert("请输入用户名再点击搜索！");
          return;
        }
        let form = {"username": this.search};
        axios
          .post('http://localhost:8888/api/user/friend/find', form)
          .then(response => {
            this.findUser = [];
            if(response.data.length === 0) {
              alert("该用户不存在！");
            } else {
              this.findUser = response.data;
            }
          })
      },
      handleAdd (username) {
        let form = {"username": username};
        axios
          .post('http://localhost:8888/api/friend/add', form)
          .then(response => {
            if(response.data === 1){
              alert("添加成功！");
            }else{
              alert("添加失败！");
            }
            this.loadUsers();
          })
      },
      handleDelete (username) {
        let form = {"username": username};
        axios
          .post('http://localhost:8888/api/friend/delete', form)
          .then(response => {
            if(response.data === 1){
              alert("删除成功！");
            }else{
              alert("删除失败！");
            }
            this.loadUsers();
          })
      }
    }
  };
</script>

<style scoped>

</style>
