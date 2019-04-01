<template>
  <div class="book_management">
      <div id="page" align="center">
			<div id="top">
				<div id="top_left">
					<img alt="" src="../assets/logo.png" width="50%">
				</div>
			</div>
			<div class="clear"></div>
			<div id="menu">
				<a href="/home">首页</a>
				<a class="now" href="#">书籍</a>
				<a href="/usercart">购物车</a>
				<a href="/userorder">我的订单</a>
			</div>
			<div id="search">
				<span>Search</span>
				<input type="text" />
        <input type="submit" />
			</div>
		</div>
		<div id="content">
			<el-table :data="bookData.slice((bookCurrentPage - 1) * bookPageSize, bookCurrentPage * bookPageSize)" border style="width: 100%; margin-top: 10px">
          <el-table-column prop="id" label="id"></el-table-column>
          <el-table-column label="封面">
            <template slot-scope="scope">
              <img :src="scope.row.cover" style="width: 50px">
            </template>
          </el-table-column>
          <el-table-column prop="title" label="书名"></el-table-column>
          <el-table-column prop="author" label="作者"></el-table-column>
          <el-table-column prop="price" label="单价"></el-table-column>
          <el-table-column prop="stock" label="库存"></el-table-column>
          <el-table-column label="操作" width="150px">
            <template slot-scope="scope">
              <el-button size="mini" @click="handleEdit(scope.row.id)">编辑</el-button>
              <el-button size="mini" type="danger">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination style="text-align: center; margin-top: 5px;" :current-page="bookCurrentPage" :page-sizes="[10, 20, 30, 40, 50]" :page-size="bookPageSize" layout="total, sizes, prev, pager, next, jumper" :total="bookNumber"></el-pagination>
        <el-dialog title="书籍编辑" :visible.sync="dialogFormVisible" width="400px">
          <el-form :model="bookForm">
            <el-form-item label="书名">
              <el-input v-model="bookForm.title"></el-input>
            </el-form-item>
            <el-form-item label="作者">
              <el-input v-model="bookForm.author"></el-input>
            </el-form-item>
            <el-form-item label="单价">
              <el-input v-model="bookForm.price"></el-input>
            </el-form-item>
            <el-form-item label="库存">
              <el-input v-model="bookForm.stock"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="dialogFormVisible = false">提交</el-button>
          </div>
        </el-dialog>
			<div class="clear"></div>
            
		</div>
  </div>
</template>

<script>
export default {
  name: 'book_management',
  data: function() {
    return {
      activeIndex: 'bookmanagement',
      search: '',
      dialogFormVisible: false,
      bookForm: {
        id: 1,
        cover: require('../assets/software.jpg'),
        title: '软件工程原理',
        author: 'xxx',
        price: 10,
        stock: 0
      },
      bookData: [{
        id: 1,
        cover: require('../assets/datastructure.jpg'),
        title: '数据结构',
        author: 'xxx',
        price: 10,
        stock: 0
      },{
        id: 2,
        cover: require('../assets/software.jpg'),
        title: '软件工程原理',
        author: 'xxx',
        price: 10,
        stock: 0
      },{
        id: 3,
        cover: require('../assets/database.jpg'),
        title: '数据库系统概念',
        author: 'xxx',
        price: 10,
        stock: 0
      },{
        id: 4,
        cover: require('../assets/ics.jpg'),
        title: '深入理解计算机系统',
        author: 'xxx',
        price: 10,
        stock: 0
      }],
      bookNumber: 4,
      bookCurrentPage: 1,
      bookPageSize: 10
    }
  },
  methods: {
    handleEdit(id) {
      window.console.log(id);
      this.dialogFormVisible = true;
    }
  }
}
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