<script setup lang="ts">
import { RouterView } from 'vue-router'
import { onMounted } from 'vue'
import UserStorage from '@/utils/UserStorage'
import { useLoginStore } from '@/stores/login'

const loginStore = useLoginStore();

onMounted(async () => {
  const userInfo = UserStorage.getUserInfo();
  if (userInfo) {
    loginStore.isLogin = true;
  }
})
</script>

<template>
  <van-config-provider theme="dark">
    <div class="index">
      <RouterView />
    </div>
  </van-config-provider>
</template>

<style scoped></style>
