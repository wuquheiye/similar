package job.controller;

import job.entity.User;
import job.service.IUserService;
import job.vo.LoginVO;
import job.vo.ReturnMsg;
import job.vo.ReturnMsgPage;
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
import java.util.List;

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
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService iUserService;

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
        UsernamePasswordToken token = new UsernamePasswordToken(user.getTelephonenumber(), user.getPassword());
        ReturnMsg msg = new ReturnMsg();
        try {
            // 主体提交登录请求到SecurityManager
            subject.login(token);
            // 缓存到shiro
            LoginVO loginUser = iUserService.getLoginVO(user.getTelephonenumber());
            if (loginUser != null && loginUser.getUser() != null && loginUser.getRole() != null) {
                loginUser.getUser().setPassword(null);
                if (loginUser.getUser().getState() == 0) {
                    msg.setStatus("300");
                    msg.setStatusMsg("由于违规操作，此账号已被禁用，请联系客服。");
                    msg.setMsg(loginUser);
                    return msg;
                } else {
                    subject.getSession().setAttribute("loginUser", loginUser);
                    msg.setStatus("200");
                    msg.setStatusMsg("登陆成功");
                    msg.setMsg(loginUser);
                    return msg;
                }
            }else{
                msg.setStatus("202");
                msg.setStatusMsg("账号异常，请联系客服。");
                msg.setMsg(loginUser);
                return msg;
            }
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
     * 通过电话号码获取用户
     *
     * @param telephonenumber
     * @return
     */
    @ResponseBody
    @RequestMapping("/finduserbytelephonenumber")
    public ReturnMsg findUserByTelephonenumber(@RequestParam("telephonenumber") String telephonenumber) {
        ReturnMsg msg = new ReturnMsg();
        try {
            User user = iUserService.findUserByTelephonenumber(telephonenumber);
            if (user != null) {
                user.setPassword(null);
                msg.setStatus("200");
                msg.setStatusMsg("通过电话号码查询单个用户成功");
                msg.setMsg(user);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("通过电话号码查询单个用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("通过电话号码查询单个用户异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    /**
     * 发送手机验证码
     *
     * @return
     */
    @RequestMapping("/sendtelephonenumberverificationcode")
    @ResponseBody
    public ReturnMsg sendTelephonenumberVerificationCode(@RequestParam("telephonenumber") String telephonenumber) {
        ReturnMsg msg = new ReturnMsg();
        try {
            String verificationCode = iUserService.sendTelephonenumberVerificationCode(telephonenumber);
            if (verificationCode != null && !"".equals(verificationCode)) {
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
     * 注册
     *
     * @param roleId
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/doregist")
    public ReturnMsg doRegist(@RequestParam("roleId") int roleId, User user) {
        ReturnMsg msg = new ReturnMsg();
        // 1.判断用户信息是否为空
        if (user == null) {
            msg.setStatus("201");
            msg.setStatusMsg("用户信息不能为空");
            return msg;
        }
        // 2.判断手机号码
        if (user.getTelephonenumber() == null || "".equals(user.getTelephonenumber())) {
            msg.setStatus("202");
            msg.setStatusMsg("手机号码不能为空");
            return msg;
        }
        // 3.判断角色
        if (roleId != 2) {
            roleId = 1;
        }
        try {
            boolean isTrue = iUserService.regist(user, roleId);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("新建用户成功");
            } else {
                msg.setStatus("203");
                msg.setStatusMsg("新建用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("204");
            msg.setStatusMsg("新建用户异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/forgetpassword")
    public ReturnMsg forgetPassword(User user) {
        ReturnMsg msg = new ReturnMsg();
        if (user.getTelephonenumber() == null) {
            msg.setStatus("203");
            msg.setStatusMsg("手机号码不能为空");
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

//    /**
//     * 发送手机号码验证码（待用）
//     *
//     * @return
//     */
//    @RequestMapping("/sendverificationcode")
//    @ResponseBody
//    public ReturnMsg sendVerificationCode(@RequestParam("email") String email) {
//        ReturnMsg msg = new ReturnMsg();
//        try {
//            String verificationCode = iUserService.sendEmail(email);
//            if (verificationCode != null && !"".equals(verificationCode)) {
//                msg.setStatus("200");
//                msg.setStatusMsg("发送验证码成功");
//                msg.setMsg(verificationCode);
//            } else {
//                msg.setStatus("202");
//                msg.setStatusMsg("发送验证码失败");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            msg.setStatus("201");
//            msg.setStatusMsg("发送验证码异常");
//            msg.setMsg(e.getMessage());
//        }
//        log.info(String.valueOf(msg));
//        return msg;
//    }
//    @ResponseBody
//    @RequestMapping("/save")
//    public ReturnMsg save(@RequestParam("roleId") String roleId, User user) {
//        ReturnMsg msg = new ReturnMsg();
//        if (user != null) {
//            try {
//                boolean isTrue = iUserService.save(user);
//                if (isTrue) {
//                    msg.setStatus("200");
//                    msg.setStatusMsg("新建用户成功");
//
//                } else {
//                    msg.setStatus("202");
//                    msg.setStatusMsg("新建用户失败");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                msg.setStatus("201");
//                msg.setStatusMsg("新建用户异常");
//                msg.setMsg(e.getMessage());
//            }
//        } else {
//            msg.setStatus("220");
//            msg.setStatusMsg("验证码不匹配");
//        }
//        log.info(String.valueOf(msg));
//        return msg;
//    }
//
//    @ResponseBody
//    @RequestMapping("/removebyid")
//    public ReturnMsg removeById(@RequestParam("id") int id) {
//        ReturnMsg msg = new ReturnMsg();
//        try {
//            boolean isTrue = iUserService.removeById(id);
//            if (isTrue) {
//                msg.setStatus("200");
//                msg.setStatusMsg("删除用户成功");
//            } else {
//                msg.setStatus("202");
//                msg.setStatusMsg("删除用户失败");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            msg.setStatus("201");
//            msg.setStatusMsg("删除用户异常");
//            msg.setMsg(e.getMessage());
//        }
//        log.info(String.valueOf(msg));
//        return msg;
//    }
//
////    @ResponseBody
////    @RequestMapping("/updatebyid")
////    public ReturnMsg updateById(User user) {
////        ReturnMsg msg = new ReturnMsg();
////        try {
////            boolean isTrue = iUserService.updateById(user);
////            if (isTrue) {
////                msg.setStatus("200");
////                msg.setStatusMsg("修改用户成功");
////            } else {
////                msg.setStatus("202");
////                msg.setStatusMsg("修改用户失败");
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////            msg.setStatus("201");
////            msg.setStatusMsg("修改用户异常");
////            msg.setMsg(e.getMessage());
////        }
////        log.info(String.valueOf(msg));
////        return msg;
////    }
//
//    @ResponseBody
//    @RequestMapping("/updatestate")
//    public ReturnMsg updateState(@RequestParam("id") int id, @RequestParam("state") int state) {
//        ReturnMsg msg = new ReturnMsg();
//        try {
//            boolean isTrue = iUserService.updateState(id, state);
//            if (isTrue) {
//                msg.setStatus("200");
//                msg.setStatusMsg("修改用户状态成功");
//            } else {
//                msg.setStatus("202");
//                msg.setStatusMsg("修改用户状态失败");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            msg.setStatus("201");
//            msg.setStatusMsg("修改用户状态异常");
//            msg.setMsg(e.getMessage());
//        }
//        log.info(String.valueOf(msg));
//        return msg;
//    }
//
//    @ResponseBody
//    @RequestMapping("/getbyid")
//    public ReturnMsg getById(@RequestParam("id") int id) {
//        ReturnMsg msg = new ReturnMsg();
//        try {
//            User user = iUserService.getById(id);
//            if (user != null) {
//                msg.setStatus("200");
//                msg.setStatusMsg("通过id查询单个用户成功");
//                msg.setMsg(user);
//            } else {
//                msg.setStatus("202");
//                msg.setStatusMsg("通过id查询单个用户失败");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            msg.setStatus("201");
//            msg.setStatusMsg("通过id查询单个用户异常");
//            msg.setMsg(e.getMessage());
//        }
//        log.info(String.valueOf(msg));
//        return msg;
//    }
//
//
//
//    @ResponseBody
//    @RequestMapping("/getpermission")
//    public ReturnMsg getPermission(@RequestParam("telephonenumber") String telephonenumber) {
//        ReturnMsg msg = new ReturnMsg();
//        try {
//            List<Permission> permissionList = iUserService.getPermission(telephonenumber);
//            if (permissionList != null) {
//                msg.setStatus("200");
//                msg.setStatusMsg("获取用户权限成功");
//                msg.setMsg(permissionList);
//            } else {
//                msg.setStatus("202");
//                msg.setStatusMsg("获取用户权限失败");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            msg.setStatus("201");
//            msg.setStatusMsg("获取用户权限异常");
//            msg.setMsg(e.getMessage());
//        }
//        log.info(String.valueOf(msg));
//        return msg;
//    }
//
//
//
//    /**
//     * @method 注销（logout方法自动清空shiro相关用户缓存)
//     * @author Mr yi
//     * @time 2019年5月6日
//     * @return
//     */
//    @RequestMapping("/logout")
//    public String logout1() {
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        LoginVO loginUser = (LoginVO) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
//        log.info(" 用户"+loginUser.getUser().getUsername()+"注销成功 ！");
//        return "use/login";
//    }
//
//    /**
//     * 获取用户信息
//     *
//     * @return
//     */
//    @RequestMapping("/getuser")
//    @ResponseBody
//    public LoginVO getUser() {
//        LoginVO loginUser = (LoginVO) SecurityUtils.getSubject().getSession().getAttribute("loginUser");
//        return loginUser;
//    }
//
//    @ResponseBody
//    @RequestMapping("/selectbypageandcondition")
//    public ReturnMsgPage selectByPageAndCondition(User user, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
//        ReturnMsgPage msg = new ReturnMsgPage();
//        try {
//            List<User> roleList = iUserService.selectByPageAndCondition(user, page, pageSize);
//            int totalSize = iUserService.selectCount(user);
//            int totalPage = (int) Math.ceil(1.0 * totalSize / pageSize);
//            int pageEnd = page * pageSize < pageSize ? page * pageSize : pageSize;
//            if (roleList != null) {
//                msg.setStatus("200");
//                msg.setMsg(roleList);
//                msg.setPageSize(pageSize);
//                msg.setStatusMsg("获取用户条件分页成功");
//                msg.setTotalPage(totalPage);
//                msg.setPageStart((page - 1) * pageSize);
//                msg.setTotalSize(totalSize);
//                msg.setPageEnd(pageEnd);
//                msg.setCurrentPage(page);
//            } else {
//                msg.setStatus("202");
//                msg.setStatusMsg("获取用户条件分页失败");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.info(e.getMessage());
//            msg.setStatus("201");
//            msg.setStatusMsg("获取用户条件分页异常");
//            msg.setMsg(e.getMessage());
//        }
//        log.info(String.valueOf(msg));
//        return msg;
//    }
}

