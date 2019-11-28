package swtech.pageDesignControl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import swtech.pageDesignControl.common.utils.DateUtil;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.common.vo.ReturnMsgPage;
import swtech.pageDesignControl.common.vo.UsersVO;
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
    @GetMapping("/manage/users/updateuustate")
    public ReturnMsg updateUustate(@RequestParam("uid") int uid, @RequestParam("ustate") int ustate) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iUsersService.updateUustate(uid, ustate);
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
    @GetMapping("/manage/users/getbyid")
    public ReturnMsg getById(@RequestParam("uid") int uid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            Users users = iUsersService.getById(uid);
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
            List<UsersVO> roleList = iUsersService.selectByPageAndCondition(users, page, pageSize);
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

}

