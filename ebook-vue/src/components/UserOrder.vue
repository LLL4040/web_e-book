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
              <router-link :to="{name:'Home',params:{user: ''}}" >退出登录</router-link>
              <router-link :to="{name:'UserBooks',params:{user: this.user}}" >书籍</router-link>
              <router-link :to="{name:'UserCart',params:{user: this.user}}" >购物车</router-link>
              <a class="now" href="#">我的订单</a>
            </div>
        </div>
        <div id="content">
            <div id="content_bottom">
                <el-main style="width: 900px; margin: 0 auto;">
                  <el-table :data="items.slice((itemCurrentPage - 1) * itemPageSize, itemCurrentPage * itemPageSize) && items.filter(data => (!startdate || data.time >= startdate) &&(!enddate || data.time <= enddate))" :span-method="objectSpanMethod" border style="width: 100%; margin-top: 20px">
                    <el-table-column align="center" prop="id" label="ID" width="100"></el-table-column>
                    <el-table-column align="center" prop="time" label="日期" width="160"></el-table-column>
                    <el-table-column align="center" prop="cover" label="封面" width="110">
                      <template slot-scope="scope">
                        <img :src="scope.row.cover" style="width: 80px; height: 120px">
                      </template>
                    </el-table-column>
                    <el-table-column align="center" prop="bookname" label="书名"></el-table-column>
                    <el-table-column align="center" prop="price" label="单价"></el-table-column>
                    <el-table-column align="center" prop="number" label="数量"></el-table-column>
                    <el-table-column align="center" prop="total" width="200">
                      <template slot="header" slot-scope="scope">
                        <el-input v-model="startdate" type="date" style="width: 170px;"/>
                        <el-input v-model="enddate"   type="date" style="width: 170px;"/>
                      </template>
                    </el-table-column>
                  </el-table>
                </el-main>
              <el-pagination style="text-align: center; margin-top: 5px;" :current-page="itemCurrentPage"
                             :page-sizes="[10, 20, 30, 40, 50]" :page-size="itemPageSize"
                             layout="total, sizes, prev, pager, next, jumper" :total="itemNumber"></el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
  import axios from "axios";

  export default {
    name: 'orders',
    data: function () {
      return {
        user: '',
        activeIndex: 'orders',
        items: [],
        itemCurrentPage: 1,
        itemPageSize: 10,
        itemNumber: 0,
        spanArr: [],
        startdate: '',
        enddate: ''
      };
    },
    mounted () {
      this.user = this.$route.params.user;
      if (this.user === '') {
        alert("请登录后查看订单！");
        this.$router.push({name: "Home"});
      }
      let form = {"username": this.user};
      axios
        .post('http://localhost:8088/api/orders', form)
        .then(response => {
          this.items = response.data;
          this.itemNumber = this.items.length;
          for (let i = 0; i < this.items.length; i++) {
            this.$set(this.items[i],'total',0);
            if (i === 0) {
              this.spanArr.push(1);
              this.pos = 0;
              this.items[i].total = this.items[i].price * this.items[i].number;
            } else {
              // 判断当前元素与上一个元素是否相同
              if (this.items[i].id === this.items[i - 1].id) {
                this.spanArr[this.pos] += 1;
                this.spanArr.push(0);
                this.items[this.pos].total += this.items[i].price * this.items[i].number;
              } else {
                this.spanArr.push(1);
                this.pos = i;
                this.items[this.pos].total = this.items[i].price * this.items[i].number;
              }
            }
          }
        })
    },
    methods: {
      objectSpanMethod({ row, column, rowIndex, columnIndex }) {
        if (columnIndex === 0 || columnIndex === 1 || columnIndex === 6) {
          const _row = this.spanArr[rowIndex];
          const _col = _row > 0 ? 1 : 0;
          return {
            rowspan: _row,
            colspan: _col
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
