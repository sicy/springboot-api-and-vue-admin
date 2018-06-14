<template>
  <!-- 页面 只允许一个根节点-->
  <div>{{msg}}</div>
</template>

<script>
  //代码
  export default {
    name: 'Keyword',
    mounted: function () {
        //页面加载完成后执行

    },
    methods: {
        //定义方法

    },
    data (){
      return {
          //定义数据
        msg: 'hello world'
      }
    }
  }
</script>

<style>
  /*样式*/

</style>
