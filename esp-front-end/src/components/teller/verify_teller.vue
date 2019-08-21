<template>
  <div class="container-fluid" style="height:90%;">
    <div class="row no-glutters" style="height:80%;">
      <div class="col-10">
        <!-- 單據顯示 -->
        <div class="row no-glutters" style=" height:55%;">
          <div class="col-12">
            <formTeller @infoPresent="get_infoPresent_data" @docIDReceive="get_docID"></formTeller>
          </div>
        </div>

        <!-- 背面資料 -->
        <div class="row no-glutters" style=" height:45%;">
          <div class="col-12">
            <backData @question_updated="get_questionForm_data"></backData>
          </div>
        </div>
      </div>
      <!-- 現金明細 -->
      <div class="col-2">
        <p>
          <br />
        </p>
        <cashDetail @cash_updated="get_cashForm_data"></cashDetail>
      </div>
    </div>
    <!-- 按鈕 -->
    <div class="row no-glutters" style="height:10%;">
      <div class="col">
        <button
          type="button"
          class="btn btn-info btn-lg btn-block"
          @click="toggle_finished"
        >{{button_content}}</button>
      </div>
    </div>
  </div>
</template>

<script>
import cashDetail from "./cash_detail";
import backData from "./back_data";
import formTeller from "./form_teller";
export default {
  data() {
    return {
      docId: "",
      transact_data: {},
      button_content: "",
      cashForm: {},
      questionForm: {}
    };
  },
  created() {
    window.addEventListener('beforeunload', this.handler)
    
  },
  beforeDestroy() {
    window.removeEventListener('beforeunload', this.handler)
  },
  methods: {
    // Detect close the tab or reload.
    handler: function handler(event) {
      this.transact_data.finishedCondition = 0;
      this.axios
        .put("api/PUT/transaction/" + this.docId, this.transact_data)
        .then(res => {
          this.transact_data = res.data;
        }).catch(err => console.log(err));
    },
    toggle_finished() {
      var mes = this.transact_data.finished
        ? "您確定要取消此筆交易之辦理嗎？"
        : "您確定要辦理此筆交易嗎？";
      if (confirm(mes)) {
        this.transact_data.finished = !this.transact_data.finished;
        if (this.transact_data.finished) {
          this.transact_data.broker = this.$store.getters.getUser;
          this.transact_data.finishedTime = moment();
        }
        else {
          this.transact_data.broker = null;
          this.transact_data.finishedTime = null;
        }
        this.transact_data.cashDetail = JSON.stringify(this.cashForm);
        this.transact_data.backData = JSON.stringify(this.questionForm);
        
        this.axios
          .put("api/PUT/transaction/" + this.docId, this.transact_data)
          .then(res => {
            this.transact_data = res.data;
          });

        this.$router.push("inquire_teller");
      }
    },
    get_infoPresent_data(value) {
      // after created
      this.transact_data = value.transact_data;
      if (this.transact_data.finished) {
        this.button_content = "取消此筆交易之辦理";
      } else {
        this.button_content = "辦理此筆交易";
      }
      // set the status to verifying
      this.transact_data.finishedCondition = 1
      this.axios
      .put("api/PUT/transaction/" + this.docId, this.transact_data)
      .then(put_res => {
      });
    },
    get_docID(value) {
      this.docId = value.docId;
    },
    get_cashForm_data(value) {
      this.cashForm = value;
    },
    get_questionForm_data(value) {
      this.questionForm = value;
    }
  },
  beforeRouteLeave(to, from, next) {
    this.transact_data.finishedCondition = 0;
    this.axios
      .put("api/PUT/transaction/" + this.docId, this.transact_data)
      .then(res => {
        this.transact_data = res.data;
        next();
      });
  },
  components: {
    formTeller,
    cashDetail,
    backData
  }
};
</script>

