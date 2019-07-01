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
        <a class="now" href="#">书籍管理</a>
        <router-link :to="{name:'ManageUsers',params:{user: this.user}}" >用户管理</router-link>
        <router-link :to="{name:'ManageOrders',params:{user: this.user}}" >订单管理</router-link>
        <router-link :to="{name:'Statistics',params:{user: this.user}}" >统计</router-link>
      </div>
    </div>
    <div id="content">
      <div id="content_bottom">
        <el-card class="box-card">
          <el-row type="flex" justify="center">
            <el-input v-model="search" size="mini" placeholder="输入关键字搜索"/>
          </el-row>
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
            <el-table-column align="center" width="180px">
              <template slot="header" slot-scope="scope">
                <el-button size="mini " style="margin-left: 5px; " @click="handleAdd()">添加新书</el-button>
              </template>
              <template slot-scope="scope">
                <el-button size="mini" type="success" @click="handleEdit(scope.row)">编辑</el-button>
                <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination style="text-align: center; margin-top: 5px;" :current-page="bookCurrentPage"
                         :page-sizes="[10, 20, 30, 40, 50]" :page-size="bookPageSize"
                         layout="total, sizes, prev, pager, next, jumper" :total="bookNumber"></el-pagination>
          <div class="clear"></div>
          <el-dialog title="书籍管理" :visible.sync="dialogFormVisible" width="400px">
            <el-form :model="bookForm">
              <el-form-item label="ISBN">
                <el-input v-model="bookForm.isbn"></el-input>
              </el-form-item>
              <el-form-item label="封面">
                <br>
                <input type='file' accept="image/*" id="file" ref="file"></input>
              </el-form-item>
              <el-form-item label="书名">
                <el-input v-model="bookForm.bookname"></el-input>
              </el-form-item>
              <el-form-item label="作者">
                <el-input v-model="bookForm.author"></el-input>
              </el-form-item>
              <el-form-item label="单价">
                <el-input v-model="bookForm.price"></el-input>
              </el-form-item>
              <el-form-item label="库存">
                <el-input v-model="bookForm.amount"></el-input>
              </el-form-item>
              <el-form-item label="作者简介">
                <el-input v-model="bookForm.authorInfo"></el-input>
              </el-form-item>
              <el-form-item label="内容简介">
                <el-input v-model="bookForm.contentInfo"></el-input>
              </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button type="primary" @click="submitChange()">提交</el-button>
            </div>
          </el-dialog>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from "axios";

  export default {
    name: 'book_manager',
    data () {
      return {
        index: '退出登录',
        user: '',
        file:'',
        bookNumber: 0,
        bookCurrentPage: 1,
        bookPageSize: 10,
        books: [],
        book:'',
        search: '',
        dialogFormVisible: false,
        bookForm: {
          isbn: '',
          cover: null,
          bookname: '',
          author: '',
          price: 0,
          amount: 0,
          authorInfo: '',
          contentInfo: ''
        }
      }
    },
    mounted () {
      this.loadBooks();
    },
    methods: {
      loadBooks () {
        this.user = this.$route.params.user;
        if (this.user === '') {
          alert("请登录！");
          this.$router.push({name: "Home"});
        }
        axios
          .get('http://localhost:8088/api/book/all')
          .then(response => {
            this.books = response.data;
            this.bookNumber = this.books.length;
            let self = this;
            for(let i = 0; i < this.bookNumber; i++) {
              axios
                .post('http://localhost:8088/api/book/isbn/mongo', {"isbn": self.books[i].isbn})
                .then(response => {
                  self.books[i].cover = "data:image/png;base64," + response.data.cover.toString();
                })
            }
          })
      },
      handleEdit (row) {
        this.bookForm = row;
        this.dialogFormVisible = true;
      },
      handleAdd () {
        this.bookForm = [];
        this.dialogFormVisible = true;
      },
      handleDelete (row) {
        let form = {"isbn": row.isbn, "amount": 0};
        axios
          .post("http://localhost:8088/api/book/delete", form)
          .then(response => {
            if(response) {
              alert("删除成功！");
              this.loadBooks();
            }
            else {
              alert("删除失败！");
            }
          })
      },
      submitChange() {
        this.dialogFormVisible = false;
        let imagFile = this.$refs.file.files[0];
        let bodyData = new FormData();
        bodyData.set('isbn', this.bookForm.isbn);
        bodyData.set('bookname', this.bookForm.bookname);
        bodyData.set('author', this.bookForm.author);
        bodyData.set('price', this.bookForm.price);
        bodyData.set('amount', this.bookForm.amount);
        bodyData.set('authorInfo', this.bookForm.authorInfo);
        bodyData.set('contentInfo', this.bookForm.contentInfo);
        bodyData.append('cover', imagFile);
        axios({method: 'post',
            url: 'http://localhost:8088/api/book/add',
            data: bodyData,
            config: { headers: {'Content-Type': 'multipart/form-data' }}}
            )
          .then(response => {
            if(response.data) {
              alert("更新数据成功！");
              this.loadBooks();
            }
            else {
              alert("更新失败！");
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
