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
                <a href="/home">首页</a>
                <a href="/manage">用户管理</a>
                <a href="/managebooks">书籍管理</a>
                <a href="/manageorder">查看订单</a>
                <a class="now" href="#">统计</a>
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
        <el-row type="flex" justify="center">
          <el-date-picker v-model="dateRange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
          <el-button type="primary" style="margin-left: 5px;">提交</el-button>
        </el-row>
        <h4>用户统计</h4>
        <el-table :data="userStatisticsData.slice((userStatisticsCurrentPage - 1) * userStatisticsPageSize, userStatisticsCurrentPage * userStatisticsPageSize)" border style="width: 100%;">
          <el-table-column align="center" prop="id" label="id"></el-table-column>
          <el-table-column align="center" prop="username" label="用户名"></el-table-column>
          <el-table-column align="center" prop="totalNumber" label="总数"></el-table-column>
          <el-table-column align="center" prop="totalAmount" label="总金额"></el-table-column>
        </el-table>
        <h4>书籍统计</h4>
        <el-table :data="bookStatisticsData.slice((bookStatisticsCurrentPage - 1) * bookStatisticsPageSize, bookStatisticsCurrentPage * bookStatisticsPageSize)" border style="width: 100%;">
          <el-table-column align="center" prop="id" label="id"></el-table-column>
          <el-table-column align="center" prop="title" label="书名"></el-table-column>
          <el-table-column align="center" prop="totalNumber" label="总数"></el-table-column>
          <el-table-column align="center" prop="totalAmount" label="总金额"></el-table-column>
        </el-table>
      </el-main>
                </div>
        </div>
  </div>
</template>

<script>
export default {
  name: 'statistics',
  data: function () {
    return {
      activeIndex: 'statistics',
      dateRange: '',
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
      userStatisticsPageSize: 10,
      bookStatisticsData: [{
        id: 1,
        title: '1',
        totalNumber: 1,
        totalAmount: 12
      }, {
        id: 2,
        title: '2',
        totalNumber: 2,
        totalAmount: 30
      }],
      bookStatisticsNumber: 2,
      bookStatisticsCurrentPage: 1,
      bookStatisticsPageSize: 10
    };
  },
  methods: {
    handleEdit (id) {
      window.console.log(id);
      this.dialogFormVisible = true;
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
