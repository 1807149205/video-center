<script setup lang="ts">
import router from '@/router'
import { showToast } from 'vant'
import {computed, onMounted, reactive, ref} from 'vue'
import HttpClient from '@/utils/HttpClient'
import axios from 'axios'
import { useUserStore } from '@/stores/user'
import VideoUpload from '@/components/VideoUpload.vue'

const userStore = useUserStore();

const videoName = ref('');
const uploadLoading = ref(false);
const categorySelectPopupVis = ref(false);
const selectedCategory = ref();
const selectedCategoryArr = ref([]);

let options = [
  {
    text: '浙江省',
    value: '330000',
    children: [{ text: '杭州市', value: '330100' }],
  },
  {
    text: '江苏省',
    value: '320000',
    children: [{ text: '南京市', value: '320100' }],
  },
];

const videoPostBtnDisabled = computed(() => {
  return (!videoName.value || selectedCategoryArr.value.length <= 1);
})

const categorySelectHandle = ({ selectedOptions }) => {
  const selected = selectedOptions[selectedOptions.length - 1];
  let isCategoryNew = !selectedCategoryArr.value.some(category => category.value === selected.value);

  if (isCategoryNew) {
    selectedCategoryArr.value.push(selected);
  }

  selectedCategory.value = null;
  categorySelectPopupVis.value = false;
}

const onClickLeft = () => {
  router.push('/my');
}

const uploadHandler = async (videoId: string) => {
  uploadLoading.value = true;
  console.log(videoId,'videoId')
  try {
    await HttpClient.post('/video/upload2', JSON.stringify({
      videoId: videoId,
      userId: userStore.userInfo.id,
      videoName: videoName.value,
      categoryIds: selectedCategoryArr.value.map((category) => category.value)
    }))
    showToast({ type: 'success', message: '添加成功！' })
  } finally {
    uploadLoading.value = false;
  }
}

const initPage = async () => {
  options = await HttpClient.get('category/getAll');
}

onMounted(async () => {
  await initPage();
})


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
    <van-field v-model="videoName" label="视频标题" />
    <van-field label="选择分类" @click="categorySelectPopupVis = true"/>
  </van-cell-group>

  <van-popup v-model:show="categorySelectPopupVis" round position="bottom">
    <van-cascader
        v-model="selectedCategory"
        title="请选择所在地区"
        :options="options"
        @close="categorySelectPopupVis = false"
        @finish="categorySelectHandle"
    />
  </van-popup>

  <div class="tagsContainer">
    <van-tag plain closeable type="primary" size="medium" v-for="c in selectedCategoryArr">
      {{c.text}}
    </van-tag>
  </div>

  <div class="uploadContainer">
    <!--    <van-uploader :before-read="beforeRead" accept="video/*">-->
    <!--      <van-button icon="plus" type="primary" :loading="uploadLoading">上传文件</van-button>-->
    <!--    </van-uploader>-->
    <VideoUpload :btn-disabled="videoPostBtnDisabled" @uploadEmit="uploadHandler" />
  </div>

  <div>
    <span>请至少选择两个视频分类</span>
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
.tagsContainer {
  margin-top: 2rem;
}
.tagsContainer > * {
  margin-left: 0.5rem;
}
</style>