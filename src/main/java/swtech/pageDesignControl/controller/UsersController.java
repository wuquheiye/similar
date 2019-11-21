package swtech.pageDesignControl.controller;

import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import swtech.pageDesignControl.entity.Users;
import swtech.pageDesignControl.service.IUsersService;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-19
 */
@Controller
public class UsersController {

    @Resource
    private IUsersService iUsersService;

    /**
     * 注册
     *
     * @param users
     * @return
     */
    @PostMapping("/doregist")
    @ResponseBody
    public String doregist(@RequestBody Users users) {
        JSONObject json = new JSONObject();
        System.out.println(users);
        int num = -2;
        num = iUsersService.insert(users);
        System.out.println(num);
        if(num >0){
            json.put("result", "注册成功");
            return json.toString();
        }
        json.put("result", "注册失败");
        return json.toString();
    }

    /**
     * 登录
     *
     * @param users
     * @return
     */
    @PostMapping("/dologin")
    @ResponseBody
    public String doLogin(@RequestBody Users users) {
        JSONObject json = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(users.getUusername(), users.getUpassword());
        try {
            //主体提交登录请求到SecurityManager
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            json.put("result", "密码不正确");
            return json.toString();
        } catch (UnknownAccountException uae) {
            json.put("result", "账号不存在");
            return json.toString();
        } catch (AuthenticationException ae) {
            json.put("result", "状态不正常");
            return json.toString();
        }
        json.put("result", "登陆成功");
        return json.toString();
    }

    /**
     * 跳转登录页面
     *
     * @return
     */
    @RequestMapping("/login")
    public String login() {
        return "use/login";
    }

    @RequestMapping("/index")
    public String index(){
        return "use/index";
    }
    @RequestMapping("/manage")
    public String manage(){
        return "manage/manage";
    }

    @RequestMapping("/403")
    public String index403(){
        return "use/403";
    }

//
//
//    @RequiresRoles("user")
//    @GetMapping("/u")
//    @ResponseBody
//    public String testRoles(){
//        return  "testRole success";
//    }
//
//    @RequiresRoles("manage")
//    @GetMapping("/m")
//    @ResponseBody
//    public String testRoles1(){
//        return  "testRole success";
//    }
//
//    @RequiresPermissions("user:add")
//    @GetMapping("/a")
//    @ResponseBody
//    public String testPermissions(){
//        return  "testPermissions success";
//    }
//
//
//    @RequiresPermissions("user:add1")
//    @GetMapping("/b")
//    @ResponseBody
//    public String testPermissions1(){
//        return  "testPermissions success";
//    }
//
//
//    @GetMapping("/c")
//    @ResponseBody
//    public String testPermissions2(){
//        return  "testPermissions success";
//    }
}

