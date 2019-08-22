<template>
  <!-- 表單 -->
  <div class="container-fluid" style="height:90%">
    <form v-on:submit.prevent="submit_form" style="width:100%;height:100%">
      <p>
        <br />
      </p>
      <!-- 匯款金額 -->
      <div class="d-flex justify-content-center form-row form-group">
        <div class="col-md-2">
          <label for="transactAmount">匯款金額（新台幣）</label>
        </div>
        <div class="col-md-4">
          <input
            autocomplete="off"
            type="text"
            class="form-control"
            id="transactAmount"
            placeholder="請輸入金額"
            @input="transferFeeCounter()"
            v-model="transferForm.transactAmount"
            required
          />
        </div>
      </div>

      <!-- 收款人帳號 -->
      <div class="d-flex justify-content-center form-row form-group">
        <div class="col-md-2">
          <label for="transferAccount">收款人帳號</label>
        </div>
        <div class="col-md-4">
          <input
            autocomplete="off"
            type="text"
            class="form-control"
            id="transferAccount"
            placeholder="請輸入帳號"
            v-model="transferForm.transferAccount"
            required
          />
        </div>
      </div>
      <!-- 收款人戶名 -->
      <div class="d-flex justify-content-center form-row form-group">
        <div class="col-md-2">
          <label for="transferName">收款人戶名</label>
        </div>
        <div class="col-md-4">
          <input
            autocomplete="off"
            type="text"
            class="form-control"
            id="transferName"
            placeholder="請輸入姓名"
            v-model="transferForm.transferName"
            required
          />
        </div>
      </div>

      <!-- 收款銀行 -->
      <div class="d-flex justify-content-center form-row form-group">
        <div class="col-md-2">
          <label for="transferBank">收款銀行</label>
        </div>
        <div class="col-md-2">
          <select
            class="custom-select"
            id="transferBank"
            v-model="transferForm.transferBank"
            required
          >
            <!-- <option value>請選擇收款銀行</option> -->
            <option v-for=" bank in banks" :key="bank.name">{{bank.swiftcode}} {{bank.name}}</option>
          </select>
        </div>

        <div class="col-md-2">
          <input
            autocomplete="off"
            type="text"
            class="form-control"
            id="transferBranch"
            placeholder="可輸入分行"
            v-model="transferForm.transferBranch"
          />
        </div>
      </div>

      <!-- 匯款人戶名 -->
      <div class="d-flex justify-content-center form-row form-group">
        <div class="col-md-2">
          <label for="customerName">匯款人戶名</label>
        </div>
        <div class="col-md-1">
          <input
            autocomplete="off"
            type="text"
            class="form-control"
            id="customerName"
            placeholder="請輸入姓名"
            v-model="transferForm.customerName"
            required
          />
        </div>
        <div class="d-flex align-items-end flex-column col-md-1">
          <label for="customerPhone">電話</label>
        </div>
        <div class="col-md-2">
          <input
            autocomplete="off"
            type="text"
            class="form-control"
            id="customerPhone"
            placeholder="請輸入電話"
            v-model="transferForm.customerPhone"
            required
          />
        </div>
      </div>

      <!-- 匯款人統一編號 -->
      <div class="d-flex justify-content-center form-row form-group">
        <div class="col-md-2">
          <label for="customerTaxNumber">匯款人統一編號</label>
        </div>
        <div class="col-md-4">
          <input
            autocomplete="off"
            type="text"
            class="form-control"
            id="customerTaxNumber"
            placeholder="請輸入統編"
            v-model="transferForm.customerTaxNumber"
            required
          />
        </div>
      </div>

      <!-- 匯款方式 -->
      <div class="d-flex justify-content-center form-row form-group">
        <div class="col-md-2">
          <label for="transferMethod">匯款方式</label>
        </div>
        <div class="col-md-1">
          <select
            class="custom-select"
            id="transferMethod"
            v-model="transferForm.transferMethod"
            @change="transferFeeCounter()"
            required
          >
            <option value>請選擇匯款方式</option>
            <option value="account">轉帳</option>
            <option value="cash">現金</option>
          </select>
        </div>
        <div class="d-flex align-items-end flex-column col-md-1">
          <label for="transferFeeMethod">匯費方式</label>
        </div>
        <div class="col-md-2">
          <select
            class="custom-select"
            id="transferFeeMethod"
            v-model="transferForm.transferFeeMethod"
            required
          >
            <option value>請選擇匯費方式</option>
            <option value="account">轉帳</option>
            <option value="cash">現金</option>
          </select>
        </div>
      </div>

      <!-- dynamic transact method  -->
      <component
        :is="transferForm.transferMethod||transferForm.transferFeeMethod"
        @value_updated="get_child_data"
      ></component>

      <!-- 匯費和合計 -->
      <div class="d-flex justify-content-center form-row form-group">
        <div class="col-md-2">
          <label>匯費</label>
        </div>
        <div class="col-md-1">
          <label>{{transferForm.transferFee}}</label>
        </div>
        <div class="d-flex align-items-end flex-column col-md-1">
          <label>合計</label>
        </div>
        <div class="col-md-2">
          <label>{{transferForm.totalAmount}}</label>
        </div>
      </div>

      <div class="d-flex justify-content-center form-row">
        <div class="col-4"></div>
        <div class="d-flex align-items-end flex-column col-2">
          <button type="submit" class="btn btn-primary">確認</button>
        </div>
      </div>
    </form>
  </div>
