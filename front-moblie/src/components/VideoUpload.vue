<script lang="ts" setup>
import { ref } from 'vue';
import { message } from 'ant-design-vue';
import type { UploadChangeParam, UploadProps } from 'ant-design-vue';
import HTTPClient from "@/utils/HTTPClient";

const props = defineProps({
  btnDisabled: {
    required: true,
    type: Boolean
  }
})

const emit = defineEmits(['uploadEmit'])

const uploadEmit = (req: string) => {
  // 触发自定义事件，并将值作为参数传递
  emit('uploadEmit', req);
};

function getBase64(img: Blob, callback: (base64Url: string) => void) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result as string));
  reader.readAsDataURL(img);
}

const fileList = ref([]);
const loading = ref<boolean>(false);
const imageUrl = ref<string>('');

const handleChange = (info: UploadChangeParam) => {
  if (info.file.status === 'uploading') {
    loading.value = true;
    return;
  }
  if (info.file.status === 'done') {

    // Get this url from response in real world.
    getBase64(info.file.originFileObj!, (base64Url: string) => {
      imageUrl.value = base64Url;
      loading.value = false;
    });

    if (info.file.response.code == 200) {
      message.success("上传成功！");
      uploadEmit(info.file.response.data);
    } else {
      uploadFileFail();
    }
  }
  if (info.file.status === 'error') {
    loading.value = false;
    uploadFileFail();
  }
};

const uploadFileFail = () => {
  message.error('上传失败！');
}

const beforeUpload = (file: UploadProps['fileList'][number]) => {
  return true;
};
</script>
<template>
  <a-upload v-model:file-list="fileList"
            name="file"
            :show-upload-list="false"
            :action="HTTPClient.getUrl() + '/video/upload1'"
            :before-upload="beforeUpload"
            @change="handleChange">
    <VanButton type="primary" :disabled="props.btnDisabled">上传视频</VanButton>
  </a-upload>
</template>
<style scoped>
.avatar-uploader>.ant-upload {
  width: 128px;
  height: 128px;
}

.ant-upload-select-picture-card i {
  font-size: 32px;
  color: #999;
}

.ant-upload-select-picture-card .ant-upload-text {
  margin-top: 8px;
  color: #666;
}
</style>