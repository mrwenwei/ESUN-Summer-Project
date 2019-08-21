<template>
  <div class="h-100">
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
        <a class="navbar-brand text-light" href="#">電子表單系統</a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0"></ul>
      </div>
    </nav>
    <!-- Button -->
    <div class="container-fluid h-100">
      <div class="row h-100 no-glutters">
        <div class="col" style="padding:0;">
          <form v-on:submit.prevent="goto_deposit" style="width:100%;height:100%">
            <button
              type="submit"
              class="btn btn-warning text-light"
              style="width:100%;height:100%"
            >存款</button>
          </form>
        </div>
        <div class="col" style="padding:0;">
          <button type="submit" class="btn btn-success text-light" style="width:100%;height:100%">取款</button>
        </div>
        <div class="col" style="padding:0;">
          <button type="submit" class="btn btn-danger text-light" style="width:100%;height:100%">匯款</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    goto_deposit() {
      this.$router.push({ name: "deposit_customer" });
    }
  }
};
</script>


