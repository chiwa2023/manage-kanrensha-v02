<script setup lang="ts">
import { ref, watch, type Ref } from 'vue';
import type { PagingDto } from '../../dto/paging/pagingDto';
import { createPagingData } from '../../util/createPagingData';

// props,emit
const props = defineProps<{ allCount: number, limit: number, pageNumber: number }>();
const emits = defineEmits(["sendPagingNumber"]);

// Paging
const listPaging: Ref<PagingDto[]> = ref(createPagingData(props.allCount, props.limit, props.pageNumber));
const lastPage: Ref<number> = ref(Math.floor(props.allCount/props.limit)+1); // Mock data

watch(props, () => {
    // TODO 不正値の場合の処理
    listPaging.value = createPagingData(props.allCount, props.limit, props.pageNumber);
    lastPage.value = Math.floor(props.allCount/props.limit)+1;
});


function onSearchFirst() {
    onSearchPage(0);
}

function onSearchPre() {
    onSearchPage(props.pageNumber-1);
}

function onSearchPro() {
    onSearchPage(props.pageNumber+1);
}

function onSearchLast() {
    onSearchPage(lastPage.value);
}

/**
 * 選択されたページ番号を親に伝達する
 * @param pageNumber 表示ページ番号
 */
function onSearchPage(pageNumber: number) {
    if (0 <= pageNumber) {
        emits("sendPagingNumber",pageNumber);
    }
}

</script>
<template>
    <div class="one-line">
        <div class="pagination-container">
            <span class="pagination-info">全 {{ props.allCount }} 件中 {{ props.limit * props.pageNumber + 1 }} - {{
                props.limit * (props.pageNumber + 1) }} 件を表示</span>
            <div class="pagination-controls">
                <button @click="onSearchFirst">&lt;&lt;</button>
                <button @click="onSearchPre">&lt;</button>
                <button v-for="dto of listPaging" :class="dto.viewClass" @click="onSearchPage(dto.pageNumber)"
                    :disabled="dto.disabled">{{
                        dto.pageText }}</button>
                <button @click="onSearchPro">&gt;</button>
                <button @click="onSearchLast">&gt;&gt;</button>
            </div>
        </div>
    </div>
</template>
<style scoped></style>
