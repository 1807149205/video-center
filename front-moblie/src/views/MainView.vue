<script setup lang="ts">
import TabbarComponent from '@/components/Main/TabbarComponent.vue';

</script>

<template>
<div class="main-view-container">
    <div class="main-content">
        <RouterView></RouterView>
    </div>
    <!-- 底部导航栏 -->
    <TabbarComponent></TabbarComponent>
</div>
</template>

<style scoped>
.main-view-container {

}
.main-content{
    padding-bottom: 50px;
}
</style>