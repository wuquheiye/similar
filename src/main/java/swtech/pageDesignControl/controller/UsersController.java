package swtech.pageDesignControl.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import swtech.pageDesignControl.entity.Users;

/**
 * Created by Administrator on 2018/6/24 0024.
 */
@RestController
public class UsersController {

    @PostMapping("/login")
    public String login(Users user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUusername(), user.getUpassword());
        try {
            subject.login(token);
            return "登录成功";
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
    }

    @RequiresRoles("user")
    @GetMapping("/u")
    public String testRoles(){
        return  "testRole success";
    }

    @RequiresRoles("manage")
    @GetMapping("/m")
    public String testRoles1(){
        return  "testRole success";
    }

    @RequiresPermissions("user:add")
    @GetMapping("/a")
    public String testPermissions(){
        return  "testPermissions success";
    }


    @RequiresPermissions("user:add1")
    @GetMapping("/b")
    public String testPermissions1(){
        return  "testPermissions success";
    }


    @GetMapping("/c")
    public String testPermissions2(){
        return  "testPermissions success";
    }
}

