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
    userName:localStorage.getItem('userName') || '',
    doc:localStorage.getItem('doc') || '',
    branchCode:localStorage.getItem('branchCode') || '',
  },
  mutations: {
    auth_request(state) {
      state.status = 'loading'
    },
    auth_success(state, payload) {
      state.status = 'success'
      state.token = payload.token
      state.userName = payload.userName
      state.branchCode = payload.branchCode
    },
    auth_error(state) {
      state.status = 'error'
    },
    logout(state) {
      state.status = ''
      state.token = ''
      state.userName = ''
      state.branchCode = ''
      state.doc = ''
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
            const userName = resp.data.name
            const branchCode = resp.data.branchCode
            console.log(resp.data.branchCode)
            localStorage.setItem('token', token)
            axios.defaults.headers.common['Authorization'] = token
            localStorage.setItem('userName', userName)
            localStorage.setItem('branchCode', branchCode)
            commit('auth_success', {'token':token, 'userName':userName, 'branchCode':branchCode})
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
        localStorage.removeItem('userName')
        localStorage.removeItem('doc')
        localStorage.removeItem('branchCode')
        delete axios.defaults.headers.common['Authorization']
        resolve()
      })
    },
    edit({commit}, doc){
      return new Promise((resolve) => {
        localStorage.setItem('doc', doc)
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
    getUser: state => state.userName,
    getBranchCode: state => state.branchCode
  }
})
