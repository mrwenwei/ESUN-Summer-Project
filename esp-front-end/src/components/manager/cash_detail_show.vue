<template>
  <form>
    <div class="form-row" style="border:1px solid">
      <label
        class="col-md-12 col-form-label col-form-label-sm text-center"
        style="border:1px solid;"
      >現金明細</label>
    </div>
    <div class="form-row">
      <span class="col-md-6" style="border:1px solid;">$2000</span>
      <span class="col-md-6" style="border:1px solid;">{{cashDetail.cash2000}}</span>
    </div>
    <div class="form-row">
      <span class="col-md-6" style="border:1px solid;">$1000</span>
      <span class="col-md-6" style="border:1px solid;">{{cashDetail.cash1000}}</span>
    </div>
    <div class="form-row">
      <span class="col-md-6" style="border:1px solid;">$500</span>
      <span class="col-md-6" style="border:1px solid;">{{cashDetail.cash500}}</span>
    </div>
    <div class="form-row">
      <span class="col-md-6" style="border:1px solid;">$200</span>
      <span class="col-md-6" style="border:1px solid;">{{cashDetail.cash200}}</span>
    </div>
    <div class="form-row">
      <span class="col-md-6" style="border:1px solid;">$100</span>
      <span class="col-md-6" style="border:1px solid;">{{cashDetail.cash100}}</span>
    </div>
    <div class="form-row">
      <span class="col-md-6" style="border:1px solid;">$50</span>
      <span class="col-md-6" style="border:1px solid;">{{cashDetail.cash50}}</span>
    </div>
    <div class="form-row">
      <span class="col-md-6" style="border:1px solid;">$20</span>
      <span class="col-md-6" style="border:1px solid;">{{cashDetail.cash20}}</span>
    </div>
    <div class="form-row">
      <span class="col-md-6" style="border:1px solid;">$10</span>
      <span class="col-md-6" style="border:1px solid;">{{cashDetail.cash10}}</span>
    </div>
    <div class="form-row">
      <span class="col-md-6" style="border:1px solid;">$5</span>
      <span class="col-md-6" style="border:1px solid;">{{cashDetail.cash5}}</span>
    </div>
    <div class="form-row">
      <span class="col-md-6" style="border:1px solid;">$1</span>
      <span class="col-md-6" style="border:1px solid;">{{cashDetail.cash1}}</span>
    </div>
    <div class="form-row">
      <span class="col-md-6" style="border:1px solid;">合計</span>
      <span class="col-md-6" style="border:1px solid;">{{cashDetail.cashTotal}}</span>
    </div>
  </form>
</template>
<script>
export default {
  data() {
    return {
      transact_data: {},
      cashDetail: {}
    };
  },
  created() {
    this.docId = this.$store.getters.editedDoc;
    this.axios.get("api/GET/transaction/" + this.docId).then(res => {
      this.transact_data = res.data;
      this.cashDetail = JSON.parse(res.data.cashDetail);
    });
  }
};
</script>
