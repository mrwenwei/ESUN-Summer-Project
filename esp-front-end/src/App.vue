<template>
  <div id="app" >
    <!-- nav bar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-info">
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarTogglerDemo01"
        aria-controls="navbarTogglerDemo01"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <span class="navbar-brand text-light">電子表單系統</span>

        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <a v-if="isLoggedIn" class="nav-link" href="/" @click="logout">Logout</a>
          </li>
        </ul>
      </div>
    </nav>
    <router-view />
  </div>
</template>



<script>
export default {
  // data() {
  //   return {
  //     currentTime: new Date().getTime() 
  //   };
  // },
  computed: {
    isLoggedIn: function() {
      return this.$store.getters.isLoggedIn;
    }
  },
  created: function() {
    this.$http.interceptors.response.use(undefined, function(err) {
      return new Promise(function(resolve, reject) {
        if (err.status === 401 && err.config && !err.config.__isRetryRequest) {
          this.$store.dispatch(logout);
        }
        throw err;
      });
    });
  },
  methods: {
    logout: function() {
      this.$store.dispatch("logout");
    },
    // OperatingWebsite() {
    //   let currentTime = this.currentTime;
    //   console.log(currentTime, "currentTime");
    //   let lastTime = new Date().getTime();
    //   console.log(lastTime, "lastTime");
    //   let timeOut = 0.1 * 60 * 1000; //設定時間 10分鐘
    //   if (lastTime - currentTime > timeOut) {
    //     // 未操作頁面，跳轉登入頁面
       
    //     this.$router.push("/");
    //     console.log("滾啦")
    //   } else {
    //     this.currentTime = new Date().getTime(); 
    //   }
    // }
  }
};
</script>


<style>
html,
body,
#app {
  height: 100%;
}
</style>
