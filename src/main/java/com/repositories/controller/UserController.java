package com.repositories.controller;
import com.repositories.dao.User;
import com.repositories.dao.Code_Info;
import com.repositories.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/CodeRepository")
public class UserController {
    @Autowired
    private UserService RPUser;
    @PostMapping("/login")
    //对前段传入的数据转化为User实体类对象
    public String login(Model model, @RequestBody User loginInfo){
        String username = loginInfo.getUsername();
        String password = loginInfo.getPassword();
        User user = RPUser.login(username);
        System.out.println(user);
        if(user==null) {
            //了解后端接收前段数据的方法和后端给前端传输数据的方法，并写好接口文档
            //参考工资管理系统是如何进行前后端数据交互的
            //查看YU的前端数据格式
            //将最新的数据导入数据库
            //设计筛选条件
            //用户不存在发送给前端消息1
            model.addAttribute("Password_error",1);
            System.out.println("用户不存在");
            return "Password_error";
        }
        else if(!password.equals(user.getPassword())){
            //密码错误发送给前端消息2
            model.addAttribute("not_exist",2);
            System.out.println("密码错误");
            return "not_exist";
        }
        else{
            //登陆成功发送给前端消息0
            System.out.println("登录成功");
            model.addAttribute("login_success",0);
            return "login_success";
        }
    }

    @PostMapping("/signup")
    public String signup(Model model,@RequestBody User signupInfo){
        String username = signupInfo.getUsername();
        String password = signupInfo.getPassword();
        if(RPUser.signup(username,password)==1){
            model.addAttribute("signup_success",1);
            System.out.println("注册成功");
            //注册成功返回登录页面
            return "signup_success";
        }
        else {
            model.addAttribute("signup_fail", 0);
            System.out.println("用户已经存在注册失败");
            //注册失败继续停留注册页面
            return "signup_fail";
        }
    }

}
