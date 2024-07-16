import { createRouter, createWebHashHistory } from 'vue-router'
// @ts-ignore
import MainView from '@/views/MainView.vue'
import VideoView from '@/views/VideoView.vue'
import MyView from '@/views/MyView.vue'
import LoginView from '@/views/LoginView.vue'
import PostVideoView from '@/views/PostVideoView.vue'

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      name: 'main',
      component: MainView,
      children: [
        {
          path: '/',
          name: 'VideoView',
          component: VideoView
        },
        {
          path: '/my',
          name: 'My',
          component: MyView
        },
        {
          path: '/postVideo',
          name: 'PostVideo',
          component: PostVideoView
        }
      ],
    },
    {
      path: '/login',
      name: 'Login',
      component: LoginView
    }
    // {
    //   path: '/about',
    //   name: 'about',
    //   // route level code-splitting
    //   // this generates a separate chunk (About.[hash].js) for this route
    //   // which is lazy-loaded when the route is visited.
    //   component: () => import('../views/AboutView.vue')
    // }
  ]
})

export default router
