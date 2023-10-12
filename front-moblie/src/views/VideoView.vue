<script setup lang="ts">

import {onMounted, PropType, ref} from "vue";
import SearchComponent from "@/components/search/SearchComponent.vue";
import {useSearchStore} from "@/stores/search";
import router from "@/router";

const searchStore = useSearchStore();

const props = defineProps({
  searchParam: { type: [String, Number] as PropType<string | number>, required: true },
})

//控制搜索弹出框
const searchVis = ref(false);
const searchValue = ref('');

const onSearch = () => {
  console.log('点击了搜索', searchValue, searchStore.searchParam);
  router.push({
    path: '/',
    query: {
      searchParam: JSON.stringify(searchStore.searchParam)
    }
  })
}

onMounted(() => {
  console.log(props.searchParam)
})

</script>

<template>

  <van-nav-bar title="视频中心"/>

  <van-search
    v-model="searchValue"
    show-action
    placeholder="请输入搜索关键词"
    @search="onSearch"
  >
    <template #action>
      <div>
        <van-button type="primary" @click="onSearch" icon="search">搜索</van-button>
        <van-button @click="searchVis = true" style="margin-left: 10px">高级搜索</van-button>
      </div>
    </template>
  </van-search>

  <van-action-sheet v-model:show="searchVis" title="搜索">
    <search-component></search-component>
  </van-action-sheet>


  <div>VideoView</div>

</template>

<style scoped></style>
