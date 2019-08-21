
<template>
  <div class="container-fluid" style=" height:90%">
    <!-- login form -->
    <div class="d-flex row h-100 no-glutters">
      <div class="col-md-12 align-self-center">
        <form v-on:submit.prevent="login">
          <!-- 帳號 -->
          <div class="d-flex justify-content-center form-row form-group">
            <div class="d-flex align-items-end flex-column col-md-1">
              <label style="font-size: x-large;">帳號</label>
            </div>
            <div class="col-md-3">
              <input
                type="text"
                v-model="user.id"
                required
                class="form-control"
                placeholder="account"
              />
            </div>
          </div>
          <!-- 密碼 -->
          <div class="d-flex justify-content-center form-row form-group">
            <div class="d-flex align-items-end flex-column  col-md-1">
              <label style="font-size: x-large;">密碼</label>
            </div>
            <div class="col-md-3">
              <input
                type="password"
                v-model="user.password"
                required
                class="form-control"
                placeholder="password"
              />
            </div>
          </div>
          <!-- 登入按鈕 -->
          <div class="d-flex justify-content-center form-row">
            <div class="col-3"></div>
            <div class="d-flex align-items-end flex-column col-1">
              <button type="submit" class="btn btn-primary">登入</button>
            </div>
          </div>
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
            // console.log(this.$store.getters.authToken);
            this.$store.dispatch("logout");
            this.$router.push("/");
          } else if (this.$store.getters.authToken == "Wrong password") {
            alert("密碼錯誤");
            // console.log(this.$store.getters.authToken);
            this.$store.dispatch("logout");
            this.$router.push("/");
          } else {
            this.$router.push("/home_" + this.$store.getters.authToken);
            this.$router.go()
            // console.log(this.$store.getters.authToken);
            // console.log(this.$store.getters.getUser);
          }
          
        })
        .catch(err => console.log(err));
    },
  }
};
</script>


