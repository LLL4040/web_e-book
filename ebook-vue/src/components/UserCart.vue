<template>
    <div class="Cart">
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
              <a class="now" href="#">购物车</a>
              <router-link :to="{name:'UserOrder',params:{user: this.user}}" >我的订单</router-link>
              <router-link :to="{name:'ChatRoom',params:{user: this.user}}" >聊天室</router-link>
            </div>
        </div>
        <div id="content">
            <div id="content_bottom">
              <el-card class="box-card" style="text-align: center;">
                <div v-if="CartItems.length === 0">
                  您的购物车为空
                </div>
                <el-row v-for="(item, index) in CartItems" :key="index" style="padding: 10px 0; border-bottom: 1px solid #eff2f6">
                  <el-col :span="4">
                    <img :src="item.book.cover" style="width: 120px;">
                  </el-col>
                  <el-col :span="4" style="line-height: 50px; width: 120px;">
                    {{ item.book.bookname }}
                  </el-col>
                  <el-col :span="4" style="height: 103.8px; display: flex; justify-content: center; flex-direction: column;">
                    单价<br>{{ item.book.price }}
                  </el-col>
                  <el-col :span="4" style="height: 103.8px; display: flex; justify-content: center; flex-direction: column;">
                    数量<br>
                    <el-input-number v-model="item.num" @change="handleChange(item.book.isbn, item.num)" :precision="0" size="small" :min="1" :max="99"></el-input-number>
                  </el-col>
                  <el-col :span="4" style="height: 103.8px; display: flex; justify-content: center; flex-direction: column;">
                    金额<br>{{ item.book.price * item.num }}
                  </el-col>
                  <el-col :span="4" style="line-height: 103.8px">
                    <el-button type="danger" icon="el-icon-delete" circle @click="handleDelete(item.id, item.book.isbn)"></el-button>
                  </el-col>
                </el-row>
              </el-card>
              <el-row style="margin-top: 10px;">
                <el-card class="box-card" style="text-align: right;">
                  <el-row type="flex" justify="end">
                    <el-col :span="4" style="text-align: center; line-height: 40px;">
                      总价 {{ getTotalAmount }}
                    </el-col>
                    <el-col :span="4" style="text-align: center;">
                      <el-button type="primary" @click="handleSubmit()">提交订单</el-button>
                    </el-col>
                  </el-row>
                </el-card>
              </el-row>
            </div>
        </div>
    </div>
</template>

<script>
  import axios from "axios";

  export default {
    name: 'Cart',
    data: function () {
      return {
        user: '',
        activeIndex: 'cart',
        CartItems: []
      };
    },
    mounted () {
      this.loadCarts();
    },
    computed: {
      getTotalAmount () {
        let s = 0;
        for (let i = 0; i < this.CartItems.length; i++) {
          s += (this.CartItems[i].num * this.CartItems[i].book.price);
        }
        return s;
      }
    },
    methods: {
      loadCarts () {
        this.user = this.$route.params.user;
        if (this.user === '') {
          alert("请登录后查看购物车！");
          this.$router.push({name: "Home"});
        }
        let form = {"username": this.user};
        axios
          .post('http://localhost:8088/api/cart/all', form)
          .then(response => {
            this.CartItems = response.data;
            let self = this;
            for(let i = 0; i < this.CartItems.length; i++) {
              axios
                .post('http://localhost:8088/api/book/isbn/mongo', {"isbn": self.CartItems[i].book.isbn})
                .then(response => {
                  self.CartItems[i].book.cover = "data:image/png;base64," + response.data.cover.toString();
                })
            }
          })
      },
      handleDelete (i, ISBN) {
        let form = {"username": this.user, "isbn": ISBN};
        axios
          .post('http://localhost:8088/api/cart/delete', form)
          .then(response => {
            if (response.data === true) {
              alert("删除成功！");
              this.loadCarts();
              // this.$forceUpdate();
            }
          })
      },
      handleChange (ISBN, num) {
        let form = {"username": this.user, "isbn": ISBN, "num": num};
        axios
          .post('http://localhost:8088/api/cart/update', form)
          .then(response => {
            if (response.data === true) {
              console.log("同步成功！");
            }
            else {
              alert("加购数量不能超过库存量！");
              console.log("同步失败！");
            }
          })
      },
      handleSubmit () {
        let form = {"username": this.user};
        axios
          .post('http://localhost:8088/api/order/insert', form)
          .then(response => {
            if (response.data === true) {
              alert("提交成功！");
              this.CartItems = [];
            }
            else {
              alert("提交失败！请联系客服处理。");
            }
          })
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
