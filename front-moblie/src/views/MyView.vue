<script setup lang="ts">

import { useLoginStore } from '@/stores/login'
import router from '@/router'
import { showConfirmDialog, showDialog } from 'vant'
import UserStorage from '@/utils/UserStorage'
import { useUserStore } from '@/stores/user'

const userStore = useUserStore();
const loginStore = useLoginStore();

const logout = () => {
  showConfirmDialog({
    title: '标题',
    message:
      '确定要注销？',
  })
    .then(() => {
      UserStorage.clean();
      loginStore.isLogin = false;
    })
    .catch(() => {})
}

</script>

<template>
  <div style="padding: 2rem">

    <div class="topCard">
      <template v-if="!loginStore.isLogin">
        <div style="font-size: 16px" @click="router.push('/login')">
          当前用户未登录，请登录后再操作 >
        </div>
      </template>
      <template v-else>
        <div>
          <p style="margin: 0">已登录</p>
          <span>当前登录账号:</span> <span style="color: #96a2c3"> {{ userStore.userInfo.username }} </span>
        </div>
      </template>
    </div>

    <div class="buttonGroup">
      <van-cell title="退出当前账号" is-link @click="logout" v-if="loginStore.isLogin"/>
    </div>
  </div>

</template>

<style scoped>
.topCard {
  width: 100%;
  height: 80px;
  background: #202020;
  border-radius: 15px;
  box-sizing: border-box;
  padding: 15px;
}

.buttonGroup {
  margin-top: 20px;
}
</style>
