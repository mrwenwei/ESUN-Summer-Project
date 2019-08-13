<template>
  <form>
    <div class="form-row" style="border:1px solid">
      <label class="col-md-12 col-form-label col-form-label-sm text-center" style="border:1px solid;">銀行作業勾選欄位</label>
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
    </div>

    <!-- 第二大題 -->
    <div class="form-row">
      <label
        for="question2"
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >2.申請人是否認識存入帳戶的受款人</label>
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
      <label
        for="question3"
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >如是，與受款人關係為</label>
      <div class="col-md-2" style="border:1px solid">
        <input
          type="text"
          class="form-control form-control-sm"
          id="question3"
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
      <label
        for="question7"
        class="col-md-4 col-form-label col-form-label-sm"
        style="border:1px solid;"
      >如是，請續答，家人是否知悉此筆交易</label>
      <div class="col-md-2" style="border:1px solid">
        <select
          class="form-control form-control-sm"
          id="question7"
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
    </div>
  </form>
</template>

<script>
export default {
  data() {
    return {
      question1: "",
      question2: "",
      question3: "",
      question4: "",
      question5: "",
      question6: "",
      question7: "",
      question8: ""
    };
  }
};
</script>