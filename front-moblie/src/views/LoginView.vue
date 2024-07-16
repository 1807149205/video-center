<script setup lang="ts">
import { ref } from 'vue'
import router from '@/router'
import { useLoginStore } from '@/stores/login'
import { useUserStore } from '@/stores/user'
import UserStorage from '@/utils/UserStorage'

const loginStore = useLoginStore();
const userStore = useUserStore();

const username = ref('');
const password = ref('');
const loginLoading = ref(false);
const onSubmit = async (values) => {
  loginLoading.value = true;
  const userData = await loginStore.loginHandler(values.username, values.password);
  if (userData) {
    userStore.userInfo = userData;
    UserStorage.setUserInfo(userData);
    loginStore.isLogin = true;
    await router.push('/my')
  }
  loginLoading.value = false;
};

const onClickLeft = () => {
  router.back();
}
</script>

<template>
  <van-nav-bar
    title="标题"
    left-text="返回"
    left-arrow
    @click-left="onClickLeft"
  />
  <van-form @submit="onSubmit" style="margin-top: 15px;">
    <van-cell-group inset>
      <van-field
        v-model="username"
        name="username"
        label="用户名"
        placeholder="用户名"
        :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
        v-model="password"
        type="password"
        name="password"
        label="密码"
        placeholder="密码"
        :rules="[{ required: true, message: '请填写密码' }]"
      />
    </van-cell-group>
    <div style="margin: 16px;">
      <van-button round block type="primary" native-type="submit" :loading="loginLoading">
        提交
      </van-button>
    </div>
  </van-form>
</template>

<style scoped>

</style>