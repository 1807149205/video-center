<script setup lang="ts">
import { onMounted, PropType, ref } from 'vue'
import SearchComponent from '@/components/search/SearchComponent.vue'
import { useSearchStore } from '@/stores/search'
import router from '@/router'

const searchStore = useSearchStore();

const props = defineProps({
  searchParam: { type: [String, Number] as PropType<string | number> },
  searchContent: { type: String as PropType<string> },
})

//控制搜索弹出框
const searchVis = ref<boolean>(false);
//url传递的searchContent的值、搜索框的值
const searchValue = ref<string>('');

const onSearch = () => {
  router.push({
    path: '/',
    query: {
      searchParam: JSON.stringify(searchStore.searchParam),
      searchContent: searchValue.value
    }
  })
}

onMounted(() => {
  searchValue.value = props.searchContent as string;
  if (props.searchParam) {
    searchStore.searchParam = JSON.parse(<string>props.searchParam);
  }
  if (router.currentRoute.value.query.searchContent) {
    searchValue.value = router.currentRoute.value.query.searchContent as string;
  }
})
</script>

<template>
  <van-nav-bar title="视频中心" />
<!--搜索框-->
  <van-search v-model="searchValue" show-action placeholder="请输入搜索关键词" @search="onSearch">
    <template #action>
      <van-button type="primary" @click="onSearch" icon="search">搜索</van-button>
      <van-button @click="searchVis = true" style="margin-left: 10px">高级搜索</van-button>
    </template>
  </van-search>
<!--搜索弹出框-->
  <van-action-sheet v-model:show="searchVis" title="搜索">
    <search-component></search-component>
  </van-action-sheet>

<!--  主界面-->
  <div class="main-content">
    VideoView
  </div>
</template>

<style scoped>
.main-content {
  padding: 15px;
}
</style>
