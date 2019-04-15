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
                <a href="/userbooks">书籍</a>
                <a href="/usercart">购物车</a>
                <a href="/userorder">我的订单</a>
                <a href="/manage">管理员入口</a>
            </div>
            <div id="search">
                <span>Search</span>
                <input type="text" />
                <input type="submit" />
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
                        <form action="#" method="get">
                            <p class="headline">用户登录</p>
                            <p class="astyle">用户名：</p>
                            <input type="text" value="">
                            <p class="astyle">密码：</p>
                            <input type="password" value="">
                            <input type="submit" value="登陆" name="login" >
                            <br>
                            <p class="bstyle"><input type="checkbox" name="rempas" />记住密码</p>
                            <p class="cstyle"><a href="#">忘记密码？</a></p>
                            <br><br>
                            <p class="cstyle">没有账号？<a href="/register">立即注册</a></p>
                        </form>
                    </div>
                </div>
                </div>
            </div>
        </div>
  </div>
</template>

<script>

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
