<template>
  <form>
    <div class="form-row" style="border:1px solid">
      <label
        class="col-md-12 col-form-label col-form-label-sm text-center"
        style="border:1px solid;"
      >銀行作業勾選欄位</label>
    </div>
    <!-- 第一大題 -->
    <div class="form-row">
      <label
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >1.申請人是否接到陌生者、檢調單位等電話指示</label>
      <div class="col-md-2" style="border:1px solid">
        <span>{{backData.question1}}</span>
      </div>

      <div class="col-4" style="border:1px solid"></div>
      <div class="col-2" style="border:1px solid"></div>
    </div>
    <!-- 第二大題 -->
    <div class="form-row">
      <label
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >2.申請人是否認識存入帳戶的受款人</label>
      <div class="col-md-2" style="border:1px solid">
        <span>{{backData.question2}}</span>
      </div>
      <div class="col-md-4" style="border:1px solid">
        <label
          class="col-form-label col-form-label-sm"
          v-if='backData.question2==="是"'
        >如是，與受款人關係為</label>
      </div>
      <div class="col-md-2" style="border:1px solid">
        <span v-if='backData.question2==="是"'>{{backData.question3}}</span>
      </div>
    </div>

    <!-- 第三大題 -->
    <div class="form-row">
      <label
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >3.申請人存入款項的目的</label>
      <div class="col-md-2" style="border:1px solid">
        <span>{{backData.question4}}</span>
      </div>
      <label
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >此目的是否正常</label>
      <div class="col-md-2" style="border:1px solid">
        <span >{{backData.question5}}</span>
      </div>
    </div>

    <!-- 第四大題 -->
    <div class="form-row">
      <label
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >4.金額50萬以上或申請人為70歲以上者</label>
      <div class="col-md-2" style="border:1px solid">
        <span >{{backData.question6}}</span>
      </div>
      <div class="col-md-4" style="border:1px solid">
      <label
        class="col-form-label col-form-label-sm"
        v-if='backData.question6==="是"'
      >如是，請續答，家人是否知悉此筆交易</label>
      </div>
      <div class="col-md-2" style="border:1px solid">
        <span v-if='backData.question6==="是"'>{{backData.question7}}</span>
      </div>
    </div>

    <!-- 第五六大題 -->
    <div class="form-row">
      <label
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >5.顧客是否拒絕回答</label>
      <div class="col-md-2" style="border:1px solid">
        <span >{{backData.question8}}</span>
      </div>
      <label
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >經判斷無詐騙之虞</label>
      <div class="col-md-2" style="border:1px solid">
        <span >{{backData.question9}}</span>
      </div>
    </div>
  </form>
</template>

<script>
export default {
  data() {
    return {
      transact_data: {},
      backData: {}
    };
  },
  created() {
    this.docId = this.$store.getters.editedDoc;
    this.axios.get("api/GET/transaction/" + this.docId).then(res => {
      this.transact_data = res.data;
      console.log(this.transact_data);

      this.backData = JSON.parse(res.data.backData);
      console.log("qq" + this.backData);
    });
  }
};
</script>