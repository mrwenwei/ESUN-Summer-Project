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
        for="question1"
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >1.申請人是否接到陌生者、檢調單位等電話指示</label>
      <div class="col-md-2" style="border:1px solid">
        <select
          class="form-control form-control-sm"
          id="question1"
          v-model="question1"
          @change="send_to_parent"
          required
        >
          <option value>請選擇</option>
          <option value="是">是</option>
          <option value="否">否</option>
        </select>
      </div>

      <div class="col-4" style="border:1px solid"></div>
      <div class="col-2" style="border:1px solid"></div>
    </div>

    <!-- 第二大題 -->
    <div class="form-row">
      <label
        for="question2"
        class="col-md-4 col-form-label col-form-label-sm"
        v-if="this.transact_data.type=='存款' || this.transact_data.type=='匯款'"
        style="border:1px solid;"
      >2.申請人是否認識存入帳戶的受款人</label>

      <label
        for="question2"
        class="col-md-4 col-form-label col-form-label-sm"
        v-if="this.transact_data.type=='取款'"
        style="border:1px solid;"
      >2.申請人是否認識陪同提款者(有陪同提款者時詢問)</label>

      <div class="col-md-2" style="border:1px solid">
        <select
          class="form-control form-control-sm"
          id="question2"
          v-model="question2"
          @change="send_to_parent"
          required
        >
          <option value>請選擇</option>
          <option value="是">是</option>
          <option value="否">否</option>
        </select>
      </div>
      <!-- 第二題 追問 -->
      <div class="col-md-4" style="border:1px solid">
        <label
          for="question3"
          class="col-form-label col-form-label-sm"
          v-if="question2=='是'"
        >如是，與受款人關係為</label>
      </div>
      <div class="col-md-2" style="border:1px solid">
        <input
          type="text"
          class="form-control form-control-sm"
          id="question3"
          v-if="question2=='是'"
          placeholder="請輸入"
          v-model="question3"
          @input="send_to_parent"
          required
        />
      </div>
    </div>

    <!-- 第三大題 -->
    <div class="form-row">
      <label
        for="question4"
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >3.申請人存入款項的目的</label>
      <div class="col-md-2" style="border:1px solid">
        <input
          type="text"
          class="form-control form-control-sm"
          id="question4"
          placeholder="請輸入"
          v-model="question4"
          @input="send_to_parent"
          required
        />
      </div>
      <!-- 第三大題 追問 -->
      <label
        for="question5"
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >此目的是否正常</label>
      <div class="col-md-2" style="border:1px solid">
        <select
          class="form-control form-control-sm"
          id="question5"
          v-model="question5"
          @change="send_to_parent"
          required
        >
          <option value>請選擇</option>
          <option value="是">是</option>
          <option value="否">否</option>
        </select>
      </div>
    </div>

    <!-- 第四大題 -->
    <div class="form-row">
      <label
        for="question6"
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >4.金額50萬以上或申請人為70歲以上者</label>
      <div class="col-md-2" style="border:1px solid">
        <select
          class="form-control form-control-sm"
          id="question6"
          v-model="question6"
          @change="send_to_parent"
          required
        >
          <option value>請選擇</option>
          <option value="是">是</option>
          <option value="否">否</option>
        </select>
      </div>
      <!-- 第四大題 追問 -->
      <div class="col-md-4" style="border:1px solid">
        <label
          for="question7"
          class="col-form-label col-form-label-sm"
          v-if="question6=='是'"
        >如是，請續答，家人是否知悉此筆交易</label>
      </div>
      <div class="col-md-2" style="border:1px solid">
        <select
          class="form-control form-control-sm"
          id="question7"
          v-if="question6=='是'"
          v-model="question7"
          @change="send_to_parent"
          required
        >
          <option value>請選擇</option>
          <option value="是">是</option>
          <option value="否">否</option>
        </select>
      </div>
    </div>

    <!-- 第五大題 -->
    <div class="form-row">
      <label
        for="question8"
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >5.顧客是否拒絕回答</label>
      <div class="col-md-2" style="border:1px solid">
        <select
          class="form-control form-control-sm"
          id="question8"
          v-model="question8"
          @change="send_to_parent"
          required
        >
          <option value>請選擇</option>
          <option value="是">是</option>
          <option value="否">否</option>
        </select>
      </div>
      <!-- 第六大題 -->
      <label
        for="question9"
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >經判斷無詐騙之虞</label>
      <div class="col-md-2" style="border:1px solid">
        <select
          class="form-control form-control-sm"
          id="question9"
          v-model="question9"
          @change="send_to_parent"
          required
        >
          <option value>請選擇</option>
          <option value="是">是</option>
          <option value="否">否</option>
        </select>
      </div>
    </div>
  </form>
</template>

<script>
export default {
  data() {
    return {
      transact_data: "",
      question1: "",
      question2: "",
      question3: "",
      question4: "",
      question5: "",
      question6: "",
      question7: "",
      question8: "",
      question9: ""
    };
  },
  methods: {
    send_to_parent() {
      this.$emit("question_updated", {
        question1: this.question1,
        question2: this.question2,
        question3: this.question3,
        question4: this.question4,
        question5: this.question5,
        question6: this.question6,
        question7: this.question7,
        question8: this.question8,
        question9: this.question9
      });
    }
  },
  created() {
    this.docId = this.$store.getters.editedDoc;
    this.axios.get("api/GET/transaction/" + this.docId).then(res => {
      this.transact_data = res.data;
      this.backData = JSON.parse(res.data.backData);
      this.question1 = this.backData.question1;
      this.question2 = this.backData.question2;
      this.question3 = this.backData.question3;
      this.question4 = this.backData.question4;
      this.question5 = this.backData.question5;
      this.question6 = this.backData.question6;
      this.question7 = this.backData.question7;
      this.question8 = this.backData.question8;
      this.question9 = this.backData.question9;
    });
  }
};
</script>