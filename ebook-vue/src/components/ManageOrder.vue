<template>
  <div class="manageorder">
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
                <a class="now" href="#">查看订单</a>
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
        <el-row type="flex" justify="center">
          <el-col :span="8">
            <el-input placeholder="请输入订单号" v-model="search" clearable></el-input>
          </el-col>
        </el-row>
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
  name: 'manageorder',
  data: function () {
    return {
      activeIndex: 'manageorder',
      search: '',
      orders: [
        {
          date: '2019-01-09',
          orderNumber: 1651194849,
          number: 3,
          totalAmount: '41.00',
          items: [{
            cover: require('../assets/datastructure.jpg'),
            title: '数据结构',
            number: 1,
            amount: '100.00'
          }, {
            cover: require('../assets/software.jpg'),
            title: '软件工程原理',
            number: 2,
            amount: '100.00'
          }]
        }, {
          date: '2019-04-01',
          orderNumber: 1651195445,
          number: 2,
          totalAmount: 200.00,
          items: [{
            cover: require('../assets/database.jpg'),
            title: '数据库系统概念',
            number: 1,
            amount: '100.00'
          }, {
            cover: require('../assets/ics.jpg'),
            title: '深入理解计算机系统',
            number: 2,
            amount: '100.00'
          }]
        }]
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
