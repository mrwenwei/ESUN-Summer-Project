<template>
  <div class="container-fluid h-100">
    <div class="row">
      <table class="table table-bordered" >
        <!-- <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">First</th>
            <th scope="col">Last</th>
            <th scope="col">Handle</th>
          </tr>
        </thead>-->
        <tbody>
          <tr>
            <!-- <th scope="row">1</th> -->

            <th style="width:25%">金額</th>
            <td style="width:25%">amount</td>
            <th style="width:25%">存款戶名/票據申請人</th>
            <td style="width:25%">name</td>
          </tr>

          <tr>
            <th rowspan="3" style="width:5%">擇一填寫</th>
            <th style="width:5%">存款</th>
            <th style="width:5%">帳號</th>
            <td style="width:85%">Account</td>
          </tr>

          <tr>
            <th style="width:100%">繳卡款</th>
          </tr>

          <tr>
            <th style="width:100%">開立票據</th>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      doc: "",
      transact_data: ""
    };
  },
  created() {
    this.doc = this.$store.getters.editedDoc;
    this.axios.get("api/GET/transaction/" + this.doc).then(res => {
      this.transact_data = res.data;
    });
  }
};
</script>

