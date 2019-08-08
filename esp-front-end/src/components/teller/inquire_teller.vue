<template>
  <div class="container-fluid h-100">
    <div class="row no-glutters justify-content-center">
      <div class="col">
        <div class="btn-group" data-toggle="buttons">
          <label class="btn">
            <input type="checkbox" value="deposit" v-model="checked" /> 存款
          </label>
          <label class="btn">
            <input type="checkbox" value="withdraw" v-model="checked" /> 提款
          </label>
          <label class="btn">
            <input type="checkbox" value="transfer" v-model="checked" /> 匯款
          </label>
        </div>
      </div>

      <div class="col">
        <div class="btn-group" data-toggle="buttons">
          <label class="btn">
            <input type="checkbox" value="todo" v-model="checked" /> 待辦理
          </label>
          <label class="btn">
            <input type="checkbox" value="finish" v-model="checked" /> 已完成
          </label>
        </div>
      </div>

      <div class="col">
        <div class="input-group">
          <input type="text" class="form-control" v-model="searched" />
          <span class="input-group-btn">
            <button class="btn btn-default" type="button">搜尋</button>
          </span>
        </div>
      </div>
    </div>
    <div class="row">{{checked}}{{searched}}</div>
    <div class="row h-100">
      <div class="col">
        <v-client-table ref="myTable" :data="tableData" :columns="columns" :options="options"></v-client-table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      checked: [],
      searched: "",
      columns: [
        "id",
        "type",
        "dateTime",
        "branchCode",
        "broker",
        "receiptsData",
        "finished",
        "reviewed"
      ],
      tableData: [],
      options: {}
    };
  },
  created() {
    this.axios.get("api/GET/transactions").then(res => {
      this.tableData = res.data;
    });
  }
};
</script>
