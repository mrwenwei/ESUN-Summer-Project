import Vue from 'vue'
import VueRouter from 'vue-router'

import login from '@/components/Login'
import home_customer from '@/components/customer/home_customer'
import deposit_customer from '@/components/customer/deposit_customer'
import transfer_customer from '@/components/customer/transfer_customer'
import withdraw_customer from '@/components/customer/withdraw_customer'
import home_manager from '@/components/manager/home_manager'
import inquire_manager from '@/components/manager/inquire_manager'
import verify_manager from '@/components/manager/verify_manager'
import verify_teller from '@/components/teller/verify_teller'
import home_teller from '@/components/teller/home_teller'
import inquire_teller from '@/components/teller/inquire_teller'

Vue.use(VueRouter)
import Vuex from 'vuex'
Vue.use(Vuex)
import store from '../store'

let router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'login',
      component: login,
      beforeEnter: (to, from, next) => {
        if(store.getters.isLoggedIn){
          next('home_'+store.getters.authToken)
        }else{
          next()
        }
      }
    },
    {
      path: '/home_customer',
      name: 'home_customer',
      component: home_customer,
      meta: {
        requiresAuth: true,
        allowAuth: 'customer'
      },
    },
    {
      path: 'deposit_customer',
      name: 'deposit_customer',
      component: deposit_customer,
      meta: {
        requiresAuth: true,
        allowAuth: 'customer'

      }
    },
    {
      path: '/home_manager',
      name: 'home_manager',
      component: home_manager,
      meta: {
        requiresAuth: true,
        allowAuth: 'manager'

      }
    },
    {
      path: '/home_teller',
      name: 'home_teller',
      component: home_teller,
      meta: {
        requiresAuth: true,
        allowAuth: 'teller'

      }
    },
    {
      path: '/transfer_customer',
      name: 'transfer_customer',
      component: transfer_customer,
      meta: {
        requiresAuth: true,
        allowAuth: 'customer'

      }
    },
    {
      path: '/withdraw_customer',
      name: 'withdraw_customer',
      component: withdraw_customer,
      meta: {
        requiresAuth: true,
        allowAuth: 'customer'

      }
    },
    {
      path: '/inquire_manager',
      name: 'inquire_manager',
      component: inquire_manager,
      meta: {
        requiresAuth: true,
        allowAuth: 'manager'

      }
    },
    {
      path: '/verify_manager',
      name: 'verify_manager',
      component: verify_manager,
      meta: {
        requiresAuth: true,
        allowAuth: 'manager'

      }
    },
    {
      path: '/verify_teller',
      name: 'verify_teller',
      component: verify_teller,
      meta: {
        requiresAuth: true,
        allowAuth: 'teller'

      }
    },
    {
      path: '/inquire_teller',
      name: 'inquire_teller',
      component: inquire_teller,
      meta: {
        requiresAuth: true,
        allowAuth: 'teller'

      }
    },
    
  ]
})




router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (store.getters.isLoggedIn) {

      if (to.matched.some(record => record.meta.allowAuth)) {


        if (store.getters.authToken == to.meta.allowAuth) {
          next()
          return
        } else {
          next('/')
        }
      }

    } else {
      next('/')
    }

  } else {
    next()
  }
})

export default router

