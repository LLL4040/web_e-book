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
                <a href="/home">首页</a>
                <a class="now" href="#">用户管理</a>
                <a href="/managebooks">书籍管理</a>
                <a href="/manageorder">查看订单</a>
                <a href="/statistics">统计</a>
            </div>
            <div id="search">
                <span>Search</span>
                <input type="text" />
                <input type="submit" />
            </div>
        </div>
        <div id="content">
                <div id="content_bottom">
      <el-main style="width: 800px; margin: 0 auto;">
        <el-table :data="userData.slice((userCurrentPage - 1) * userPageSize, userCurrentPage * userPageSize)" border style="width: 100%; margin-top: 10px;">
          <el-table-column align="center" prop="id" label="id"></el-table-column>
          <el-table-column align="center" prop="username" label="username"></el-table-column>
          <el-table-column align="center" prop="email" label="email"></el-table-column>
          <el-table-column align="center" prop="status" label="status">
            <template slot-scope="scope">
              <el-button v-if="scope.row.status == 1" type="success" size="small" @click="handleEnabledClick(scope.row.username)">enabled</el-button>
              <el-button v-else type="danger" size="small" @click="handleDisabledClick(scope.row.username)">disabled</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination style="text-align: center; margin-top: 5px;" :current-page="userCurrentPage" :page-sizes="[10, 20, 30, 40, 50]" :page-size="userPageSize" layout="total, sizes, prev, pager, next, jumper" :total="userNumber"></el-pagination>
      </el-main>
            </div>
        </div>
  </div>
</template>

<script>
export default {
  name: 'manage',
  data: function () {
    return {
      activeIndex: 'manage',
      search: '',
      userData: [{
        id: 1,
        username: '1',
        email: '111@qq.com',
        status: 1
      }, {
        id: 2,
        username: '2',
        email: '222@qq.com',
        status: 1
      }, {
        id: 3,
        username: '3',
        email: '333@qq.com',
        status: 1
      }],
      userNumber: 3,
      userCurrentPage: 1,
      userPageSize: 10
    };
  },
  methods: {
    handleEnabledClick (username) {
      window.console.log(username);
      for (var i = 0; i < this.userData.length; i++) {
        if (this.userData[i].username == username) {
          this.userData[i].status = 0;
          break;
        }
      }
    },
    handleDisabledClick (username) {
      window.console.log(username);
      for (var i = 0; i < this.userData.length; i++) {
        if (this.userData[i].username == username) {
          this.userData[i].status = 1;
          break;
        }
      }
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
