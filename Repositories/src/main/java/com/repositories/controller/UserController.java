package com.repositories.controller;
import com.repositories.dao.User;
import com.repositories.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/CodeRepository")
public class UserController {
    @Autowired
    private UserService RPUser;
    //路由按照status判断
    @PostMapping("/login")
    public User login(Model model, @RequestBody User loginInfo){
        String account = loginInfo.getAccount();
        String password = loginInfo.getPassword();
        System.out.println(account);
        User user = RPUser.login(account);
        System.out.println(user);
        if(user==null) {
            model.addAttribute("not_exist",1);
            return null;
        }
        else if(!password.equals(user.getPassword())){
            model.addAttribute("passwordwrong_",2);
            System.out.println("密码错误");
            return null;
        }
        else{
            System.out.println("登录成功");
            return user;
        }
    }

    @PostMapping("/signup")
    public int signup(@RequestBody User signupInfo){
        String account = signupInfo.getAccount();
        String password = signupInfo.getPassword();
        if(RPUser.signup(account,password)==1){
            System.out.println("注册成功");
            return 1;
        }
        else {
            System.out.println("用户已经存在注册失败");
            return 0;
        }
    }



    @PostMapping("/set_info")
    public void setInfo(Model model,@RequestBody User extraInfo){
        String account = extraInfo.getAccount();
        String username = extraInfo.getUsername();
        String email = extraInfo.getEmail();
        String phone = extraInfo.getPhone();
        String address = extraInfo.getAddress();
        String school = extraInfo.getSchool();
        System.out.println("username的值为"+username);
        System.out.println(extraInfo);
        RPUser.setInfo(account,username,email,phone,address,school);
    }

    @GetMapping("/getAll")
    public List<User> getAll(){
        List<User> allInfo =  RPUser.getAll();
        return allInfo;
    }

}
