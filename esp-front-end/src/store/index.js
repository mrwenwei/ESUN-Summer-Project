import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.use(Vuex)
Vue.use(VueAxios, axios)

export default new Vuex.Store({
  state: {
    status: '',
    token: localStorage.getItem('token') || '',
    userAccount:localStorage.getItem('userAccount') || '',
    doc:'',
  },
  mutations: {
    auth_request(state) {
      state.status = 'loading'
    },
    auth_success(state, token, userAccount) {
      state.status = 'success'
      state.token = token
      state.userAccount = userAccount
      console.log(state.userAccount)
    },
    auth_error(state) {
      state.status = 'error'
    },
    logout(state) {
      state.status = ''
      state.token = ''
    },
    edit(state, doc){
      state.doc = doc
    }, 
  },
  actions: {
    login({ commit }, user) {
      return new Promise((resolve, reject) => {
        commit('auth_request')

        axios({ url: '/api/POST/user/auth/' + user.id, data: user, method: 'POST' })
          .then(resp => {
            const token = resp.data.role
            const userAccount = resp.data.account
            localStorage.setItem('token', token)
            axios.defaults.headers.common['Authorization'] = token
            localStorage.setItem('userAccount', userAccount)
            console.log(userAccount)
            commit('auth_success', token, userAccount)
            resolve(resp)
          })
          .catch(err => {
            commit('auth_error')
            localStorage.removeItem('token')
            reject(err)
          })
      })
    },
    logout({ commit }) {
      return new Promise((resolve, reject) => {
        commit('logout')
        localStorage.removeItem('token')
        delete axios.defaults.headers.common['Authorization']
        resolve()
      })
    },
    edit({commit}, doc){
      return new Promise((resolve, reject) => {
        // localStorage.setItem('doc', doc)
        commit('edit', doc)
        resolve()
      })
    }
  },
  getters: {
    isLoggedIn: state => !!state.token,
    authStatus: state => state.status,
    authToken: state => state.token,
    editedDoc: state => state.doc,
    getUser: state => state.userAccount
  }
})