<template>
    <div id="uploadFile">
     点击上传
 
		<el-form-item :label="uploadParams.label" :label-width="uploadParams.labelWidth" :id="uploadParams.key">
			<el-upload 
			    :data="paramsData"
				:ref="uploadParams.key"
	    	    class="upload-demo"
	    	    :before-upload="beforeUploadFile"
	    	    :action="actionUrl"
	    	    :on-preview="handlePreview"
	    	    :on-success="handleSuccess"
	    	    :on-error="handleError"
	    	    :before-remove="beforeRemove"
	    	    :on-remove="handleRemove"
				:disabled="uploadParams.disabled?uploadParams.disabled: false"
	    	    :limit="uploadParams.limit?uploadParams.limit:1"
	    	    :on-exceed="handleExceed"
	    	    :file-list="uploadParams.fileList">
	    		<el-button size="small" :disabled="uploadParams.disabled?uploadParams.disabled: false" type="primary">点击上传</el-button>
	    		<div v-if="uploadParams.showTip?uploadParams.showTip: false" slot="tip" class="el-upload__tip" style="word-break:break-all;"> {{`只能上传${uploadParams.type}文件，且不超过${uploadParams.fileSize}M`}} </div>
	    		<div v-if="uploadParams.showTip2?uploadParams.showTip2: false" slot="tip" class="el-upload__tip" style="word-break:break-all;color:red"> {{`只能上传${uploadParams.limit}个${uploadParams.type}文件，再次上传需要删除之前的模板`}} </div>
	    	</el-upload>
		</el-form-item>
	</div>
</template>
<script>
// ===============上传文件组件使用=================
// =========嵌套在el-form中调用组件： <el-from>  <uploadFile :uploadParams="传递参数" @getFileIdReturn="接受上传文件id数组回传"></uploadFile>  </el-form>
// =========  uploadParams中参数说明： label: form的label，labelWidth： form的label-width， key： 指定的组件标识,
// ================================    fileList: 存放文件数组，limit：存放限制上传数量,
// ================================	  type: 附件类型(.xxx)，多种以英文逗号分隔， fileSize： 附件大小（M为单位）
// ================================   , showTip: 是否展示提示信息
// 
// import fileAPI from "@/api/fileApi";
// import Config from "@/settings";
// export default {
// 	name: "uploadFile",
// 	props: {
// 		uploadParams: Object,
// 		paramsData:Object
// 	},
// 	data() {
// 		return {
// 			fileIdList: [],//存放附件id数组
// 			actionUrl: process.env.VUE_APP_BASE_API + "xxxx/xxxx",//文件上传地址
// 			removeUrl: 'xxxx/xxx',// 删除文件地址
// 		}
// 	},
// 	created() {},
// 	methods: {
// 		// 文件上传成功时的钩子
//     	handleSuccess(res, file, fileList) {
//     		this.$message.success("文件上传成功");
//     		let filename = fileList.map(item => {
//     			return item.name;
//     		});
// 			this.fileIdList.push(res.data.fid);
// 			this.$emit("getFileIdReturn", this.fileIdList);//返回文件id数组
// 		},
// 		// 文件上传失败时的钩子
// 		handleError() {
// 			this.$message.error("文件上传失败");
// 		},
// 		handleExceed(files, fileList) {
//     	//上传超过限制个数
// 			this.$message.warning(`当前限制选择 ${this.uploadParams.limit} 个文件，本次选择了 ${ files.length } 个文件，
// 				共选择了 ${files.length + fileList.length} 个文件`
//     		);
//     	},
//     	beforeRemove(file, fileList) {
//     		if(file.url==undefined){
//     			return;
//     		}
//     		// 移除前钩子
//     		return this.$confirm(`确定移除 ${file.name}？`)
//     	},
 
