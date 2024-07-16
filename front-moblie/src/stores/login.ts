import { ref } from 'vue'
import { defineStore } from 'pinia'
import HttpClient from '@/utils/HttpClient'
import type { User } from '@/model/user'

export const useLoginStore = defineStore('login', () => {

  const isLogin = ref(false);

  const loginHandler = async (username: string, password: string) => {
    let userData: User | null;
    try {
      userData = await HttpClient.post('/user/login', { username, password });
    } catch (e) {
      console.log('登录失败');
      return null;
    }
    return userData;
  }

  return { isLogin, loginHandler }
})
