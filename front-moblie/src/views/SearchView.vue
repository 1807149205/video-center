<script setup lang="ts">

import router from "@/router";

const onClickLeft = () => {
  router.push('/');
}

</script>

<template>
  <van-nav-bar
      title="搜索"
      left-text="返回"
      left-arrow
      @click-left="onClickLeft"
  />
<div>搜索界面</div>
</template>

<style scoped>

</style>