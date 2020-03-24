import Vue from 'vue';
import Router from 'vue-router';
import Home from '../components/Home.vue';
import Register from '../components/Register.vue';
import UserBooks from '../components/UserBooks.vue';
import UserCart from '../components/UserCart.vue';
import UserOrder from '../components/UserOrder.vue';
import ManageBooks from '../components/ManageBooks.vue';
import ManageOrders from '../components/ManageOrders.vue';
import Statistics from '../components/Statistics.vue';
import Hello from '../components/HelloWorld.vue';
import ManageUsers from "../components/ManageUsers";
import ChatRoom from "../components/ChatRoom";

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [{
    path: '*',
    redirect: '/home'
  }, {
    path: '/hello',
    name: 'Hello',
    component: Hello
  }, {
    path: '/home',
    name: 'Home',
    component: Home
  }, {
    path: '/register',
    name: 'Register',
    component: Register
  }, {
    path: '/userbooks',
    name: 'UserBooks',
    component: UserBooks
  }, {
    path: '/usercart',
    name: 'UserCart',
    component: UserCart
  }, {
    path: '/userorder',
    name: 'UserOrder',
    component: UserOrder
  }, {
    path: '/manageusers',
    name: 'ManageUsers',
    component: ManageUsers
  }, {
    path: '/managebooks',
    name: 'ManageBooks',
    component: ManageBooks
  }, {
    path: '/manageorder',
    name: 'ManageOrders',
    component: ManageOrders
  }, {
    path: '/statistics',
    name: 'Statistics',
    component: Statistics
  }, {
    path: '/chatRoom',
    name: 'ChatRoom',
    component: ChatRoom
  }]
});
