import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import Components from 'unplugin-vue-components/vite';
import { VantResolver } from '@vant/auto-import-resolver';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    Components({
      resolvers: [VantResolver()],
    }),
  ],
  base: './', // 使用相对路径
  build: {
    // 设置输出目录，将构建后的文件输出到指定目录，可以是相对或绝对路径
    outDir: '../', // 默认是 'dist'

    // 配置公共路径，如果你的项目部署在子目录下，可以设置为 './subdirectory/'
    // base: '/subdirectory/', // 默认是 '/'

    // 自定义输出文件名，可以包含哈希值以防止缓存问题
    assetsDir: 'lib', // 默认是 'assets'

    // 是否开启压缩，默认是 true
    minify: 'esbuild',

    // 设置目标浏览器，可以根据需要配置，例如 'chrome58'
    target: 'esnext',

    // 在打包时将构建信息写入文件，用于分析构建结果，默认是 false
    write: true, // 默认是 true
  },
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
