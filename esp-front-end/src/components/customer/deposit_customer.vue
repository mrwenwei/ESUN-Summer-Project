<template>
  <!-- 表單 -->
  <div class="container-fluid" style="height:90%">
    <form v-on:submit.prevent="submit_form" style="width:100%;height:100%">
      <p>
        <br />
      </p>
      <!-- 存款金額 -->
      <div class="d-flex justify-content-center form-row form-group">
        <div class="col-md-2">
          <label for="transactAmount">金額（新台幣）</label>
        </div>
        <div class="col-md-4">
          <input
            autocomplete="off"
            type="text"
            class="form-control"
            id="transactAmount"
            placeholder="請輸入金額"
            v-model="depositForm.transactAmount"
            required
          />
        </div>
      </div>
      <!-- 姓名 -->
      <div class="d-flex justify-content-center form-row form-group">
        <div class="col-md-2">
          <label for="customerName">存款戶名/票據申請人</label>
        </div>
        <div class="col-md-4">
          <input
            autocomplete="off"
            type="text"
            class="form-control"
            id="customerName"
            placeholder="請輸入姓名"
            v-model="depositForm.customerName"
            required
          />
        </div>
      </div>

      <!-- 電話 -->
      <div class="d-flex justify-content-center form-row form-group">
        <div class="col-md-2">
          <label for="customerPhone">聯絡電話</label>
        </div>
        <div class="col-md-4">
          <input
            autocomplete="off"
            type="text"
            class="form-control"
            id="customerPhone"
            placeholder="請輸入聯絡電話"
            v-model="depositForm.customerPhone"
            required
          />
        </div>
      </div>

      <!-- 交易方式 -->
      <div class="d-flex justify-content-center form-row form-group">
        <div class="col-md-2">
          <label for="depositMethod">存款方式</label>
        </div>
        <div class="col-md-4">
          <select
            class="custom-select"
            id="depositMethod"
            v-model="depositForm.depositMethod"
            required
          >
            <option value>請選擇存款方式</option>
            <option value="deposit">存戶存款</option>
            <option value="creditCardFee">繳卡款</option>
            <option value="ticket">開立票據</option>
          </select>
          <!-- <div class="invalid-feedback">"您必須選擇交易方式"</div> -->
        </div>
      </div>

      <!-- dynamic transact method  -->
      <component :is="depositForm.depositMethod" @value_updated="get_child_data"></component>
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
      transactType: "存款",
      depositForm: {
        transactAmount: "",
        customerName: "",
        customerPhone: "",
        depositMethod: "",
        transactDetail: {}
      }
    };
  },
  methods: {
    submit_form() {
      console.log(this.$store.getters.getBranchCode);
      if (confirm("您確定要送出申請嗎？")) {
        let uri = "api/POST/transaction";
        this.axios
          .post(uri, {
            type: this.transactType,
            receiptsData: JSON.stringify(this.depositForm),
            branchCode: this.$store.getters.getBranchCode
          })
          .then(response => {
            console.log(response.data);
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
    get_child_data(value) {
      this.depositForm.transactDetail = value;
    }
  },

  components: {
    deposit: {
      template: `
        <div class="d-flex justify-content-center form-row form-group">
          <div class="col-md-2">
            <label for="depositAccount">存款帳號</label>
          </div>
          <div class="col-md-4">
            <input autocomplete="off" type="text" class="form-control" id="depositAccount" placeholder="請輸入帳號" v-model="depositAccount" @input="send_to_parent" required />
          </div>
        </div>
      `,
      data() {
        return {
          depositAccount: ""
        };
      },
      methods: {
        send_to_parent() {
          this.$emit("value_updated", {
            depositAccount: this.depositAccount
          });
        }
      }
    },
    creditCardFee: {
      template: `
        <div>
          <div class="d-flex justify-content-center form-row form-group">
            <div class="col-md-2">
              <p>
                帳號
                <br>戶名
              </p>
            </div>
            <div class="col-md-4">
              <p>
                0598-440-011210
                <br>玉山銀行股份有限公司信用卡暨支付金融事業處
              </p>
            </div>
          </div>
          <div class="d-flex justify-content-center form-row form-group">
            <div class="col-md-2">
              <label for="creditCardUser">信用卡正卡戶姓名</label>
            </div>
            <div class="col-md-4">
              <input autocomplete="off" type="text" class="form-control" id="creditCardUser" placeholder="請輸入姓名" v-model="creditCardUser" @input="send_to_parent" required />
            </div>
          </div>
          <div class="d-flex justify-content-center form-row form-group">
            <div class="col-md-2">
              <label for="creditCardUserTaxNumber">正卡戶統一編號</label>
            </div>
            <div class="col-md-4">
              <input autocomplete="off" type="text" class="form-control" id="creditCardUserTaxNumber" placeholder="請輸入統編" v-model="creditCardUserTaxNumber" @input="send_to_parent" required />
            </div>
          </div>
        </div>
      `,
      data() {
        return {
          creditCardUser: "",
          creditCardUserTaxNumber: ""
        };
      },
      methods: {
        send_to_parent() {
          this.$emit("value_updated", {
            creditCardUser: this.creditCardUser,
            creditCardUserTaxNumber: this.creditCardUserTaxNumber
          });
        }
      }
    },
    ticket: {
      template: `
       <div>
          <div class="d-flex justify-content-center form-row form-group">
            <div class="col-md-2">
              <label for="ticketType">票據種類</label>
            </div>
            <div class="col-md-2">
              <select
                class="custom-select"
                id="ticketType"
                v-model="ticketType"
                @change="send_to_parent"
                required
              >
                <option value>請選擇票據種類</option>
                <option value="本支">本支</option>
                <option value="台支">台支</option>
              </select>
            </div>

            <div class="col-md-2">
              <div class="form-checkbox">
                <input autocomplete="off"
                 
                  type="radio"
                  id="prohibitTransfer"
                  :value="true"
                  v-model="prohibitTransfer"
                  @change="send_to_parent"
                />
                <label class="form-check-label" for="prohibitTransfer">禁止背書轉讓</label>
              </div>
            </div>
          </div>

          <div class="d-flex justify-content-center form-row form-group">
            <div class="col-md-2">
              <label for="ticketHead">抬頭</label>
            </div>
            <div class="col-md-4">
              <input autocomplete="off"
                type="text"
                class="form-control"
                id="ticketHead"
                placeholder="請輸入抬頭"
                v-model="ticketHead"
                @input="send_to_parent"
              />
            </div>
          </div>

          <div class="d-flex justify-content-center form-row form-group">
            <div class="col-md-2">
              <label for="ticketNum">票號</label>
            </div>
            <div class="col-md-4">
              <input autocomplete="off"
                type="text"
                class="form-control"
                id="ticketNum"
                placeholder="請輸入票號"
                v-model="ticketNum"
                @input="send_to_parent"
              />
            </div>
          </div>
        </div>
      `,
      data() {
        return {
          ticketType: "",
          prohibitTransfer: "false",
          ticketHead: "",
          ticketNum: ""
        };
      },
      methods: {
        send_to_parent() {
          this.$emit("value_updated", {
            ticketType: this.ticketType,
            prohibitTransfer: this.prohibitTransfer,
            ticketHead: this.ticketHead,
            ticketNum: this.ticketNum
          });
        }
      }
    }
  }
};
</script>
