<template>
  <!-- Button -->
  <div class="container-fluid h-100">
    <div class="row h-100 no-glutters">
      <div class="col" style="padding:0;">
        <form v-on:submit.prevent="goto_deposit" style="width:100%;height:100%">
          <button type="submit" class="btn btn-warning text-light" style="width:100%;height:100%">存款</button>
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


