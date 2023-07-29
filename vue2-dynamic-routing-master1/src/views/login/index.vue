<template>
	<div class="wrapper">
		<!-- 登录 -->
		<div class="modal" v-show="modal">
			<el-form :model="user" status-icon :rules="rules" ref="userForm">
				<div class="title">Code Acquisition System</div>
				<el-form-item prop="username">
					<el-input type="text" prefix-icon="el-icon-user" placeholder="请输入用户名" v-model="user.username" />
				</el-form-item>
				<el-form-item prop="password">
					<el-input type="password" prefix-icon="el-icon-view" placeholder="请输入密码" v-model="user.password" />
				</el-form-item>
				<el-form-item>
					<el-button type="primary" class="btn-login" @click="checkShow">注册</el-button>
					<el-button type="primary" class="btn-login" @click="login">登录</el-button>
				</el-form-item>
			</el-form>
		</div>

		<!-- 注册 -->
		<div v-show="register">
        	<el-form ref="registerForm" :model="form" :rules="RegisterRules" label width=" 80px" class="register-box">
        	    <h3 class="register-title">欢迎登录</h3>
        	    <el-form-item label="账号" prop="username">
        	        <el-input type="text" placeholder=" 请输入账号" v-model="form.username"/>
        	    </el-form-item>
        	    <el-form-item label="密码" prop="password">
        	        <el-input type="password" placeholder=" 请输入密码" v-model=" form.password"/>
        	    </el-form-item>
        	    <el-form-item label="确认密码" prop="checkPass">
        	        <el-input type="password" placeholder=" 请确认密码" v-model="form.checkPass" />
        	    </el-form-item>
        	    <el-form-item>
        	        <el-button type="primary" v-on:click="onSubmit('registerForm')">注册</el-button>
        	    </el-form-item>
        	</el-form>
        <el-dialog
                title="温馨提示"
                :visible.sync="dialogVisible"
                width="30%"
                :before-close="handleClose">
            <span>请输入账号和密码</span>
            <span slot="footer" class="dialog-footer">
			<el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      		</span>
        </el-dialog>
    </div>
	</div>
</template>
<script>
import dynamicUser from "../../mock"
import { Message } from "element-ui"

export default {
	name: 'login',
	data() {
		 var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.form.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
		return {
			user: {
				username: "liwen",
				password: "123"
			},
			form: {
                    username: '',
                    password: '',
                    checkPass: '',
                },
			rules: {
				username: [
					{ required: true, message: '请输入用户名', trigger: 'blur' }
				],
				password: [
					{ required: true, message: '请输入密码', trigger: 'blur' }
				]
			},
			// 表单验证， 需要在el-form-item- 元素中增加prop属性
            RegisterRules: {
                checkPass: [{validator: validatePass2, trigger: 'blur'}],
                username: [{required: true, message: '账号不可为空', trigger: 'blur'}],
                password: [{required: true, message: '密码不可为空', trigger: 'blur'}]
            },
			modal : true,
			//对话框显示和隐藏
            dialogVisible: false,
            register: false
		}
	},
	methods: {
		checkShow() {
			this.register = !this.register
			this.modal = !this.modal
		},
		// login() {
		// 	this.$refs.userForm.validate(( valid ) => {
		// 	    if(valid) {
		// 			let flag = !1
		// 			window.localStorage.removeItem("userInfo")
		// 			dynamicUser.forEach(item => {
		// 				if(item["username"] == this.user['username'] && item["password"] == this.user['password']) {
		// 					flag = !0
		// 					Message({ type: 'success', message: "登录成功", showClose: true, duration: 3000 })
		// 					window.localStorage.setItem("userInfo", JSON.stringify(item))
		// 					this.$router.replace({ path: "/" })
		// 				}
		// 			})
		// 			if(!flag) Message({ type: 'warning', message: "账号密码错误，请重试!", showClose: true, duration: 3000 })
		// 	    } else return false
		// 	})
		// },

		login() {
			this.$refs.userForm.validate(( valid ) => {
			    if(valid) {
					let flag = !1
					// 移除本地存储中的userInfo键值对
					window.localStorage.removeItem("userInfo")
					this.$axios.post('/CodeRepository/login', {
                            username: this.user.username,
                            password: this.user.password
                        }).then(response => {
							console.log(response.data);
                            if(response.data == "login_success"){
                                // 遍历动态数组dynamicUser中的每一个元素
					            dynamicUser.forEach(item => {
					            	// 判断用户名和密码是否与用户输入的一致
					            	if(item["username"] == this.user['username'] && item["password"] == this.user['password']) {
					            		flag = !0
										console.log(flag)
					            		Message({ type: 'success', message: "登录成功", showClose: true, duration: 3000 })
					            		// 将用户信息存储到localStorage中，
					            		window.localStorage.setItem("userInfo", JSON.stringify(item))
					            		// 然后使用$router.replace方法将路由跳转到首页
					        			this.$router.replace({ path: "/" })
                                		this.$message.success(response.data.msg)
					            	}
					            })	
                            }else{
                                console.log(response.data);
                                this.$message.error(this.user.username+' '+response.data.msg)
                            }
                        })
					// 如果没有找到匹配的用户，显示账号密码错误的提示信息
					if(flag) Message({ type: 'warning', message: "账号密码错误，请重试!", showClose: true, duration: 3000 })
			    } else return false // 如果表单验证不通过（valid为false），则直接返回false
			})
		},

		handleClose: function () {
            console.log("Handle Close，空函数");
        },
		onSubmit(formName) {
        //为表单绑定验证功能
        this.$refs [formName].validate((valid) => {
        if (valid) {
            this.$axios.post('/CodeRepository/signup', {
                username: this.form.username,
                password: this.form.password
            })
            .then(response => {
				console.log(response.data)
                if(response.data == "signup_success"){
                        //使用vue-router 路由到指定页面，该方式称之为编程式导航
                        this.modal = !this.modal
                        this.register = !this.register
                        // this.$router.push("/login");
                        this.$message.success(response.data.msg)
                    }else{
                        console.log(response.data);
                        this.$message.error(this.form.username+"已被注册")
                    }
                })
                } else {
                    this.dialogVisible = true;
                    return false;
                }
            });
        },
	}
}
</script>
<style scoped>
.wrapper {
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: center;
	background-color: #fff;
	width: 100vw;
	height: 88vh;
}
.modal {
	width: 360px;
	/* height: 380px; */
	height: 255px;
	box-shadow: 0 0 10px 5px #ddd;
	padding: 50px;
	border-radius: 5px;
}
.title {
	width: 100%;
	text-align: center;
	line-height: 1.5;
	font-size: 30px;
	margin-bottom: 30px;
}
.btn-login {
	width: 48%;
}
.toast{
	width: 100%;
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: space-between;
	height: 50px;
}
.register-box {
    border: 1px solid #DCDFE6;
    width: 350px;
    margin: 50px auto;
    padding: 35px 35px 15px 35px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 25px #909399;
}
.register-title {
    text-align: center;
    margin: 0 auto 40px auto;
    color: #303133;
}
</style>