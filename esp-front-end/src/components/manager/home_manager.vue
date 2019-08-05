<template>
  <!-- Button -->
  <div class="container-fluid h-100">
    <div class="row h-100 no-glutters">
      <div class="col" style="padding:0;">
        <form v-on:submit.prevent="goto_inquire" style="width:100%;height:100%">
          <button type="submit" class="btn btn-warning text-light" style="width:100%;height:100%">查詢</button>
        </form>
      </div>
      <div class="col" style="padding:0;">
        <form v-on:submit.prevent="goto_verify" style="width:100%;height:100%">
            <button type="submit" class="btn btn-success text-light" style="width:100%;height:100%">審核</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    goto_inquire() {
      this.$router.push({ name: "inquire_manager" });
    },
    goto_verify() {
      this.$router.push({ name: "verify_manager" });
    }
  }
};
</script>


