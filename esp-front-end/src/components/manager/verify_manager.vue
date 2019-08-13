<template>
  <div class="container-fluid" style=" height:80%">
    <!-- <div>{{transact_data}}</div> -->
    <div class="row no-glutters" style=" height:10%;">
      <div class="col-md-3 align-self-center" style="solid;height:100%;">
        <span>{{transact_data.dateTime}}</span>
      </div>
      <div class="col-md-6 text-center" style=" solid;height:100%;">
        <span>玉山銀行 存款憑條</span>
      </div>
      <div class="col-md-3 align-self-center" style="solid;height:100%;">
        <span></span>
      </div>
    </div>
    <div class="row no-glutters" style=" height:10%;">
      <div class="col-md-1 align-self-center bg-info" style="border:1px solid;height:100%;">
        <span>存款金額(新台幣)</span>
      </div>

      <div class="col-md-5 align-self-center" style="border:1px solid;height:100%;">
        <span>{{receiptsData.depositAmount}}</span>
      </div>

      <div class="col-md-1 align-self-center bg-info" style="border:1px solid;height:100%;">
        <span>存款戶名/申請人</span>
      </div>

      <div class="col-md-5 align-self-center" style="border:1px solid;height:100%;">
        <span>{{receiptsData.customerName}}</span>
      </div>
    </div>
    <!-- 交易方式 -->
    <div class="row no-glutters" v-if='receiptsData.depositMethod==="deposit"' style=" height:10%;">
      <div class="col-md-1 align-self-center bg-info" style="border:1px solid;height:100%;">
        <span>交易方式</span>
      </div>
      <div class="col-md-11 align-self-center" style="border:1px solid;height:100%;">
        <!-- 存款 -->
        <div class="row no-glutters" style=" height:100%;">
          <div class="col-md-1 align-self-center" style="border:1px solid;height:100%;">
            <span>存款</span>
          </div>
          <div class="col-md-1 align-self-center bg-info" style="border:1px solid;height:100%;">
            <span>帳號</span>
          </div>
          <div class="col-md-10 align-self-center" style="border:1px solid;height:100%;">
            <span>{{transactDetail.depositAccount}}</span>
          </div>
        </div>
      </div>
    </div>
    <!-- 交易方式 -->
    <div
      class="row no-glutters"
      v-else-if='receiptsData.depositMethod=="creditCardFee"'
      style=" height:30%;"
    >
      <div class="col-md-1 align-self-center bg-info" style="border:1px solid;height:100%;">
        <span>交易方式</span>
      </div>
      <div class="col-md-11 align-self-center" style="border:1px solid;height:100%;">
        <!-- 繳卡款 -->
        <div class="row no-glutters" style=" height:100%;">
          <div class="col-md-1 align-self-center"  style="border:1px solid;height:100%;">
            <span>繳卡款</span>
          </div>
          <div class="col-md-11 align-self-center" style="border:1px solid;height:100%;">
            <div class="row no-glutters" style=" height:25%;">
              <div class="col-md-12 align-self-center" style="border:1px solid;height:100%;">
                <span>帳號：0598-440-011210</span>
              </div>
            </div>
            <div class="row no-glutters" style=" height:25%;">
              <div class="col-md-12 align-self-center" style="border:1px solid;height:100%;">
                <span>戶名：玉山銀行股份有限公司信用卡暨支付金融事業處</span>
              </div>
            </div>
            <div class="row no-glutters" style=" height:50%;">
              <div class="col-md-2 align-self-center bg-info" style="border:1px solid;height:100%;">
                <span>信用卡正戶姓名</span>
              </div>
              <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
                <span>{{transactDetail.creditCardUser}}</span>
              </div>
              <div class="col-md-2 align-self-center bg-info" style="border:1px solid;height:100%;">
                <span>正卡戶統一編號</span>
              </div>
              <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
                <span>{{transactDetail.creditCardUserTaxNumber}}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 交易方式 -->
    <div
      class="row no-glutters"
      v-else-if='receiptsData.depositMethod=="ticket"'
      style=" height:30%;"
    >
      <div class="col-md-1 align-self-center bg-info" style="border:1px solid;height:100%;">
        <span>交易方式</span>
      </div>
      <div class="col-md-11 align-self-center" style="border:1px solid;height:100%;">
        <!-- 開立票據 -->
        <div class="row no-glutters" style=" height:100%;">
          <div class="col-md-1 align-self-center" style="border:1px solid;height:100%;">
            <span>開立票據</span>
          </div>
          <div class="col-md-11 align-self-center" style="border:1px solid;height:100%;">
            <div class="row no-glutters" style=" height:50%;">
              <div class="col-md-2 align-self-center bg-info" style="border:1px solid;height:100%;">
                <span>票據類型</span>
              </div>
              <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
                <span>{{transactDetail.ticketType}}</span>
              </div>
              <div class="col-md-2 align-self-center bg-info" style="border:1px solid;height:100%;">
                <span>禁止背書轉讓</span>
              </div>
              <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
                <span>{{transactDetail.prohibitTransfer}}</span>
              </div>
            </div>
            <div class="row no-glutters" style=" height:50%;">
              <div class="col-md-2 align-self-center bg-info" style="border:1px solid;height:100%;">
                <span>抬頭</span>
              </div>
              <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
                <span>{{transactDetail.ticketHead}}</span>
              </div>
              <div class="col-md-2 align-self-center bg-info" style="border:1px solid;height:100%;">
                <span>票號</span>
              </div>
              <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
                <span>{{transactDetail.ticketNum}}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
      
    </div>

    <div class="row">
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
export default {
  data() {
    return {
      id: "",
      transact_data: "",
      receiptsData: "",
      transactDetail: "",
      button_content: ""
    };
  },
  created() {
    this.id = this.$store.getters.editedDoc;
    this.axios.get("api/GET/transaction/" + this.id).then(res => {
      this.transact_data = res.data;
      this.receiptsData = JSON.parse(res.data.receiptsData);
      this.transactDetail = this.receiptsData.transactDetail
      console.log(this.transactDetail);
      console.log(typeof this.transactDetail);
      if (this.transact_data.reviewed)
        this.button_content = "取消此筆交易之審核";
      else this.button_content = "審核此筆交易";
    });
  },
  methods: {
    toggle_reviewed() {
      var mes = this.transact_data.reviewed
        ? "您確定要取消此筆交易之審核嗎？"
        : "您確定要審核此筆交易嗎？";
      if (confirm(mes)) {
        this.transact_data.reviewed = !this.transact_data.reviewed;
        this.axios
          .put("api/PUT/transaction/" + this.id, this.transact_data)
          .then(res => {
            this.transact_data = res.data;
          });
        this.$router.push("inquire_manager");
      }
    }
  },
  beforeRouteLeave(to, from, next) {
    this.transact_data.reviewedCondition = 0;
    this.axios
      .put("api/PUT/transaction/" + this.id, this.transact_data)
      .then(res => {
        this.transact_data = res.data;
        next();
      });
  }
};
</script>

<style>
</style>
