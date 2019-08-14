<template>
  <div class="container-fluid h-100">
    <div class="row">
      <div class="col-md-12">
        <v-client-table ref="myTable" :data="tableData" :columns="columns" :options="options">
          <template slot="filter__reviewed">
            <input type="radio" value="false" v-model="keywordReviewed" @change="searchByReviewed" />
            <label for="one">未審核</label>

            <input type="radio" value="true" v-model="keywordReviewed" @change="searchByReviewed" />
            <label for="one">已審核</label>
          </template>

          <template slot="reviewed" slot-scope="props">
            <a
              @click="edit(props.row.id)"
              style="color:blue; cursor:pointer"
            >{{ modifyItem(props.row.reviewed, props.row.reviewedCondition) }}</a>
          </template>
          <template slot="顧客姓名" slot-scope="props">
            {{ modifyReceiptsDataName(props.row.receiptsData) }}
          </template>
          <template slot="交易金額" slot-scope="props">
            {{ modifyReceiptsDataAmount(props.row.receiptsData) }}
          </template>

        </v-client-table>
      </div>
    </div>
  </div>
</template>

<script>
import daterangepicker from "daterangepicker";
export default {
  data() {
    return {
      keywordReviewed: null,
      columns: ["type", "id", "顧客姓名", "交易金額", "dateTime", "broker", "finishedTime", "reviewed"],
      tableData: [],
      options: {
        perPage: 25,
        headings: {
          id: "ID",
          type: "項目",
          dateTime: "申請時間",
          broker: "經手人",
          reviewed: "審核結果",
          finishedTime: "交易完成時間"
        },
        requestFunction: function(params) {
          return this.axios.get("api/GET/transactions", {
            params: params
          });
        },
        responseAdapter({ data }) {
          return {
            data,
            count: data.length
          };
        },
        filterByColumn: true,
        filterable: ["id", "type", "dateTime"],
        dateColumns: ["dateTime"],
        dateFormat: "YYYY/MM/DD HH:mm:ss",
        datepickerOptions: {
          //See http://www.daterangepicker.com/#options
          showDropdowns: true,
          autoUpdateInput: true
        },
        listColumns: {
          type: [
            { id: "存款", text: "存款" },
            { id: "取款", text: "取款" },
            { id: "匯款", text: "匯款" }
          ]
        },
        customFilters: [
          {
            name: "filterBySide",
            callback: function(row, query) {
              return row.id.toLowerCase().includes(query.toLowerCase());
            }
          },
          {
            name: "filterByReviewed",
            callback: function(row, query) {
              return String(row.reviewed) == String(query);
            }
          }
        ]
      }
    };
  },
  created() {
    this.axios.get("api/GET/transactions").then(res => {
      this.tableData = res.data;
      this.tableData.map(x => {
        x.dateTime = moment(x.dateTime + " GMT+0000");
        if (x.finishedTime) 
          x.finishedTime = moment(x.finishedTime + " GMT+0000");
      });
    });
  },
  methods: {
    search(keyword) {
      Event.$emit("vue-tables.filter::filterBySide", keyword);
    },
    searchByReviewed() {
      if (this.keywordReviewed) {
        Event.$emit(
          "vue-tables.filter::filterByReviewed",
          this.keywordReviewed
        );
      }
    },
    modifyItem(reviewed, reviewedCondition) {
      if (reviewedCondition) return "審核中"
      else if (reviewed) return "已審核";
      else return "未審核";
    },
    modifyReceiptsDataName(receiptsData) {
      var receipts = JSON.parse(receiptsData)
      return receipts.customerName;
    },
    modifyReceiptsDataAmount(receiptsData) {
      var receipts = JSON.parse(receiptsData)
      return bigdecimal.BigInteger(receipts.depositAmount);
    },
    edit(id) {
      // Update local table
      this.axios.get("api/GET/transaction/" + id).then(res => {
        for (let i = 0; i < this.tableData.length; i++) {
          if (this.tableData[i].id == id) {
            this.tableData[i] = res.data;
            break;
          }
        }
        // Handling race condition, 1 for free, 0 for busy
        if (res.data.reviewedCondition == 0) {
          this.$store
            .dispatch("edit", id)
            .then(() => {
              // set the status to verifying
              res.data.reviewedCondition = 1
              this.axios
              .put("api/PUT/transaction/" + id, res.data)
              .then(put_res => {
                console.log(put_res.data.reviewedCondition)
                this.$router.push("verify_manager");
              });
              
            })
            .catch(err => console.log(err));
        } else {
          confirm("此筆交易辦理中，請稍候！");
        }
      });
    }
  }
};
</script>
<style>
@import "../../../node_modules/daterangepicker/daterangepicker.css";
.VueTables__date-filter {
  border: 1px solid #ccc;
  padding: 6px;
  border-radius: 4px;
  cursor: pointer;
}
</style>
