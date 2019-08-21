<template>
  <div style="height:100%;wieght:100%;">
    <!-- deposit form -->
    <div class="container-fluid" v-if="transact_data.type=='存款'" style=" height:100%;">
      <p>
        <br />
      </p>
      <div class="row no-glutters" style=" border:1px solid;height:10%;">
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
      <div class="row no-glutters" style=" height:15%;">
        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>存款金額</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span>{{receiptsData.transactAmount}}元</span>
        </div>

        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>戶名/申請人</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span>{{receiptsData.customerName}}</span>
        </div>
      </div>
      <!-- 交易方式 -->
      <div
        class="row no-glutters"
        v-if="receiptsData.depositMethod=='deposit'"
        style=" height:15%;"
      >
        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>交易方式</span>
        </div>
        <div class="col-md-10 align-self-center" style="border:1px solid;height:100%;">
          <div class="row no-glutters" style=" height:100%;">
            <div class="col-md-1 align-self-center" style="border:1px solid;height:100%;">
              <span>存款</span>
            </div>
            <div class="col-md-1 align-self-center" style="border:1px solid;height:100%;">
              <span>帳號</span>
            </div>
            <div class="col-md-10 align-self-center" style="border:1px solid;height:100%;">
              <span>{{receiptsData.transactDetail.depositAccount}}</span>
            </div>
          </div>
        </div>
      </div>
      <!-- 交易方式 -->
      <div
        class="row no-glutters"
        v-else-if="receiptsData.depositMethod=='creditCardFee'"
        style=" height:45%;"
      >
        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>交易方式</span>
        </div>
        <div class="col-md-10 align-self-center" style="border:1px solid;height:100%;">
          <div class="row no-glutters" style=" height:100%;">
            <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
              <span>繳卡款</span>
            </div>
            <div class="col-md-10 align-self-center" style="border:1px solid;height:100%;">
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
                <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
                  <span>信用卡正戶姓名</span>
                </div>
                <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
                  <span>{{receiptsData.transactDetail.creditCardUser}}</span>
                </div>
                <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
                  <span>正卡戶統一編號</span>
                </div>
                <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
                  <span>{{receiptsData.transactDetail.creditCardUserTaxNumber}}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 交易方式 -->
      <div
        class="row no-glutters"
        v-else-if="receiptsData.depositMethod=='ticket'"
        style=" height:30%;"
      >
        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>交易方式</span>
        </div>
        <div class="col-md-10 align-self-center" style="border:1px solid;height:100%;">
          <div class="row no-glutters" style=" height:100%;">
            <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
              <span>開立票據</span>
            </div>
            <div class="col-md-10 align-self-center" style="border:1px solid;height:100%;">
              <div class="row no-glutters" style=" height:50%;">
                <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
                  <span>票據類型</span>
                </div>
                <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
                  <span>{{receiptsData.transactDetail.ticketType}}</span>
                </div>
                <div class="col-md-3 align-self-center" style="border:1px solid;height:100%;">
                  <span>禁止背書轉讓</span>
                </div>
                <div class="col-md-3 align-self-center" style="border:1px solid;height:100%;">
                  <!-- <span>{{receiptsData.transactDetail.prohibitTransfer}}</span> -->
                  <span v-if="receiptsData.transactDetail.prohibitTransfer==true">是</span>
                  <span v-if="receiptsData.transactDetail.prohibitTransfer==false">否</span>
                </div>
              </div>
              <div class="row no-glutters" style=" height:50%;">
                <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
                  <span>抬頭</span>
                </div>
                <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
                  <span>{{receiptsData.transactDetail.ticketHead}}</span>
                </div>
                <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
                  <span>票號</span>
                </div>
                <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
                  <span>{{receiptsData.transactDetail.ticketNum}}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- withdraw form -->
    <div class="container-fluid" v-if="transact_data.type=='取款'" style=" height:100%;">
      <p>
        <br />
      </p>
      <div class="row no-glutters" style=" border:1px solid;height:10%;">
        <div class="col-md-3 align-self-center" style="height:100%;">
          <span>{{transact_data.dateTime}}</span>
        </div>
        <div class="col-md-6 text-center" style=" height:100%;">
          <span>玉山銀行 取款憑條</span>
        </div>
        <div class="col-md-3 align-self-center" style="height:100%;">
          <span></span>
        </div>
      </div>

      <div class="row no-glutters" style=" height:15%;">
        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>取款金額</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span>{{receiptsData.transactAmount}}元</span>
        </div>

        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>戶名/申請人</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span>{{receiptsData.customerName}}</span>
        </div>
      </div>

      <div class="row no-glutters" style=" height:15%;">
        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>取款帳號</span>
        </div>

        <div class="col-md-10 align-self-center" style="border:1px solid;height:100%;">
          <span>{{receiptsData.withdrawAccount}}</span>
        </div>
      </div>

      <!-- 交易方式 -->
      <div
        class="row no-glutters"
        v-if="receiptsData.withdrawMethod=='deposit'"
        style=" height:15%;"
      >
        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>交易方式</span>
        </div>
        <div class="col-md-10 align-self-center" style="border:1px solid;height:100%;">
          <div class="row no-glutters" style=" height:100%;">
            <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
              <span>單筆存入</span>
            </div>
            <div class="col-md-1 align-self-center" style="border:1px solid;height:100%;">
              <span>帳號</span>
            </div>
            <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
              <span>{{receiptsData.transactDetail.depositAccount}}</span>
            </div>
            <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
              <span>戶名</span>
            </div>
            <div class="col-md-3 align-self-center" style="border:1px solid;height:100%;">
              <span>{{receiptsData.transactDetail.accountOwner}}</span>
            </div>
          </div>
        </div>
      </div>

      <!-- 交易方式 -->
      <div
        class="row no-glutters"
        v-else-if="receiptsData.withdrawMethod=='ticket'"
        style=" height:30%;"
      >
        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>交易方式</span>
        </div>
        <div class="col-md-10 align-self-center" style="border:1px solid;height:100%;">
          <div class="row no-glutters" style=" height:100%;">
            <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
              <span>開立票據</span>
            </div>
            <div class="col-md-10 align-self-center" style="border:1px solid;height:100%;">
              <div class="row no-glutters" style=" height:50%;">
                <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
                  <span>票據類型</span>
                </div>
                <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
                  <span>{{receiptsData.transactDetail.ticketType}}</span>
                </div>
                <div class="col-md-3 align-self-center" style="border:1px solid;height:100%;">
                  <span>禁止背書轉讓</span>
                </div>
                <div class="col-md-3 align-self-center" style="border:1px solid;height:100%;">
                  <span v-if="receiptsData.transactDetail.prohibitTransfer == true">是</span>
                  <span v-if="receiptsData.transactDetail.prohibitTransfer == false">否</span>
                </div>
              </div>
              <div class="row no-glutters" style=" height:50%;">
                <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
                  <span>抬頭</span>
                </div>
                <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
                  <span>{{receiptsData.transactDetail.ticketHead}}</span>
                </div>
                <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
                  <span>票號</span>
                </div>
                <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
                  <span>{{receiptsData.transactDetail.ticketNum}}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- transfer form -->
    <div class="container-fluid" v-if="transact_data.type=='匯款'" style=" height:100%;">
      <p>
        <br />
      </p>
      <div class="row no-glutters" style=" border:1px solid;height:10%;">
        <div class="col-md-3 align-self-center" style="height:100%;">
          <span>{{transact_data.dateTime}}</span>
        </div>
        <div class="col-md-6 text-center" style=" height:100%;">
          <span>玉山銀行 匯款憑條</span>
        </div>
        <div class="col-md-3 align-self-center" style="height:100%;">
          <span></span>
        </div>
      </div>

      <div class="row no-glutters" style=" height:10%;">
        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>匯款金額</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span>{{receiptsData.transactAmount}}</span>
        </div>

        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>收款銀行</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span>{{receiptsData.transferBank}} {{receiptsData.transferBranch}}</span>
        </div>
      </div>

      <div class="row no-glutters" style=" height:10%;">
        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>收款人帳號</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span>{{receiptsData.transferAccount}}</span>
        </div>

        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>收款人戶名</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span>{{receiptsData.transferName}}</span>
        </div>
      </div>

      <div class="row no-glutters" style=" height:10%;">
        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>匯款人戶名</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span>{{receiptsData.customerName}}</span>
        </div>

        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>匯款人統一編號</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span>{{receiptsData.customerTaxNumber}}</span>
        </div>
      </div>

      <div class="row no-glutters" style=" height:10%;">
        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>匯款方式</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span v-if="receiptsData.transferMethod=='account'">轉帳</span>
          <span v-if="receiptsData.transferMethod=='cash'">現金</span>
        </div>

        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>匯費繳納方式</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span v-if="receiptsData.transferFeeMethod=='account'">轉帳</span>
          <span v-if="receiptsData.transferFeeMethod=='cash'">現金</span>
        </div>
      </div>

      <div class="row no-glutters" style=" height:10%;">
        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>取款帳號</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span>{{receiptsData.customerAccount}}</span>
        </div>

        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>匯款人電話</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span>{{receiptsData.customerPhone}}</span>
        </div>
      </div>

      <div class="row no-glutters" style=" height:10%;">
        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>匯費</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span>{{receiptsData.transferFee}}元</span>
        </div>

        <div class="col-md-2 align-self-center" style="border:1px solid;height:100%;">
          <span>合計</span>
        </div>

        <div class="col-md-4 align-self-center" style="border:1px solid;height:100%;">
          <span>{{receiptsData.totalAmount}}元</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      docId: "",
      transact_data: "",
      receiptsData: ""
    };
  },
  created() {
    this.docId = this.$store.getters.editedDoc;
    this.$emit("docIDReceive", {
      docId: this.docId
    });
    this.axios.get("api/GET/transaction/" + this.docId).then(res => {
      this.transact_data = res.data;
      this.transact_data.dateTime = moment(
        this.transact_data.dateTime + " GMT+0000"
      ).format("YYYY/MM/DD HH:mm:ss");
      this.$emit("infoPresent", {
        transact_data: this.transact_data
      });
      this.receiptsData = JSON.parse(res.data.receiptsData);
    });
  }
};
</script>