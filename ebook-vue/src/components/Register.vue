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
                <a href="/userbooks">书籍</a>
                <a href="/usercart">购物车</a>
                <a href="/userorder">我的订单</a>
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
                        <form action="" method="post" >
                            <p class="headline">用户注册</p>
                            <p class="astyle">用户名：</p>
                            <input type="text" value="" id="name">
                            <p class="astyle">密码：</p>
                            <input type="password" value="">
                            <p class="astyle">确认密码：</p>
                            <input type="password" value="">
                            <p class="astyle">邮箱：</p>
                            <input type="email" value="" id="email">
                            <input type="submit" value="注册" name="signup" >
                        </form>
                    </div>
                </div>
                </div>
            </div>
        </div>
</div>
</template>
