<template>
  <!-- Button -->
  <div class="container-fluid h-100">
    <div class="row h-50 no-glutters">
      <div class="col" style="padding:0;">
        <form v-on:submit.prevent="goto_deposit" style="width:100%;height:100%">
          <button
            type="submit"
            class="btn btn-warning text-light"
            style="width:100%;height:100%"
          >存款辦理</button>
        </form>
      </div>
      <div class="col" style="padding:0;">
        <form v-on:submit.prevent="goto_withdraw" style="width:100%;height:100%">
          <button
            type="submit"
            class="btn btn-success text-light"
            style="width:100%;height:100%"
          >取款辦理</button>
        </form>
      </div>
    </div>

    <div class="row h-50 no-glutters">
      <div class="col" style="padding:0;">
        <form v-on:submit.prevent="goto_transfer" style="width:100%;height:100%">
          <button
            type="submit"
            class="btn btn-danger text-light"
            style="width:100%;height:100%"
          >匯款辦理</button>
        </form>
      </div>

      <div class="col" style="padding:0;">
        <form v-on:submit.prevent="goto_inquire" style="width:100%;height:100%">
          <button
            type="submit"
            class="btn btn-primary text-light"
            style="width:100%;height:100%"
          >交易紀錄</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    goto_deposit() {
      this.$router.push({ name: "deposit_teller" });
    },
    goto_withdraw() {
      this.$router.push({ name: "withdraw_teller" });
    },
    goto_transfer() {
      this.$router.push({ name: "transfer_teller" });
    },
    goto_inquire() {
      this.$router.push({ name: "inquire_teller" });
    }
  }
};
</script>


