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
        <router-link :to="{name:'Home',params:{user: ''}}" >{{ index }}</router-link>
				<a class="now" href="#">书籍</a>
        <router-link :to="{name:'UserCart',params:{user: this.user}}" >购物车</router-link>
        <router-link :to="{name:'UserOrder',params:{user: this.user}}" >我的订单</router-link>
			</div>
		</div>
    <div id="content">
      <div id="content_bottom">
			<el-table :data="books.slice((bookCurrentPage - 1) * bookPageSize, bookCurrentPage * bookPageSize) &&
			books.filter(data => !search || data.bookname.toLowerCase().includes(search.toLowerCase()))"
                border style="width: 100%; margin-top: 10px">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="内容简介">
                <span>{{ props.row.contentInfo }}</span>
              </el-form-item>
              <el-form-item label="作者简介">
                <span>{{ props.row.authorInfo }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
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
        index: '首页',
        user: '',
        bookNumber: 0,
        bookCurrentPage: 1,
        bookPageSize: 10,
        books: [],
        search: '',
      }
    },
    mounted () {
      this.user = this.$route.params.user;
      if (this.user !== '') {
        this.index = '退出登录';
      }
      axios
        .get('http://localhost:8088/api/book/all')
        .then(response => {
          this.books = response.data;
          this.bookNumber = this.books.length;
        })
    },
    methods: {
      handleAdd(row) {
        if (this.user === '') {
          alert("请登录后再加入购物车！");
          this.$router.push({name: "Home"});
        }
        let form = {"username": this.user, "isbn": row.isbn, "num": 1};
        axios
          .post('http://localhost:8088/api/cart/insert', form)
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
