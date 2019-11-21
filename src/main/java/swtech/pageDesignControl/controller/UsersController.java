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
import swtech.pageDesignControl.common.utils.DateUtil;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.common.vo.ReturnMsgPage;
import swtech.pageDesignControl.entity.Users;
import swtech.pageDesignControl.service.IUsersService;

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
@Slf4j
@Controller
public class UsersController {

    @Resource
    private IUsersService iUsersService;

    @ResponseBody
    @GetMapping("/manage/users/save")
    public ReturnMsg save(Users users) {
        users.setUcreationtime(DateUtil.getNewDate());
        users.setUstate("1");
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iUsersService.save(users);
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
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/users/removebyid")
    public ReturnMsg removeById(@RequestParam("uid") int uid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iUsersService.removeById(uid);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("删除用户成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("删除用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("删除用户异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/users/updatebyid")
    public ReturnMsg updateById(Users users) {
        users.setUstate("1");
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iUsersService.updateById(users);
            if (isTrue) {
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

    @ResponseBody
    @GetMapping("/manage/users/selectbyid")
    public ReturnMsg selectById(@RequestParam("uid") int uid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            Users users = iUsersService.selectById(uid);
            if (users != null) {
                msg.setStatus("200");
                msg.setStatusMsg("查询单个用户成功");
                msg.setMsg(users);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("查询单个用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("查询单个用户异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/users/selectbypageandcondition")
    public ReturnMsgPage selectByPageAndCondition(Users users, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ReturnMsgPage msg = new ReturnMsgPage();
        try {
            List<Users> roleList = iUsersService.selectByPageAndCondition(users, page, pageSize);
            int totalSize = iUsersService.selectCount();
            int totalPage = (int) Math.ceil(1.0 * totalSize / pageSize);
            int pageEnd = page * pageSize < pageSize ? page * pageSize : pageSize;
            if (roleList != null) {
                msg.setStatus("200");
                msg.setMsg(roleList);
                msg.setPageSize(pageSize);
                msg.setStatusMsg("获取用户条件分页成功");
                msg.setTotalPage(totalPage);
                msg.setPageStart((page - 1) * pageSize);
                msg.setTotalSize(totalSize);
                msg.setPageEnd(page * pageSize);
                msg.setCurrentPage(page);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("获取用户条件分页失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("获取用户条件分页异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

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

}

