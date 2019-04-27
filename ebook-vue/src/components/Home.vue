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
              <router-link :to="{name:'UserBooks',params:{username: this.username}}" >书籍</router-link>
              <router-link :to="{name:'UserCart',params:{username: this.username}}" >购物车</router-link>
              <router-link :to="{name:'UserOrder',params:{username: this.username}}" >我的订单</router-link>
              <a href="/manage">管理员入口</a>
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
                        <el-form-item label="用户名" prop="name">
                          <el-input v-model="ruleForm.name"></el-input>
                        </el-form-item>
                        <el-form-item label="密码" prop="pass">
                          <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
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
        username: 'test',
        ruleForm: {
          name: '',
          pass: '',
          msg: ''
        },
        rules: {
          name: [
            { validator: checkName, trigger: 'blur' },
            { required: true }
          ],
          pass: [
            { validator: validatePass, trigger: 'blur' },
            { required: true }
          ],
        }
      };
    },
    methods: {
      submitForm(ruleForm) {
        this.$refs['ruleForm'].validate((valid) => {
          if (valid) {
            axios
              .post('http://localhost:8088/api/login', ruleForm)
              .then(response => {
                this.msg = response.data;
                if (this.msg === 0) {
                  alert('用户名或密码错误，登录失败请重新登录！');
                  return false;
                } else {
                  this.$router.push({name: "UserBooks", params: {"user": ruleForm.name}});
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
