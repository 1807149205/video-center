<script setup lang="ts">
import router from "@/router";
import {onMounted, ref} from "vue";
import AllVideoTab from "@/components/VideoView/AllVideoTab.vue";

const tabsActive = ref(0);

const initPage = async () => {

}

onMounted(async () => {
  await initPage();
})
</script>

<template>
  <van-search @click="router.push('/search')" placeholder="请输入搜索关键词" />
  <van-tabs v-model:active="tabsActive">
    <van-tab title="全部视频">
      <AllVideoTab/>
    </van-tab>
    <van-tab title="推荐视频">内容 2</van-tab>
    <van-tab title="我的视频">内容 2</van-tab>
  </van-tabs>
</template>

<style scoped></style>
