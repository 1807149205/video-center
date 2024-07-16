import { ref, computed, reactive } from 'vue'
import { defineStore } from 'pinia'
import type { User } from '@/model/user'

export const useUserStore = defineStore('user', () => {
  const userInfo = reactive<User>({
    id: '',
    username: '',
    password: '',
    createDate: '',
    updateDate: ''
  })

  return { userInfo }
})
