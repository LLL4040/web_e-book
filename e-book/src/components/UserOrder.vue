<template>
    <div class="orders">
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
                <a href="/usercart">购物车</a>
                <a class="now" href="#">我的订单</a>
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
                    <el-card v-for="(order, orderIndex) in orders" :key="orderIndex" class="box-card" style="margin: 10px 0;">
                    <div slot="header" class="clearfix">
                        <span><b>{{ order.date }}</b> 订单号：{{ order.orderNumber }}</span>
                    </div>
                    <el-row style="text-align: center;">
                        <el-col :span="16">
                        <div v-for="(item, itemIndex) in order.items" :key="itemIndex" style="padding: 10px 0; border-bottom: 1px solid #eff2f6">
                            <el-row>
                            <el-col :span="6">
                                <img :src="item.cover" style="width: 100px;">
                            </el-col>
                            <el-col :span="6" style="line-height: 103.8px">
                                {{ item.title }}
                            </el-col>
                            <el-col :span="6" style="height: 103.8px; display: flex; justify-content: center; flex-direction: column;">
                                数量<br>{{ item.number }}
                            </el-col>
                            <el-col :span="6" style="height: 103.8px; display: flex; justify-content: center; flex-direction: column;">
                                价格<br>{{ item.amount }}
                            </el-col>
                            </el-row>
                        </div>
                        </el-col>
                        <el-col :span="4" style="height: 249.6px; display: flex; justify-content: center; flex-direction: column;">
                        总数<br>{{ order.number }}
                        </el-col>
                        <el-col :span="4" style="height: 249.6px; display: flex; justify-content: center; flex-direction: column;">
                        总价<br>{{ order.totalAmount }}
                        </el-col>
                    </el-row>
                    </el-card>
                </el-main>
            </div>
        </div>
    </div>
</template>

<script>
export default {
  name: 'orders',
  data: function() {
    return {
      activeIndex: 'orders',
      orders: [
      {
        date: '2019-04-01',
        orderNumber: 1651194849,
        number: 2,
        totalAmount: 200.00,
        items: [{
          cover: require('../assets/datastructure.jpg'),
          title: '数据结构',
          number: 1,
          amount: '100.00'
        },{
          cover: require('../assets/software.jpg'),
          title: '软件工程原理',
          number: 2,
          amount: '100.00'
        }]
      },{
        date: '2019-04-01',
        orderNumber: 1651195445,
        number: 2,
        totalAmount: 200.00,
        items: [{
          cover: require('../assets/database.jpg'),
          title: '数据库系统概念',
          number: 1,
          amount: '100.00'
        },{
          cover: require('../assets/ics.jpg'),
          title: '深入理解计算机系统',
          number: 2,
          amount: '100.00'
        }]
      }]
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
