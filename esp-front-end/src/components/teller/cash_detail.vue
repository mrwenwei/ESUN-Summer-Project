<template>
  <form>
    <div class="form-row" style="border:1px solid">
      <label class="col-md-12 col-form-label col-form-label-sm text-center" style="border:1px solid;">現金明細</label>
    </div>
    <div class="form-row">
      <label
        for="cash2000"
        class="col-md-6 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >$2000</label>
      <div class="col-md-6" style="border:1px solid">
        <input
          type="text"
          class="form-control form-control-sm"
          id="cash2000"
          placeholder="0"
          v-model="cash2000"
          @input="send_to_parent"
          required
        />
      </div>
    </div>
    <div class="form-row">
      <label
        for="cash1000"
        class="col-md-6 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >$1000</label>
      <div class="col-md-6" style="border:1px solid">
        <input
          type="text"
          class="form-control form-control-sm"
          id="cash1000"
          placeholder="0"
          v-model="cash1000"
          @input="send_to_parent"
          required
        />
      </div>
    </div>
    <div class="form-row">
      <label
        for="cash500"
        class="col-md-6 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >$500</label>
      <div class="col-md-6" style="border:1px solid">
        <input
          type="text"
          class="form-control form-control-sm"
          id="cash500"
          placeholder="0"
          v-model="cash500"
          @input="send_to_parent"
          required
        />
      </div>
    </div>
    <div class="form-row">
      <label
        for="cash200"
        class="col-md-6 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >$200</label>
      <div class="col-md-6" style="border:1px solid">
        <input
          type="text"
          class="form-control form-control-sm"
          id="cash200"
          placeholder="0"
          v-model="cash200"
          @input="send_to_parent"
          required
        />
      </div>
    </div>
    <div class="form-row">
      <label
        for="cash100"
        class="col-md-6 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >$100</label>
      <div class="col-md-6" style="border:1px solid">
        <input
          type="text"
          class="form-control form-control-sm"
          id="cash100"
          placeholder="0"
          v-model="cash100"
          @input="send_to_parent"
          required
        />
      </div>
    </div>
    <div class="form-row">
      <label
        for="cash50"
        class="col-md-6 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >$50</label>
      <div class="col-md-6" style="border:1px solid">
        <input
          type="text"
          class="form-control form-control-sm"
          id="cash50"
          placeholder="0"
          v-model="cash50"
          @input="send_to_parent"
          required
        />
      </div>
    </div>
    <div class="form-row">
      <label
        for="cash20"
        class="col-md-6 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >$20</label>
      <div class="col-md-6" style="border:1px solid">
        <input
          type="text"
          class="form-control form-control-sm"
          id="cash20"
          placeholder="0"
          v-model="cash20"
          @input="send_to_parent"
          required
        />
      </div>
    </div>
    <div class="form-row">
      <label
        for="cash10"
        class="col-md-6 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >$10</label>
      <div class="col-md-6" style="border:1px solid">
        <input
          type="text"
          class="form-control form-control-sm"
          id="cash10"
          placeholder="0"
          v-model="cash10"
          @input="send_to_parent"
          required
        />
      </div>
    </div>
    <div class="form-row">
      <label
        for="cash5"
        class="col-md-6 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >$5</label>
      <div class="col-md-6" style="border:1px solid">
        <input
          type="text"
          class="form-control form-control-sm"
          id="cash5"
          placeholder="0"
          v-model="cash5"
          @input="send_to_parent"
          required
        />
      </div>
    </div>
    <div class="form-row">
      <label
        for="cash1"
        class="col-md-6 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >$1</label>
      <div class="col-md-6" style="border:1px solid">
        <input
          type="text"
          class="form-control form-control-sm"
          id="cash1"
          placeholder="0"
          v-model="cash1"
          @input="send_to_parent"
          required
        />
      </div>
    </div>
    <div class="form-row" style="border:1px solid">
      <label class="col-md-6 col-form-label col-form-label-sm" style="border:1px solid;">合計</label>
      <label
        class="col-md-6 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >{{cashTotal}}</label>
    </div>
  </form>
</template>
<script>
export default {
  data() {
    return {
      cash2000: "0",
      cash1000: "0",
      cash500: "0",
      cash200: "0",
      cash100: "0",
      cash50: "0",
      cash20: "0",
      cash10: "0",
      cash5: "0",
      cash1: "0",
      cashTotal: "0"
    };
  },
  methods: {
    send_to_parent() {
      this.countTotal();
      console.log("call countTotal()");
      this.$emit("cash_updated", {
        cash2000: this.cash2000,
        cash1000: this.cash1000,
        cash500: this.cash500,
        cash200: this.cash200,
        cash100: this.cash100,
        cash50: this.cash50,
        cash20: this.cash20,
        cash10: this.cash10,
        cash5: this.cash5,
        cash1: this.cash1,
        cashTotal: this.cashTotal
      });
    },
    countTotal() {
      this.cashTotal = (
        parseInt(this.cash2000) * 2000 +
        parseInt(this.cash1000) * 1000 +
        parseInt(this.cash500) * 500 +
        parseInt(this.cash200) * 200 +
        parseInt(this.cash100) * 100 +
        parseInt(this.cash50) * 50 +
        parseInt(this.cash20) * 20 +
        parseInt(this.cash10) * 10 +
        parseInt(this.cash5) * 5 +
        parseInt(this.cash1) * 1
      ).toString();
      console.log(this.cashTotal);
    }
  },
  created() {
    this.docId = this.$store.getters.editedDoc;
    this.axios.get("api/GET/transaction/" + this.docId).then(res => {
      this.transact_data = res.data;
      this.cashDetail = JSON.parse(res.data.cashDetail);
      this.cash2000 = this.cashDetail.cash2000
      this.cash1000 = this.cashDetail.cash1000
      this.cash500 = this.cashDetail.cash500
      this.cash200 = this.cashDetail.cash200
      this.cash100 = this.cashDetail.cash100
      this.cash50 = this.cashDetail.cash50
      this.cash20 = this.cashDetail.cash20
      this.cash10 = this.cashDetail.cash10
      this.cash5 = this.cashDetail.cash5
      this.cash1 = this.cashDetail.cash1
    });
  }
};
</script>
