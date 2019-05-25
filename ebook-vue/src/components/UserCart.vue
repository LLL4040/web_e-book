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
                    <img :src="item.cover" style="width: 120px;">
                  </el-col>
                  <el-col :span="4" style="line-height: 50px; width: 120px;">
                    {{ item.bookname }}
                  </el-col>
                  <el-col :span="4" style="height: 103.8px; display: flex; justify-content: center; flex-direction: column;">
                    单价<br>{{ item.price }}
                  </el-col>
                  <el-col :span="4" style="height: 103.8px; display: flex; justify-content: center; flex-direction: column;">
                    数量<br>
                    <el-input-number v-model="item.num" @change="handleChange(item.username, item.isbn, item.num)" :precision="0" size="small" :min="1" :max="99"></el-input-number>
                  </el-col>
                  <el-col :span="4" style="height: 103.8px; display: flex; justify-content: center; flex-direction: column;">
                    金额<br>{{ item.price * item.num }}
                  </el-col>
                  <el-col :span="4" style="line-height: 103.8px">
                    <el-button type="danger" icon="el-icon-delete" circle @click="handleDelete(item.id, item.isbn)"></el-button>
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
      this.user = this.$route.params.user;
      if (this.user === '') {
        alert("请登录后查看购物车！");
        this.$router.push({name: "Home"});
      }
      let form = {"username": this.user};
      axios
        .post('http://localhost:8088/api/carts', form)
        .then(response => {
          this.CartItems = response.data;
        })
    },
    computed: {
      getTotalAmount () {
        let s = 0;
        for (let i = 0; i < this.CartItems.length; i++) {
          s += (this.CartItems[i].num * this.CartItems[i].price);
        }
        return s;
      }
    },
    methods: {
      handleDelete (i, ISBN) {
        let form = {"username": this.user, "isbn": ISBN};
        axios
          .post('http://localhost:8088/api/delete', form)
          .then(response => {
            if (response.data === 1) {
              alert("删除成功！");
              axios
                .post('http://localhost:8088/api/carts', form)
                .then(response => {
                  this.CartItems = response.data;
                })
            }
          })
      },
      handleChange (username, ISBN, num) {
        let form = {"username": username, "isbn": ISBN, "num": num};
        axios
          .post('http://localhost:8088/api/update', form)
          .then(response => {
            if (response.data === 1) {
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
          .post('http://localhost:8088/api/submit', form)
          .then(response => {
            if (response.data === 1) {
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
