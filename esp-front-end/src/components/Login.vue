
<template>
  <div class="container-fluid" style=" height:80%">
    <!-- login form -->
    <div class="row h-100 no-glutters justify-content-center">
      <div class="col-md-4 align-self-center ">
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
</template>

<script>
export default {
  data() {
    return {
      user: {
        id: "",
        password: ""
      }
      // logInTime: new Date().getTime()
    };
  },
  methods: {
    login: function() {
      let id = this.user.id;
      let password = this.user.password;

      this.$store
        .dispatch("login", { id, password })
        .then(() => {
          if (this.$store.getters.authToken == "Wrong account") {
            alert("帳號錯誤");
            console.log(this.$store.getters.authToken);
            this.$store.dispatch("logout");
            this.$router.push("/");
          } else if (this.$store.getters.authToken == "Wrong password") {
            alert("密碼錯誤");
            console.log(this.$store.getters.authToken);
            this.$store.dispatch("logout");
            this.$router.push("/");
          } else {
            this.$router.push("/home_" + this.$store.getters.authToken);
            console.log(this.$store.getters.authToken);
            console.log(this.$store.getters.getUser);
          }
        })
        .catch(err => console.log(err));
    }
  }
};
</script>


