<template>
  <div>
    <el-input
      v-model="input"
      placeholder="请输入内容"
      style="width: 200px"
      @keyup.enter.native="search"
    ></el-input>
    <el-button type="primary" style="margin-left: 20px" @click="search"
      >搜索</el-button
    >
    <ul>
      <li v-for="(card, index) in list" :key="index">
         {{ card }}
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  data () {
    return {
      input: '',
      list: []
    }
  },
  methods: {
     search () {
       this.$axios.get('/Code_Info/select').then(response => {
          console.log(response.data);
          this.list = response.data
          }, response => {
          console.log("error");
      });
    }
  }
}
</script>