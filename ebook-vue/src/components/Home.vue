<template>
  <div class="home">
    <div id="page" align="center">
            <div id="top">
                <div id="top_left">
                    <img alt="" src="../assets/logo.png" width="50%">
                </div>
            </div>
            <div class="clear"></div>
            <div id="menu">
              <a class="now" href="#">首页</a>
              <router-link :to="{name:'UserBooks',params:{user: this.user}}" >书籍</router-link>
              <router-link :to="{name:'UserCart',params:{user: this.user}}" >购物车</router-link>
              <router-link :to="{name:'UserOrder',params:{user: this.user}}" >我的订单</router-link>
            </div>
        </div>
        <div id="content">
                <div id="content_bottom">
            <div class="main">
                <!-- 左侧信息栏 -->
                <div class="sideleft">
                    <h2>欢迎来到eBooks！</h2>
                    <img alt="" src="../assets/bg.jpg">
                </div>
                <!-- 右侧登陆界面 -->
                <div class="sideright">
                    <div class="index">
                      <p class="headline">用户登录</p>
                      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="90px" class="demo-ruleForm">
                        <el-form-item label="用户名" prop="username">
                          <el-input v-model="ruleForm.username"></el-input>
                        </el-form-item>
                        <el-form-item label="密码" prop="password">
                          <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item align="center">
                          <el-button type="primary" @click="submitForm(ruleForm)">登录</el-button>
                          <el-button @click="resetForm('ruleForm')">重置</el-button>
                        </el-form-item>
                      </el-form>
                      <p class="cstyle">忘记密码？<a href="#">点此找回</a></p>
                      <p class="cstyle">没有账号？<a href="/register">立即注册</a></p>
                    </div>
                </div>
                </div>
            </div>
        </div>
  </div>
</template>

<script>
  import axios from "axios";

  export default {
    data() {
      let checkName = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入用户名'));
        } else {
          callback();
        }
      };
      let validatePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.ruleForm.checkPass !== '') {
            this.$refs.ruleForm.validateField('checkPass');
          }
          callback();
        }
      };
      return {
        user: '',
        ruleForm: {
          username: '',
          password: '',
          msg: ''
        },

      };
    },
    methods: {
      submitForm(ruleForm) {
        this.$refs['ruleForm'].validate((valid) => {
          if (valid) {
            axios
              .post('http://localhost:8088/api/user/login', ruleForm)
              .then(response => {
                this.msg = response.data;
                if (this.msg === 2) {
                  alert('您的账户已被禁用，请解禁后再登录！');
                  return false;
                } else if (this.msg === -1) {
                  alert('用户名或密码错误，登录失败请重新登录！');
                  return false;
                } else if (this.msg === 0) {
                  this.$router.push({name: "UserBooks", params: {"user": ruleForm.username}});
                } else if (this.msg === 1) {
                  this.$router.push({name: "ManageBooks", params: {"user": ruleForm.username}});
                }
              });
          } else {
            alert('表单信息不正确，请重新填写！');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
