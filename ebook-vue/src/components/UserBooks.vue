<template>
  <div class="book_user">
      <div id="page" align="center">
			<div id="top">
				<div id="top_left">
					<img alt="" src="../assets/logo.png" width="50%">
				</div>
			</div>
			<div class="clear"></div>
			<div id="menu">
        <router-link :to="{name:'Home',params:{username: ''}}" >退出登录</router-link>
				<a class="now" href="#">书籍</a>
        <router-link :to="{name:'UserCart',params:{username: this.username}}" >购物车</router-link>
        <router-link :to="{name:'UserOrder',params:{username: this.username}}" >我的订单</router-link>
			</div>
		</div>
    <div id="content">
      <div id="content_bottom">
			<el-table :data="books.slice((bookCurrentPage - 1) * bookPageSize, bookCurrentPage * bookPageSize) &&
			books.filter(data => !search || data.bookname.toLowerCase().includes(search.toLowerCase()))"
                border style="width: 100%; margin-top: 10px">
          <el-table-column align="center" prop="isbn" label="ISBN" width="150px"></el-table-column>
          <el-table-column align="center" prop="cover" label="封面">
            <template slot-scope="scope">
              <img :src="scope.row.cover" style="width: 80px; height: 120px">
            </template>
          </el-table-column>
          <el-table-column align="center" prop="bookname" label="书名"></el-table-column>
          <el-table-column align="center" prop="author" label="作者"></el-table-column>
          <el-table-column align="center" prop="price" label="单价"></el-table-column>
          <el-table-column align="center" prop="amount" label="库存"></el-table-column>
          <el-table-column align="center" width="170px">
            <template slot="header" slot-scope="scope">
              <el-input v-model="search" size="mini" placeholder="输入关键字搜索"/>
            </template>
            <template slot-scope="scope">
              <el-popover placement="right" width="500" trigger="click">
                <el-table :data="scope.row" height="250">
                  <el-table-column width="500" prop="contentInfo" label="内容简介"></el-table-column>
                </el-table>
                <el-table :data="scope.row" height="250">
                  <el-table-column width="500" prop="authorInfo" label="作者简介"></el-table-column>
                </el-table>
                <el-button size="mini" slot="reference">详情</el-button>
              </el-popover>
              <el-button size="mini" type="danger" @click="handleAdd(scope.row)">加购</el-button>
            </template>
          </el-table-column>
      </el-table>
      <el-pagination style="text-align: center; margin-top: 5px;" :current-page="bookCurrentPage"
                     :page-sizes="[10, 20, 30, 40, 50]" :page-size="bookPageSize"
                     layout="total, sizes, prev, pager, next, jumper" :total="bookNumber"></el-pagination>
      <div class="clear"></div>
    </div>
    </div>
  </div>
</template>

<script>
  import axios from "axios";

  export default {
    name: 'book_user',
    data () {
      return {
        username: 'test',
        bookNumber: 0,
        bookCurrentPage: 1,
        bookPageSize: 10,
        books: [],
        search: '',
      }
    },
    mounted () {
      axios
        .get('http://localhost:8088/api/books')
        .then(response => {
          this.books = response.data;
          this.bookNumber = this.books.length;
        })
    },
    methods: {
      handleAdd(row) {
        let form = {"username": this.username, "ISBN": row.isbn, "num": 1};
        axios
          .post('http://localhost:8088/api/insert', form)
          .then(response => {
            if (response.data === 1) {
              alert("成功加入购物车！");
            }
            else {
              alert("加入失败请重试！");
            }
          })
      }
    }
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  .el-table .cell {
    white-space: pre-wrap;
  }
  .el-menu-item {
    font-size: 18px;
  }
</style>
