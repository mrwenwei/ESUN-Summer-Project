<template>
  <div class="container-fluid h-100">
    <!-- <div>{{transact_data}}</div> -->
    <div class="row no-glutters" style=" height:10%;">
      <div class="col-md-3 align-self-center" style="height:100%;">
        <span>{{transact_data.dateTime}}</span>
      </div>
      <div class="col-md-6 text-center" style=" height:100%;">
        <span>玉山銀行 存款憑條</span>
      </div>
      <div class="col-md-3 align-self-center" style="height:100%;">
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
          <div class="col-md-1 align-self-center" style="border:1px solid;height:100%;">
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

    <div class="row no-glutters"  >
      <!-- 背面資料 -->
      <div class="col-10">
        <backData></backData>
      </div>
      <!-- 現金明細 -->
      <div class="col-2" >
    <cashDetail @cash_updated="get_child_data"></cashDetail>
      </div>
    </div>

    <div class="row">
      <div class="col">
        <button
          type="button"
          class="btn btn-outline-info btn-lg btn-block"
          @click="toggle_finished"
        >{{button_content}}</button>
      </div>
    </div>
  </div>
</template>

<script>
import cashDetail from './cashDetail'
import backData from './backData'
export default {
  data() {
    return {
      docId: "",
      transact_data: "",
      receiptsData: "",
      transactDetail: "",
      button_content: "",
      cashForm:{
        
      }
    };
  },
  created() {
    this.docId = this.$store.getters.editedDoc;
    this.axios.get("api/GET/transaction/" + this.docId).then(res => {
      this.transact_data = res.data;
      this.receiptsData = JSON.parse(res.data.receiptsData);
      this.transactDetail = this.receiptsData.transactDetail;
      if (this.transact_data.finished) {
        this.button_content = "取消此筆交易之辦理";
      } else {
        this.button_content = "辦理此筆交易";
      }
    });
  },
  methods: {
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
        else
          this.transact_data.broker = null;

        this.axios
          .put("api/PUT/transaction/" + this.docId, this.transact_data)
          .then(res => {
            this.transact_data = res.data;
          });
        this.$router.push("inquire_teller");
      }
    },
    get_child_data(value) {
      this.cashForm = value;
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
  components:{
    cashDetail,
    backData
  }
}
</script>

