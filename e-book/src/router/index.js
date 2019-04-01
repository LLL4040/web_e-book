import Vue from 'vue';
import Router from 'vue-router';
import Home from '../components/Home.vue'
import Registe from '../components/Registe.vue'
import UserBooks from '../components/UserBooks.vue'
import UserCart from '../components/UserCart.vue'
import UserOrder from '../components/UserOrder.vue'
import Manage from '../components/Manage.vue'
import ManageBooks from '../components/ManageBooks.vue'
import ManageOrder from '../components/ManageOrder.vue'
import Statistics from '../components/Statistics.vue'

Vue.use(Router);

export default new Router({
  mode: 'history',
  routes: [{
    path: '*',
    redirect: '/home'
  }, {
    path: '/home',
    name: 'Home',
    component: Home
  }, {
    path: '/registe',
    name: 'Registe',
    component: Registe
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
    path: '/manage',
    name: 'Manage',
    component: Manage
  }, {
    path: '/managebooks',
    name: 'ManageBooks',
    component: ManageBooks
  }, {
    path: '/manageorder',
    name: 'ManageOrder',
    component: ManageOrder
  }, {
    path: '/statistics',
    name: 'Statistics',
    component: Statistics
  }]
});