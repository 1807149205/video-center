<script setup lang="ts">

import { useLoginStore } from '@/stores/login'
import router from '@/router'

const loginStore = useLoginStore();

</script>

<template>
  <div style="padding: 2rem">
    <div class="personalContainer">
      <template v-if="!loginStore.isLogin">
        <div style="font-size: 16px" @click="router.push('/login')">
          当前用户未登录，请登录后再操作 >
        </div>
      </template>
      <template v-else>
        <div>
          已登录
        </div>
      </template>
    </div>
  </div>

</template>

<style scoped>
.personalContainer {
  width: 100%;
  height: 80px;
  background: #202020;
  border-radius: 15px;
  box-sizing: border-box;
  padding: 15px;
}
</style>
