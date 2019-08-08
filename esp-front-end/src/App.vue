<template>
  <div id="app">
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
            <a v-if="isLoggedIn" class="nav-link" href="#" @click="logout">Logout</a>
          </li>
        </ul>
      </div>
    </nav>
    <router-view/>
  </div>
</template>



<script>
export default {
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
      if (confirm("您確定要登出嗎？")) {
        this.$store.dispatch("logout");
        this.$router.push("/");
      } else {
        console.log("沒事按到ㄅ歉");
      }
    }
  },

  onIdle() {
    alert("閒置過久，請重新登入");
    this.$store.dispatch("logout");
    this.$router.push("/");
    console.log("閒置過久，已登出");
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
