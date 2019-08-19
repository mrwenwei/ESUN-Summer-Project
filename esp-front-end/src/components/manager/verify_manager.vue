<template>
  <div class="container-fluid" style="height:100%;">
    <div class="row no-glutters" style="height:80%;">
      <div class="col-10">
        <!-- 單據顯示 -->
        <div class="row no-glutters" style=" height:55%;">
          <div class="col-12">
            <depositTeller @infoPresent="get_infoPresent_data" @docIDReceive="get_docID"></depositTeller>
            <!-- <depositTeller ></depositTeller> -->
          </div>
        </div>

        <!-- 背面資料 -->
        <div class="row no-glutters" style=" height:45%;">
          <div class="col-12">
            <backDataShow></backDataShow>
          </div>
        </div>
      </div>
      <!-- 現金明細 -->
      <div class="col-2">
        <p>
          <br />
        </p>
        <cashDetailShow></cashDetailShow>
        <!-- <cashDetailShow></cashDetailShow> -->
      </div>
    </div>
    <!-- 按鈕 -->
    <div class="row no-glutters" style="height:10%;">
      <div class="col">
        <button
          type="button"
          class="btn btn-outline-info btn-lg btn-block"
          @click="toggle_reviewed"
        >{{button_content}}</button>
      </div>
    </div>
  </div>
</template>

<script>
import cashDetailShow from "./cashDetailShow";
import backDataShow from "./backDataShow";
import depositTeller from "../teller/depositTeller";

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
      this.transact_data.reviewedCondition = 0;
      console.log(this.transact_data.reviewedCondition)
      this.axios
        .put("api/PUT/transaction/" + this.docId, this.transact_data)
        .then(res => {
          this.transact_data = res.data;
        }).catch(err => console.log(err));
    },
    toggle_reviewed() {
      // reviewed default=0, 0 for pass, 1 for cancel
      var mes = this.transact_data.reviewed
        ? "您確定要取消此筆交易之退件嗎？"
        : "您確定要退件此筆交易嗎？";
      if (confirm(mes)) {
        this.transact_data.reviewed = !this.transact_data.reviewed;
        // if (this.transact_data.reviewed) {
        //   this.transact_data.broker = this.$store.getters.getUser;
        //   this.transact_data.reviewedTime = moment();
        // }
        // else {
        //   this.transact_data.broker = null;
        //   this.transact_data.reviewedTime = null;
        // }
        // this.transact_data.cashDetail = JSON.stringify(this.cashForm);
        // this.transact_data.backData = JSON.stringify(this.backData);

        this.axios
          .put("api/PUT/transaction/" + this.docId, this.transact_data)
          .then(res => {
            this.transact_data = res.data;
          });
        this.$router.push("inquire_manager");
      }
    },
    get_infoPresent_data(value) {
      // after created
      this.transact_data = value.transact_data;
      if (this.transact_data.reviewed) {
        this.button_content = "取消此筆交易之退件";
      } else {
        this.button_content = "退件此筆交易";
      }
      // set the status to verifying
      this.transact_data.reviewedCondition = 1;
      this.axios
        .put("api/PUT/transaction/" + this.docId, this.transact_data)
        .then(put_res => {
        });
    },
    get_docID(value) {
      this.docId = value.docId;
    },
    // get_cashForm_data(value) {
    //   this.cashForm = value;
    // },
    // get_questionForm_data(value) {
    //   this.questionForm = value;
    // }
  },
  beforeRouteLeave(to, from, next) {
    this.transact_data.reviewedCondition = 0;
    this.axios
      .put("api/PUT/transaction/" + this.docId, this.transact_data)
      .then(res => {
        this.transact_data = res.data;
        next();
      });
  },
  components: {
    depositTeller,
    cashDetailShow,
    backDataShow
  }
};
</script>

