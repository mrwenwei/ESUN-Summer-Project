// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
Vue.use(VueAxios, axios)
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';

import Vuex from 'vuex'
Vue.use(Vuex)
import {ServerTable, ClientTable, Event} from 'vue-tables-2';
Vue.use(ClientTable); //Client table
Vue.use(ServerTable); //Server table
window.Event = Event;
Vue.use(Event);
import store from './store'
import IdleVue from 'idle-vue'
Vue.config.productionTip = false

//jquery
window.$ = window.jQuery = require('jquery');
//moment.js
window.moment = require('moment');
window.bigdecimal = require("bigdecimal");
const eventsHub = new Vue()
 
Vue.use(IdleVue, {
  eventEmitter: eventsHub,
  // 閒置時間（單位：毫秒）
  idleTime: 1000*60*1000
})

const token = localStorage.getItem('token')
if (token) {
  Vue.prototype.$http.defaults.headers.common['Authorization'] = token
}

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
