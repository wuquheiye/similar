package swtech.pageDesignControl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.UserRole;
import swtech.pageDesignControl.mapper.UserRoleMapper;
import swtech.pageDesignControl.service.IUserRoleService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-21
 */
@Slf4j
@RestController
public class UserRoleController {

    @Autowired
    private IUserRoleService iUserRoleService;

    @ResponseBody
    @GetMapping("/manage/userrole/save")
    public ReturnMsg save(UserRole userRole) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iUserRoleService.save(userRole);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("新建用户角色关系成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("新建用户角色关系失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建用户角色关系异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/userrole/removebyid")
    public ReturnMsg removeById(@RequestParam("urid") int urid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iUserRoleService.removeById(urid);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("删除用户角色关系成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("删除用户角色关系失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("删除用户角色关系异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/userrole/updatebyid")
    public ReturnMsg updateById(UserRole userRole) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iUserRoleService.updateById(userRole);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("修改用户角色关系成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("修改用户角色关系失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("修改用户角色关系异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}
