package swtech.pageDesignControl.controller;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import swtech.pageDesignControl.common.vo.LoginVO;
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
@Slf4j
@Controller
public class LoginController {

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
        boolean isTrue = iUsersService.save(users);
        if (isTrue) {
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
    @RequestMapping("/dologin")
    @ResponseBody
    public String doLogin(Users users) {
        JSONObject json = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(users.getUusername(), users.getUpassword());
        try {
            //主体提交登录请求到SecurityManager
            subject.login(token);
            // 缓存到shiro
            LoginVO loginUser = iUsersService.getLoginVO(users.getUusername());
            subject.getSession().setAttribute("loginUser", loginUser);
            json.put("result", "登陆成功");
            return json.toString();
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
    }

    /**
     * @method 注销（logout方法自动清空shiro相关用户缓存)
     * @author Mr yi
     * @time 2019年5月6日
     * @return
     */
    @RequestMapping("/logout")
    public String logout1() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        LoginVO loginUser = (LoginVO) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        log.info(" 用户"+loginUser.getUsers().getUusername()+"注销成功 ！");
        return "use/login";
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping("/getusers")
    @ResponseBody
    public LoginVO getUser() {
        LoginVO loginUser = (LoginVO) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        return loginUser;
    }
}

