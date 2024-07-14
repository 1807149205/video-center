<script setup lang="ts">
import { RouterView } from 'vue-router'
</script>

<template>
  <van-config-provider theme="dark">
    <div class="index">
      <RouterView />
    </div>
  </van-config-provider>
</template>

<style scoped></style>
