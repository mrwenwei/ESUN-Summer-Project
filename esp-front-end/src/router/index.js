import Vue from 'vue'
import VueRouter from 'vue-router'

import login from '@/components/Login'
import home_customer from '@/components/customer/home_customer'
import deposit_customer from '@/components/customer/deposit_customer'
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


    },
    {
      path: '/home_customer',
      name: 'home_customer',
      component: home_customer,
      meta: {
        requiresAuth: true,
        allowAuth: 'customer'

      }
    },
    {
      path: '/deposit_customer',
      name: 'deposit_customer',
      component: deposit_customer,
      meta: {
        requiresAuth: true,
        allowAuth: 'customer'

      }
    }
  ]
})


// router.beforeEach((to, from, next) => {
//   if(to.matched.some(record => record.meta.requiresAuth)) {
//     if (store.getters.isLoggedIn) {
//       next()
//       return
//     }
//     next('/') 
//   } else {
//     next() 
//   }
// })

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (store.getters.isLoggedIn) {
      // console.log("your token:" + store.getters.authToken)

      if (to.matched.some(record => record.meta.allowAuth)) {


        if (store.getters.authToken == to.meta.allowAuth) {
          next()
          return
        } else {
          console.log("you are not customer")
          next('/')
        }
      }

    } else {
      console.log("you are not login")
      next('/')
    }

  } else {
    next()
  }
})



export default router

