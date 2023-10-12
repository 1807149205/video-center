<script setup lang="ts">
import router from '@/router';
import { onMounted } from 'vue';
import { watch } from 'vue';
import { ref } from 'vue';

const active = ref(0);

const changeActive = (val: string) => {
    if (val === '/') {
        active.value = 0;
    }
    if (val === '/my') {
        active.value = 1;
    }
}

watch(() => router.currentRoute.value.fullPath, (val) => {
    changeActive(val);
})

onMounted(() => {
    changeActive(router.currentRoute.value.fullPath);
})

</script>

<template>
<div class="tabbar-container">
    <van-tabbar v-model="active" active-color="#ee0a24">
        <van-tabbar-item icon="video-o" replace to="/">主页</van-tabbar-item>
        <van-tabbar-item icon="friends-o" replace to="/my">我的</van-tabbar-item>
    </van-tabbar>
</div>
</template>

<style scoped>
.tabbar-container {

}
</style>