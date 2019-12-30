package job.controller;

import job.entity.Permission;
import job.entity.User;
import job.service.IUserService;
import job.vo.ReturnMsg;
import job.vo.ReturnMsgPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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


    @ResponseBody
    @RequestMapping("/save")
    public ReturnMsg save(@RequestParam("roleId") String roleId, User user) {
        ReturnMsg msg = new ReturnMsg();
        if (user != null) {
            try {
                boolean isTrue = iUserService.save(user);
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

    @ResponseBody
    @RequestMapping("/removebyid")
    public ReturnMsg removeById(@RequestParam("id") int id) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iUserService.removeById(id);
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
    @RequestMapping("/updatebyid")
    public ReturnMsg updateById(User user) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iUserService.updateById(user);
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
    @RequestMapping("/updatestate")
    public ReturnMsg updateState(@RequestParam("id") int id, @RequestParam("state") int state) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iUserService.updateState(id, state);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("修改用户状态成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("修改用户状态失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("修改用户状态异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @RequestMapping("/getbyid")
    public ReturnMsg getById(@RequestParam("id") int id) {
        ReturnMsg msg = new ReturnMsg();
        try {
            User user = iUserService.getById(id);
            if (user != null) {
                msg.setStatus("200");
                msg.setStatusMsg("通过id查询单个用户成功");
                msg.setMsg(user);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("通过id查询单个用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("通过id查询单个用户异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @RequestMapping("/finduserbyemail")
    public ReturnMsg findUserByEmail(@RequestParam("email") String email) {
        ReturnMsg msg = new ReturnMsg();
        try {
            User user = iUserService.findUserByEmail(email);
            if (user != null) {
                user.setPassword(null);
                msg.setStatus("200");
                msg.setStatusMsg("通过email查询单个用户成功");
                msg.setMsg(user);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("通过email查询单个用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("通过email查询单个用户异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @RequestMapping("/selectbypageandcondition")
    public ReturnMsgPage selectByPageAndCondition(User user, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ReturnMsgPage msg = new ReturnMsgPage();
        try {
            List<User> roleList = iUserService.selectByPageAndCondition(user, page, pageSize);
            int totalSize = iUserService.selectCount();
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
                msg.setPageEnd(pageEnd);
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

    @ResponseBody
    @RequestMapping("/getpermission")
    public ReturnMsg getPermission(@RequestParam("telephonenumber") String telephonenumber) {
        ReturnMsg msg = new ReturnMsg();
        try {
            List<Permission> permissionList = iUserService.getPermission(telephonenumber);
            if (permissionList != null) {
                msg.setStatus("200");
                msg.setStatusMsg("获取用户权限成功");
                msg.setMsg(permissionList);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("获取用户权限失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("获取用户权限异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

}