</template>


<script>
export default {
  data() {
    return {
      banks: [],
      transactType: "匯款",
      transferForm: {
        transactAmount: "0",
        transferAccount: "",
        transferName: "",
        trasferBank: "",
        transferBranch: "",
        customerName: "",
        customerTaxNumber: "",
        customerPhone: "",
        transferMethod: "",
        transferFeeMethod: "",
        customerAccount: "",
        transferFee: "0",
        totalAmount: "0"
      }
    };
  },
  methods: {
    submit_form() {
      if (confirm("您確定要送出申請嗎？")) {
        let uri = "api/POST/transaction";
        this.axios
          .post(uri, {
            type: this.transactType,
            receiptsData: JSON.stringify(this.transferForm),
            branchCode: this.$store.getters.getBranchCode
          })
          .then(response => {
            this.axios
              .get("api/GET/transaction/" + response.data.id)
              .then(res => {
                alert(
                  "已送出申請，即將返回首頁，您的交易編號為：" + res.data.tnum
                );
              });
          });

        this.$router.push("home_customer");
      } else {
        console.log("沒事按到ㄅ歉");
      }
    },
    transferFeeCounter() {
      if (this.transferForm.transferMethod == "account") {
        if (Number(this.transferForm.transactAmount) <= 2000000) {
          this.transferForm.transferFee = "30";
        } else if (Number(this.transferForm.transactAmount) > 2000000) {
          this.transferForm.transferFee = (
            30 +
            Math.ceil(
              (Number(this.transferForm.transactAmount) - 2000000) / 1000000
            ) *
              50
          ).toString();
        }
      } else if (this.transferForm.transferMethod == "cash") {
        if (Number(this.transferForm.transactAmount) <= 2000000) {
          this.transferForm.transferFee = "100";
        } else if (Number(this.transferForm.transactAmount) > 2000000) {
          this.transferForm.transferFee = (
            100 +
            Math.ceil(
              (Number(this.transferForm.transactAmount) - 2000000) / 1000000
            ) *
              50
          ).toString();
        }
      }
      this.transferForm.totalAmount = (
        Number(this.transferForm.transactAmount) +
        Number(this.transferForm.transferFee)
      ).toString();
    },
    get_child_data(value) {
      this.transferForm.customerAccount = value.customerAccount;
    }
  },
  components: {
    account: {
      template: `
        <div>
            <div class="d-flex justify-content-center form-row form-group">
                <div class="col-md-2">
                <label for="customerAccount">取款帳號</label>
                </div>
                <div class="col-md-4">
                <input autocomplete="off"
                    type="text"
                    class="form-control"
                    id="customerAccount"
                    placeholder="請輸入帳號"
                    v-model="customerAccount"
                    @input="send_to_parent"
                    required
                />
                </div>
            </div>     
        </div>
      `,
      data() {
        return {
          customerAccount: ""
        };
      },
      methods: {
        send_to_parent() {
          this.$emit("value_updated", {
            customerAccount: this.customerAccount
          });
        }
      }
    },

    cash: {
      template: `
        
        `
    }
  },
  created() {
    this.axios.get("api/GET/banks").then(res => {
      // for(var i=0;i<res.data.length;i++){
      //   this.banks.push(res.data[i].name)
      // }
      this.banks = res.data;
      console.log(this.banks);
    });
  }
};
</script>
