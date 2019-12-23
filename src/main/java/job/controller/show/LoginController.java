package job.controller.show;

import job.utils.DateUtil;
import job.vo.LoginVO;
import job.vo.ReturnMsg;
import job.entity.User;
import job.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-19
 */
@CrossOrigin //跨域
@Slf4j
@Controller
public class LoginController {

    @Resource
    private IUserService iUserService;

    @ResponseBody
    @RequestMapping("/doregist")
    public ReturnMsg save(@RequestParam("roleId") int roleId, User user) {
        ReturnMsg msg = new ReturnMsg();
        // 判断邮箱
        if(user.getEmail() == null || "".equals(user.getEmail())){
            msg.setStatus("203");
            msg.setStatusMsg("邮箱非法");
            return msg;
        }
        // 判断角色
        if(roleId != 1 && roleId != 2){
            roleId = 1;
        }
        user.setCreationtime(DateUtil.getNewDate());
        user.setState("1");
        user.setMoney("0");
        if (user != null) {
            try {
                boolean isTrue = iUserService.regist(user,roleId);
                if (isTrue) {
                    msg.setStatus("200");
                    msg.setStatusMsg("新建用户成功");
                } else {
                    msg.setStatus("202");
                    msg.setStatusMsg("新建用户失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                msg.setStatus("201");
                msg.setStatusMsg("新建用户异常");
                msg.setMsg(e.getMessage());
            }
        } else {
            msg.setStatus("220");
            msg.setStatusMsg("验证码不匹配");
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    /**
     * 发送验证码
     *
     * @return
     */
    @RequestMapping("/setverificationcode")
    @ResponseBody
    public ReturnMsg setVerificationCode(@RequestParam("email") String email) {
        ReturnMsg msg = new ReturnMsg();
        try {
            String verificationCode = iUserService.sendEmail(email);
            if (verificationCode != null && !"".equals(verificationCode)) {
                // 获取session中数据
                msg.setStatus("200");
                msg.setStatusMsg("发送验证码成功");
                msg.setMsg(verificationCode);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("发送验证码失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("发送验证码异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping("/dologin")
    @ResponseBody
    public ReturnMsg doLogin(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getEmail(), user.getPassword());
        ReturnMsg msg = new ReturnMsg();
        try {
            //主体提交登录请求到SecurityManager
            subject.login(token);
            // 缓存到shiro
            LoginVO loginUser = iUserService.getLoginVO(user.getEmail());
            if(loginUser != null && loginUser.getUser() != null){
                loginUser.getUser().setPassword(null);
            }
            subject.getSession().setAttribute("loginUser", loginUser);
            msg.setStatus("200");
            msg.setStatusMsg("登陆成功");
            msg.setMsg(loginUser);
            return msg;
        } catch (IncorrectCredentialsException ice) {
            msg.setStatus("201");
            msg.setStatusMsg("密码不正确");
            return msg;
        } catch (UnknownAccountException uae) {
            msg.setStatus("202");
            msg.setStatusMsg("账号不存在");
            return msg;
        } catch (AuthenticationException ae) {
            msg.setStatus("202");
            msg.setStatusMsg("状态不正常");
            return msg;
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
        log.info(" 用户"+loginUser.getUser().getUsername()+"注销成功 ！");
        return "use/login";
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping("/getuser")
    @ResponseBody
    public LoginVO getUser() {
        LoginVO loginUser = (LoginVO) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
        return loginUser;
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/forgetpassword")
    public ReturnMsg updateById(User user) {
        ReturnMsg msg = new ReturnMsg();
        if(user.getEmail() == null){
            msg.setStatus("203");
            msg.setStatusMsg("邮箱不能为空");
            return msg;
        }
        try {
            int isTrue = iUserService.forgetpassword(user);
            if (isTrue > 0) {
                msg.setStatus("200");
                msg.setStatusMsg("修改用户成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("修改用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("修改用户异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}

