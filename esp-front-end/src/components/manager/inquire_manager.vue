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

          <!-- <a
            slot="reviewed"
            slot-scope="props"
            @click="edit(props.row.id)"
            
          >{{ modifyItem(props.row.reviewed) }}</a>-->

          <template slot="reviewed" slot-scope="props">
            <a
              @click="edit(props.row.id)"
              style="color:blue; cursor:pointer"
            >{{ modifyItem(props.row.reviewed) }}</a>
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
      editedDoc: "",
      keywordReviewed: false,
      columns: ["id", "type", "dateTime", "broker", "reviewed"],
      tableData: [],
      options: {
        perPage: 25,
        headings: {
          id: "ID",
          type: "項目",
          dateTime: "日期",
          broker: "經手人",
          reviewed: "審核結果"
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
      });
    });
  },
  methods: {
    search(keyword) {
      Event.$emit("vue-tables.filter::filterBySide", keyword);
    },
    searchByReviewed(keywordReviewed) {
      if (this.keywordReviewed) {
        Event.$emit(
          "vue-tables.filter::filterByReviewed",
          this.keywordReviewed
        );
      }
    },
    modifyItem(reviewed_) {
      if (reviewed_) return "已審核";
      else return "未審核";
    },
    edit(id) {
      console.log(typeof id);
      this.editedDoc = id;
      console.log("editedDoc" + this.editedDoc);

      this.$store
        .dispatch("edit", this.editedDoc)
        .then(() => {
          console.log(this.editedDoc);
          this.$router.push("verify_manager");
        })
        .catch(err => console.log(err));
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
