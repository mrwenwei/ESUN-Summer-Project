<template>
  <!-- Button -->
  <div class="container-fluid" style="height:90%">
    <div class="row justify-content-center" style="height:20%;"></div>
    <div class="row justify-content-center" style="height:40%;">
      <div class="col-3">
        <form v-on:submit.prevent="goto_deposit" style="width:100%;height:100%;">
          <button
            type="submit"
            class="btn btn-info text-light;"
            style="width:100%;height:100%;font-size:xx-large;"
          >存款</button>
        </form>
      </div>
      <div class="col-3">
        <form v-on:submit.prevent="goto_withdraw" style="width:100%;height:100%">
          <button
            type="submit"
            class="btn btn-info text-light;"
            style="width:100%;height:100%;font-size:xx-large;"
          >取款</button>
        </form>
      </div>
      <div class="col-3">
        <form v-on:submit.prevent="goto_transfer" style="width:100%;height:100%">
          <button
            type="submit"
            class="btn btn-info text-light;"
            style="width:100%;height:100%;font-size:xx-large;"
          >匯款</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    goto_deposit() {
      this.$router.push({ name: "deposit_customer" });
    },
    goto_withdraw() {
      this.$router.push({ name: "withdraw_customer" });
    },
    goto_transfer() {
      this.$router.push({ name: "transfer_customer" });
    }
  }
};
</script>
<style>
.btn-info{
  background-color:#00AD95;
}
</style>

