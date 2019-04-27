<template>
<div class="register">
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
                    <p class="headline">用户注册</p>
                      <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="90px" class="demo-ruleForm">
                        <el-form-item label="用户名" prop="name">
                          <el-input v-model="ruleForm.name"></el-input>
                        </el-form-item>
                        <el-form-item label="密码" prop="pass">
                          <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="确认密码" prop="checkPass">
                          <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
                        </el-form-item>
                        <el-form-item label="邮箱" prop="email">
                          <el-input type="email" v-model="ruleForm.email"></el-input>
                        </el-form-item>
                        <el-form-item align="center">
                          <el-button type="primary" @click="submitForm(ruleForm)">提交</el-button>
                          <el-button @click="resetForm('ruleForm')">重置</el-button>
                        </el-form-item>
                      </el-form>
                    <p class="cstyle">已有账号？<a href="/home">立即登录</a></p>
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
      let checkEmail = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入邮箱地址'));
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
      let validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.pass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        username: '',
        ruleForm: {
          name: '',
          pass: '',
          checkPass: '',
          email: '',
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
          checkPass: [
            { validator: validatePass2, trigger: 'blur' },
            { required: true }
          ],
          email: [
            { validator: checkEmail, trigger: 'blur' },
            { required: true },
            { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
          ]
        }
      };
    },
    methods: {
      submitForm(ruleForm) {
        this.$refs['ruleForm'].validate((valid) => {
          if (valid) {
            axios
              .post('http://localhost:8088/api/register', ruleForm)
              .then(response => {
                this.msg = response.data;
                if (this.msg === 0) {
                  alert('用户名重复，请重新注册！');
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
