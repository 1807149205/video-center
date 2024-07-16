<script setup lang="ts">
import router from '@/router'
import { showToast } from 'vant'
import { reactive, ref } from 'vue'
import HttpClient from '@/utils/HttpClient'
import axios from 'axios'
import { useUserStore } from '@/stores/user'
import VideoUpload from '@/components/VideoUpload.vue'

const userStore = useUserStore();
const uploadParma = reactive({
  userId: userStore.userInfo.id,
  videoName: ''
})

const uploadLoading = ref(false);

const onClickLeft = () => {
  router.back();
}

const beforeRead = async (file) => {
  uploadLoading.value = true;
  if (!uploadParma.videoName) {
    showToast('视频名称为空');
    uploadLoading.value = false;
    return false;
  }
  if (file.type !== 'video/mp4') {
    showToast('请上传 mp4 格式图片');
    uploadLoading.value = false;
    return false;
  }
  try {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('userId', userStore.userInfo.id);
    formData.append('videoName', uploadParma.videoName);
    const resp = await axios({
      method: 'post',
      url: `${HttpClient.getUrl()}/video/upload`,
      data: JSON.stringify(formData),
    })
  } finally {
    uploadLoading.value = false;
  }
  return true;
};

const uploadHandler = (videoId: string) => {
  console.log(videoId,'videoId')
}

</script>

<template>
  <van-nav-bar
    title="发布视频"
    left-text="返回"
    left-arrow
    @click-left="onClickLeft"
  />
<div class="postVideoContent">
  <van-cell-group inset>
    <!-- 输入任意文本 -->
    <van-field v-model="uploadParma.videoName" label="视频标题" />
  </van-cell-group>
  <div class="uploadContainer">
    <van-uploader :before-read="beforeRead" accept="video/*">
      <van-button icon="plus" type="primary" :loading="uploadLoading">上传文件</van-button>
    </van-uploader>
<!--    <VideoUpload :data="uploadParma" @uploadEmit="uploadHandler"/>-->
  </div>
</div>
</template>

<style scoped>
.postVideoContent {
  padding: 2rem;
}
.uploadContainer {
  height: 80px;
  display: flex;
  justify-content: center;
  margin-top: 10px;
}
</style>