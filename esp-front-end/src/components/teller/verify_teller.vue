<template>
  <div class="container-fluid h-100">
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
export default {
  data() {
    return {
      doc: "",
      transact_data: "",
      button_content: "",
      tmp_finished: null
    };
  },
  created() {
    this.doc = this.$store.getters.editedDoc;
    this.axios.get("api/GET/transaction/" + this.doc).then(res => {
      //   // if other teller is verifying
      //   if (res.data.finished == null) {
      //     confirm("此筆交易辦理中，請稍候！");
      //     // push back
      //     this.$router.push("inquire_teller");
      //   } else {
      //     this.transact_data = res.data;
      //     if (this.transact_data.finished) {
      //       this.button_content = "取消此筆交易之辦理";
      //       this.tmp_finished = 1;
      //     } else {
      //       this.button_content = "辦理此筆交易";
      //       this.tmp_finished = 0;
      //     }
      //     // set the status to verifying
      //     res.data.finished = null;
      //     this.axios
      //       .put("api/PUT/transaction/" + this.doc, res.data)
      //       .then(put_res => {});
      //   }

      this.transact_data = res.data;
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
        console.log(this.transact_data.finished);
        this.transact_data.finished = !this.transact_data.finished;
        this.axios
          .put("api/PUT/transaction/" + this.doc, this.transact_data)
          .then(res => {
            this.transact_data = res.data;
            console.log(this.transact_data);
          });
        this.$router.push("inquire_teller");
      }
    }
  }
};
</script>

