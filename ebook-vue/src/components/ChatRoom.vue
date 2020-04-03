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
        <router-link :to="{name:'UserCart',params:{user: this.user}}" >购物车</router-link>
        <router-link :to="{name:'UserOrder',params:{user: this.user}}" >我的订单</router-link>
        <a class="now" href="#">聊天室</a>
      </div>
    </div>
    <div id="content">
      <div id="content_bottom">
        <el-card class="box-card" style="text-align: left;">
          <el-container>
            <el-container>
              <el-aside style="height: 475px; width: 200px; padding-top: 0px; padding-left: 0px; padding-right: 0px; padding-bottom: 0px; border: 1px solid #eee">
                <textarea readonly="true" style="resize: none; height: 462px; width: 190px;">{{ userList }}</textarea>
              </el-aside>
              <el-container>
                <el-main style="padding-top: 0px; padding-left: 0px; padding-right: 0px; padding-bottom: 0px; border: 1px solid #eee">
                  <textarea readonly="true" style="resize: none; height: 350px; width: 640px;">{{ content }}</textarea>
                </el-main>
                <el-footer style="padding-top: 0px; height: 110px; width: 650px; border: 1px solid #eee" align="right">
                  <el-input type="textarea" :rows="3" placeholder="请输入内容，不能超过300字符" v-model="textarea" maxlength="300" resize="none">
                  </el-input>
                  <el-button type="primary" size="small" @click="sendMessage()">发送</el-button>
                </el-footer>
              </el-container>
            </el-container>
          </el-container>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'ChatRoom',
    data: function () {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        tableData: Array(5).fill(item),
        user: "", // 用户名
        textarea: "", // 输入消息
        userList: "", // 聊天成员
        wsocket: null, // Websocket connection
        content: "", // 聊天内容
      };
    },
    mounted () {
      this.user = this.$route.params.user;
      if (this.user === '') {
        alert("请登录后进入聊天室！");
        this.$router.push({name: "Home"});
      }
      this.initWebsocket();
    },
    destroyed() {
      this.wsocket.close();
    },
    methods: {
      initWebsocket() {
        let url = "ws://localhost:8088/websocket";
        this.wsocket = new WebSocket(url);
        this.wsocket.onopen = this.onOpen;
        this.wsocket.onmessage = this.onMessage;
        this.wsocket.onerror = this.onError;
        this.wsocket.onclose = this.onClose;
      },
      onOpen() {
        let joinMsg = {};
        joinMsg.type = "join";
        joinMsg.name = this.user;
        let jsonstr = JSON.stringify(joinMsg);
        this.wsocket.send(jsonstr);
        window.console.log("connect to ws.");
      },
      onError() {
        window.console.log("connect error");
      },
      onMessage(evt) {
        let line = "";
        let msg = JSON.parse(evt.data);
        if (msg.type === "chat") {
          line = msg.name + ": ";
          if (msg.target.length > 0) {
            line += "@" + msg.target + " ";
          }
          line += msg.message + "\n";
          this.content += line;
        } else if (msg.type === "info") {
          line = "[--" + msg.info + "--]\n";
          this.content += line;
        } else if (msg.type === "users") {
          line = "Users:\n";
          for (let i=0; i < msg.userList.length; i++)
            line += "-" + msg.userList[i] + "\n";
          /* Update the user list area */
          this.userList = line;
        }
      },
      sendMessage() {
        if (this.textarea.length > 0) {
          let chatMsg = {};
          chatMsg.type = "chat";
          chatMsg.name = this.user;
          chatMsg.target = this.getTarget(this.textarea.replace(/,/g, ""));
          chatMsg.message = this.cleanTarget(this.textarea);
          chatMsg.message = chatMsg.message.replace(/(\r\n|\n|\r)/gm,"");
          this.wsocket.send(JSON.stringify(chatMsg));
          this.textarea = "";
        } else {
          alert("发送消息内容不能为空！");
        }
      },
      onClose(e) {
        window.console.log('connect close', e);
      },
      getTarget(str) {
        let arr = str.split(" ");
        let target = "";
        for (let i=0; i<arr.length; i++) {
          if (arr[i].charAt(0) === '@') {
            target = arr[i].substring(1,arr[i].length);
            target = target.replace(/(\r\n|\n|\r)/gm,"");
          }
        }
        return target;
      },
      cleanTarget(str) {
        let arr = str.split(" ");
        let cleanstr = "";
        for (let i=0; i<arr.length; i++) {
          if (arr[i].charAt(0) !== '@')
            cleanstr += arr[i] + " ";
        }
        return cleanstr.substring(0,cleanstr.length-1);
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

