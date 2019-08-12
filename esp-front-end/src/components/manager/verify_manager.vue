<template>
  <div class="container-fluid h-100">
    <div class="row">
      <div class="col">
        <button
          type="button"
          class="btn btn-outline-info btn-lg btn-block"
          @click="toggle_reviewed"
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
      button_content: ""
    };
  },
  created() {
    this.doc = this.$store.getters.editedDoc;
    this.axios.get("api/GET/transaction/" + this.doc).then(res => {
      this.transact_data = res.data;
      if (this.transact_data.reviewed)
        this.button_content = "取消此筆交易之審核";
      else this.button_content = "審核此筆交易";
    });
  },
  methods: {
    toggle_reviewed() {
      var mes = this.transact_data.reviewed
        ? "您確定要取消此筆交易之審核嗎？"
        : "您確定要審核此筆交易嗎？";
      if (confirm(mes)) {
        this.transact_data.reviewed = !this.transact_data.reviewed;
        this.axios
          .put("api/PUT/transaction/" + this.doc, this.transact_data)
          .then(res => {
            this.transact_data = res.data;
            console.log(this.transact_data);
          });
        this.$router.push("inquire_manager");
      }
    }
  }
};
</script>

