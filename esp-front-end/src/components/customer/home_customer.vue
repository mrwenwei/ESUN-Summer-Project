<template>
  <!-- Button -->
  <div id="home_customer" class="container-fluid h-100">
    <div class="row  justify-content-center" style="height:20%;">
    </div>
    <div class="row justify-content-center" style="height:40%;">
     
      <div class="col-3 " >
        <form v-on:submit.prevent="goto_deposit" style="width:100%;height:100%">
          <button type="submit" class="btn btn-info text-light;" style="width:100%;height:100%;font-size:xx-large;">存款</button>
        </form>
      </div>
      <div class="col-3" >
          <button type="submit" class="btn btn-info text-light;" style="width:100%;height:100%;font-size:xx-large;">取款</button>
      </div>
      <div class="col-3" >
          <button type="submit" class="btn btn-info text-light;" style="width:100%;height:100%;font-size:xx-large;">匯款</button>
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


