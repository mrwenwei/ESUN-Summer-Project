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
            type="text"
            class="form-control"
            id="transactAmount"
            placeholder="請輸入金額"
            v-model="withdrawForm.transactAmount"
            required
          />
        </div>
      </div>
      <!-- 姓名 -->
      <div class="d-flex justify-content-center form-row form-group">
        <div class="col-md-2">
          <label for="customerName">取款戶名/票據申請人</label>
        </div>
        <div class="col-md-4">
          <input
            type="text"
            class="form-control"
            id="customerName"
            placeholder="請輸入姓名"
            v-model="withdrawForm.customerName"
            required
          />
        </div>
      </div>
      <!-- 帳號 -->
      <div class="d-flex justify-content-center form-row form-group">
        <div class="col-md-2">
          <label for="withdrawAccount">取款帳號</label>
        </div>
        <div class="col-md-4">
          <input
            type="text"
            class="form-control"
            id="withdrawAccount"
            placeholder="請輸入帳號"
            v-model="withdrawForm.withdrawAccount"
            required
          />
        </div>
      </div>

      <!-- 交易方式 -->
      <div class="d-flex justify-content-center form-row form-group">
        <div class="col-md-2">
          <label for="withdrawMethod">轉帳方式</label>
        </div>
        <div class="col-md-4">
          <select
            class="custom-select"
            id="withdrawMethod"
            v-model="withdrawForm.withdrawMethod"
          >
            <option value>請選擇轉帳方式</option>
            <option value="deposit">單筆存入</option>
            <option value="ticket">開立票據</option>
          </select>
          <!-- <div class="invalid-feedback">"您必須選擇交易方式"</div> -->
        </div>
      </div>

      <!-- dynamic transact method  -->
      <component :is="withdrawForm.withdrawMethod" @value_updated="get_child_data"></component>
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
      transactType: "取款",
      withdrawForm: {
        transactAmount: "",
        customerName:"",
        withdrawAccount: "",
        withdrawMethod: "",
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
            receiptsData: JSON.stringify(this.withdrawForm),
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
      this.withdrawForm.transactDetail = value;
    }
  },

  components: {
    deposit: {
      template: `
        <div>
            <div class="d-flex justify-content-center form-row form-group">
            <div class="col-md-2">
                <label for="depositAccount">存入帳號</label>
            </div>
            <div class="col-md-4">
                <input type="text" class="form-control" id="depositAccount" placeholder="請輸入帳號" v-model="depositAccount" @input="send_to_parent" required />
            </div>
            </div>
            <div class="d-flex justify-content-center form-row form-group">
            <div class="col-md-2">
                <label for="accountOwner">帳號戶名</label>
            </div>
            <div class="col-md-4">
                <input type="text" class="form-control" id="accountOwner" placeholder="請輸入帳號" v-model="accountOwner" @input="send_to_parent" required />
            </div>
            </div>
        </div>
      `,
      data() {
        return {
          depositAccount: "",
          accountOwner: ""
        };
      },
      methods: {
        send_to_parent() {
          this.$emit("value_updated", {
            depositAccount: this.depositAccount,
            accountOwner:this.accountOwner
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

          <div class="d-flex justify-content-center form-row form-group">
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

          <div class="d-flex justify-content-center form-row form-group">
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
