import './assets/main.css'
import 'vant/lib/index.css';
import 'ant-design-vue/dist/reset.css';
import { Upload } from 'ant-design-vue';

import { createApp } from 'vue'
import { createPinia } from 'pinia'

// @ts-ignore
import App from './App.vue'

import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(Upload);
app.mount('#app')
