<template>
  <form>
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
  </form>
</template>
<script>
export default {
  data() {
    return {};
  }
};
</script>