//     	// 上传文件之前的钩子, 参数为上传的文件,若返回 false 或者返回 Promise 且被 reject，则停止上传
//     	beforeUploadFile(file) {
//     		console.log('before upload')
//     		// console.log(file)
//     		const extension = file.name.substr(file.name.lastIndexOf('.') + 1);
//     		console.log('extension', extension)
// 			const size = file.size / 1024 / 1024;
// 			let temp = this.uploadParams.type.split(",");
// 			let typeArr = [];
// 			temp.forEach(element => {
// 				typeArr.push(element.substr(element.lastIndexOf('.') + 1));
// 			});
//     		if (!(typeArr.includes(extension)) ) {
//     			this.$message.warning(`只能上传后缀是${this.uploadParams.type}的文件`)
//     			return false
//     		}
//     		if (size > this.uploadParams.fileSize) {
//     			this.$message.warning(`文件大小不得超过${this.uploadParams.fileSize}M`)
//     			return false
//     		}
//     		return extension && size
//     	},
//     	handleRemove(file, fileList) {
//     		// 移除附件时钩子
// 			console.log(file);
//     		let id; 
// 			if(file.url) {
// 				id = file.url.split("id=")[1];
// 			}else if(file.response) {
// 				id = file.response.data.fid;
// 			}else {
// 				this.uploadParams.fileList = fileList;
// 				return
// 			}
//     		window.request({
// 				url: this.removeUrl,
// 				data: {
// 					ids: id
// 				},
//     			success: (res) => {
// 					if(res.code === 200) {
// 						this.$message.success("删除成功！");
// 						this.uploadParams.fileList = fileList;
// 						this.fileIdList.forEach((element, index) =>{
// 							if(element == id) {
// 								this.fileIdList.splice(index, 1);
// 							}
// 						});
//             			this.handLoadingShow(false)
// 						this.$emit("getFileIdReturn", this.fileIdList);//返回文件id数组
// 					}
//     			}
//     		})
//     	},
//     	handlePreview(file) {
//     		//点击文件列表中已上传的文件时的钩子
// 			  console.log(file);
// 			let fileurl;
// 			if(file.url) {
// 				fileurl = Config.downUrl + file.url;
// 			}else {
// 				fileurl = Config.downUrl + process.env.VUE_APP_BASE_API + "xxx/下载地址" + file.response.data.fid;
// 			}
// 			window.location.href = fileurl;
// 		},
// 		// 上传控件的回显====回显加载时使用
//     	loadFileList (urlAndFid) {//参数url加文件
//     		let templateList = null;
//     		const downloadUrl = 'http://10.100.76.102:8080/api'
// 			this.uploadParams.fileList = [];
// 			this.fileIdList = [];
//     		window.request({
//     			url: urlAndFid,
//     			success: ({ data }) => {
//     				templateList = data;
//     				templateList.forEach((element, index) => {
//     					const obj = {};
//     					obj.name = templateList[index].name;
//     					obj.url = downloadUrl + templateList[index].id;
//     					obj.id = templateList[index].id;
// 						this.uploadParams.fileList.push(obj);
// 						this.fileIdList.push(obj.id);
// 					});
// 					this.$emit("getFileIdReturn", this.fileIdList);//返回文件id数组
//     			}
//     		});
// 		},
// 		loadFileList2 (fileUrl, fileParam) {//url: fileUrl,参数fileParam
//     		let templateList = null;
//     		const downloadUrl = 'http://10.100.76.102:8080/api'
// 			this.uploadParams.fileList = [];
// 			this.fileIdList = [];
//     		window.request({
// 				url: fileUrl,
// 				data: fileParam,
//     			success: ({ data }) => {
//     				templateList = data;
//     				templateList.forEach((element, index) => {
//     					const obj = {};
//     					obj.name = templateList[index].name;
//     					obj.url = downloadUrl + templateList[index].id;
//     					obj.id = templateList[index].id;
// 						this.uploadParams.fileList.push(obj);
// 						this.fileIdList.push(obj.id);
// 					});
// 					this.$emit("getFileIdReturn", this.fileIdList);//返回文件id数组
//     			}
//     		}); 
//     	},
// 	}
// }
</script>