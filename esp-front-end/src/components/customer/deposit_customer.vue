<template>
  <div class="h-100">
    <!-- nav bar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-info">
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarTogglerDemo01"
        aria-controls="navbarTogglerDemo01"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand text-light" href="#">電子表單系統</a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0"></ul>
      </div>
    </nav>

    <!-- 表單 -->
    <div class="container-fluid h-100">
      <form v-on:submit.prevent="submit_form" style="width:100%;height:100%">
        <!-- 存款金額 -->
        <div class="form-row form-group">
          <div class="col-md-2">
            <label for="depositAmount">金額（新台幣）</label>
          </div>
          <div class="col-md-4">
            <input
              type="text"
              class="form-control"
              id="depositAmount"
              placeholder="請輸入金額"
              v-model="depositForm.depositAmount"
              required
            />
          </div>
        </div>
        <!-- 姓名 -->
        <div class="form-row form-group">
          <div class="col-md-2">
            <label for="customerName">存款戶名/票據申請人</label>
          </div>
          <div class="col-md-4">
            <input
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
        <div class="form-row form-group">
          <div class="col-md-2">
            <label for="customerPhone">聯絡電話</label>
          </div>
          <div class="col-md-4">
            <input
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
        <div class="form-row form-group">
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

        <button type="submit" class="btn btn-primary">確認</button>
      </form>
    </div>
  </div>
</template>


<script>
export default {
  data() {
    return {
      transactType: "存款",
      depositForm: {
        depositAmount: "",
        customerName: "",
        customerPhone: "",
        depositMethod: "",
        transactDetail: {}
      },
       
    };
  },
  methods: {
    submit_form() {
        let uri = "api/POST/transaction";
        this.axios.post(uri,  {
            'type':this.transactType,
            'receiptsData':JSON.stringify(this.depositForm)
        }).then(response => {
        // console.log(this.test);
        });
    },
    get_child_data(value) {
      this.depositForm.transactDetail = value;
    }
  },

  components: {
    deposit: {
      template: `
        <div class="form-row form-group">
          <div class="col-md-2">
            <label for="depositAccount">存款帳號</label>
          </div>
          <div class="col-md-4">
            <input type="text" class="form-control" id="depositAccount" placeholder="請輸入帳號" v-model="depositAccount" @input="send_to_parent" required />
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
          <div class="form-row form-group">
            <div class="col-md-4">
              <p>
                帳號：0598-440-011210
                <br />戶名：玉山銀行股份有限公司信用卡暨支付金融事業處
              </p>
            </div>
          </div>
          <div class="form-row form-group">
            <div class="col-md-2">
              <label for="creditCardUser">信用卡正卡戶姓名</label>
            </div>
            <div class="col-md-4">
              <input type="text" class="form-control" id="creditCardUser" placeholder="請輸入姓名" v-model="creditCardUser" @input="send_to_parent" required />
            </div>
          </div>
          <div class="form-row form-group">
            <div class="col-md-2">
              <label for="creditCardUserTaxNumber">正卡戶統一編號</label>
            </div>
            <div class="col-md-4">
              <input type="text" class="form-control" id="creditCardUserTaxNumber" placeholder="請輸入統編" v-model="creditCardUserTaxNumber" @input="send_to_parent" required />
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
          <div class="form-row form-group">
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
                <input
                 
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

          <div class="form-row form-group">
            <div class="col-md-2">
              <label for="ticketHead">抬頭</label>
            </div>
            <div class="col-md-4">
              <input
                type="text"
                class="form-control"
                id="ticketHead"
                placeholder="請輸入抬頭"
                v-model="ticketHead"
                @input="send_to_parent"
              />
            </div>
          </div>

          <div class="form-row form-group">
            <div class="col-md-2">
              <label for="ticketNum">票號</label>
            </div>
            <div class="col-md-4">
              <input
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
