<template>
  <div class="container-fluid" style="height:90%">
    <div class="row justify-content-center" style="height:20%;"></div>
    <div class="row justify-content-center" style="height:40%;">
      <div class="col-3">
        <form v-on:submit.prevent="goto_inquire" style="width:100%;height:100%">
          <button
            type="submit"
            class="btn btn-info text-light"
            style="width:100%;height:100%;font-size:xx-large;"
          >交易辦理</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  methods: {
    goto_inquire() {
      this.$router.push({ name: "inquire_teller" });
    }
  }
};
</script>
<style>
.btn-info{
  background-color:#00AD95;
}
</style>

