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
      id: "",
      transact_data: "",
      button_content: ""
    };
  },
  created() {
    this.id = this.$store.getters.editedDoc;
    this.axios.get("api/GET/transaction/" + this.id).then(res => {
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
        this.transact_data.finished = !this.transact_data.finished;
        // if (this.transact_data.finished)
        //   this.transact_data.broker = this.$store.getters.userId;
        console.log(this.$store.getters.getUser);
        this.axios
          .put("api/PUT/transaction/" + this.id, this.transact_data)
          .then(res => {
            this.transact_data = res.data;
          });
        this.$router.push("inquire_teller");
      }
    }
  },
  beforeRouteLeave(to, from, next) {
    this.transact_data.finishedCondition = 0;
    this.axios
      .put("api/PUT/transaction/" + this.id, this.transact_data)
      .then(res => {
        this.transact_data = res.data;
        next();
      });
  }
};
</script>

