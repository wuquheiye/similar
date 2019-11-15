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
import swtech.pageDesignControl.entity.User;
import swtech.pageDesignControl.entity.Users;

/**
 * Created by Administrator on 2018/6/24 0024.
 */
@RestController
public class UsersController {

    @PostMapping("/login")
    public String login(Users user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        try {
            subject.login(token);
            return "登录成功";
        } catch (AuthenticationException e) {
            return e.getMessage();
        }
    }

    @RequiresRoles("user")
    @GetMapping("/testRoles")
    public String testRoles(){
        return  "testRole success";
    }

    @RequiresPermissions("user:add")
    @GetMapping("/a")
    public String testPermissions(){
        return  "testPermissions success";
    }
}

