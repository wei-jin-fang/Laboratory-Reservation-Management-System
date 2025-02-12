<template>
    <div class="not-found">
      <h1>404</h1>
      <p>页面未找到</p>
      <router-link to="/">返回首页</router-link>
    </div>
  </template>
  
  <script>
  export default {
    name: 'NotFoundView'
  }
  </script>
  
  <style scoped>
  .not-found {
    text-align: center;
    margin-top: 50px;
  }
  .not-found h1 {
    font-size: 3em;
  }
  </style>
  