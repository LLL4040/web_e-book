<template>
    <div class="shoppingCart">
        <div id="page" align="center">
            <div id="top">
                <div id="top_left">
                    <img alt="" src="../assets/logo.png" width="50%">
                </div>
            </div>
            <div class="clear"></div>
            <div id="menu">
                <a href="/home">首页</a>
                <a href="/userbooks">书籍</a>
                <a class="now" href="/usercart">购物车</a>
                <a href="/userorder">我的订单</a>
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
                    <el-card class="box-card" style="text-align: center;">
                    <div v-if="shoppingCartItems.length == 0">
                        您的购物车为空
                    </div>
                    <el-row v-for="(item, index) in shoppingCartItems" :key="index" style="padding: 10px 0; border-bottom: 1px solid #eff2f6">
                        <el-col :span="4">
                        <img :src="item.cover" style="width: 100px;">
                        </el-col>
                        <el-col :span="4" style="line-height: 103.8px">
                        {{ item.title }}
                        </el-col>
                        <el-col :span="4" style="height: 103.8px; display: flex; justify-content: center; flex-direction: column;">
                        单价<br>{{ item.price }}
                        </el-col>
                        <el-col :span="4" style="height: 103.8px; display: flex; justify-content: center; flex-direction: column;">
                        数量<br>
                        <el-input-number v-model="item.number" size="small" :min="1" :max="99"></el-input-number>
                        </el-col>
                        <el-col :span="4" style="height: 103.8px; display: flex; justify-content: center; flex-direction: column;">
                        金额<br>{{ item.price * item.number }}
                        </el-col>
                        <el-col :span="4" style="line-height: 103.8px">
                        <el-button type="danger" icon="el-icon-delete" circle @click="handleDelete(item.id)"></el-button>
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
                            <el-button type="primary">下单</el-button>
                        </el-col>
                        </el-row>
                    </el-card>
                    </el-row>
                </el-main>
            </div>
        </div>
    </div>
</template>

<script>
export default {
  name: 'shoppingCart',
  data: function () {
    return {
      activeIndex: 'shoppingcart',
      shoppingCartItems: [{
        id: 1,
        cover: require('../assets/datastructure.jpg'),
        title: '数据结构',
        number: 1,
        price: 100
      }, {
        id: 2,
        cover: require('../assets/software.jpg'),
        title: '软件工程原理',
        number: 2,
        price: 100
      }]
    };
  },
  computed: {
    getTotalAmount () {
      var s = 0;
      for (var i = 0; i < this.shoppingCartItems.length; i++) {
        s += (this.shoppingCartItems[i].number * this.shoppingCartItems[i].price);
      }
      return s;
    }
  },
  methods: {
    handleDelete (id) {
      for (var i = 0; i < this.shoppingCartItems.length; i++) {
        if (this.shoppingCartItems[i].id === id) {
          this.shoppingCartItems.splice(i, 1);
        }
      }
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
