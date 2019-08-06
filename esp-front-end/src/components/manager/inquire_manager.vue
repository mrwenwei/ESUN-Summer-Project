<template>
    <div class="container-fluid h-100">
        <form v-on:submit.prevent="submit_form" style="width:100%;height:100%">
            <div class="form-row form-group">
                <div class="col-md-2">
                    <label for="searchItem"></label>
                </div>
                <div class="col-md-4">
                    <input
                        type="text"
                        class="form-control"
                        id="searchItem"
                        placeholder="搜尋項目"
                        v-model="searchForm.searchItem"
                        required
                    />
                </div>
            </div>
            <button type="submit" class="btn btn-primary">搜尋</button>
        </form>
    </div>
</template>

<script>
export default {
    data() {
        return {
            searchForm: {
                searchItem: ""
            }
        }
    }
}
</script>
