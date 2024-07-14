<script setup lang="ts"></script>

<template>
  <div>VideoView</div>
  <video controls style="width: 100%">
    <source src="http://127.0.0.1:8212/video/video?start=0" type="video/mp4">
    Your browser does not support the video tag.
  </video>
</template>

<style scoped></style>
