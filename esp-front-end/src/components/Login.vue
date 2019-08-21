
<template>
  <!-- nav bar -->
  <div class="h-100">
    <nav class="navbar navbar-expand-lg navbar-light bg-info">
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarTogglerDemo01"
        aria-conztrols="navbarTogglerDemo01"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand text-light" href="#">電子表單系統</a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0"></ul>
      </div>
    </nav>
    <div class="container-fluid" style=" height:80%">
      <!-- login form -->
      <div class="row h-100 no-glutters justify-content-center">
        <div class="col-md-4 align-self-center">
          <form v-on:submit.prevent="login">
            <!-- 帳號 -->
            <div class="form-group">
              <label for="formGroupExampleInput">帳號</label>
              <input
                type="text"
                v-model="user.id"
                required
                class="form-control"
                id="formGroupExampleInput"
                placeholder="account"
              />
            </div>
            <!-- 密碼 -->
            <div class="form-group">
              <label for="formGroupExampleInput2">密碼</label>
              <input
                type="test"
                v-model="user.password"
                required
                class="form-control"
                id="formGroupExampleInput2"
                placeholder="password"
              />
            </div>
            <!-- 登入按鈕 -->
            <button type="submit" class="btn btn-primary">登入</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: {
        id: "",
        password: ""
      }
    };
  },
  methods: {
    login: function() {
      // let uri = "/api/authenticate/" + this.user.id;
      // this.axios.post(uri, this.user).then(response => {
      //   if (response.data.role == "customer") {
      //     this.$router.push("/Home");
      //   }
      // });
      let id = this.user.id;
      let password = this.user.password;
      this.$store
        .dispatch("login", { id, password })
        // .then(() => this.$router.push("/home_customer"))
        .then(() => this.$router.push("/home_" + this.$store.getters.authToken))

        .catch(err => console.log(err));
    }
  }
};
</script>


