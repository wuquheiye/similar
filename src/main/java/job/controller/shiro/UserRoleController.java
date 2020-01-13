package job.controller.shiro;

import job.vo.ReturnMsg;
import job.entity.UserRole;
import job.service.IUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-21
 */
@CrossOrigin //跨域
@Slf4j
@RestController
public class UserRoleController {

    @Autowired
    private IUserRoleService iUserRoleService;

    @ResponseBody
    @RequestMapping("/userrole/save")
    public ReturnMsg save(UserRole userRole) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iUserRoleService.save(userRole);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("新建用户角色关系列表成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("新建用户角色关系列表失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建用户角色关系列表异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/userrole/deletebyuserid")
    public ReturnMsg deleteByUserId(@RequestParam("uid") int uid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            int num = iUserRoleService.deleteByUserId(uid);
            if (num > 0) {
                msg.setStatus("200");
                msg.setStatusMsg("通过用户ID删除用户角色关系成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("通过用户ID删除用户角色关系失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("通过用户ID删除用户角色关系异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/userrole/deletebyroleid")
    public ReturnMsg deleteByRoleId(@RequestParam("rid") int rid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            int num = iUserRoleService.deleteByRoleId(rid);
            if (num > 0) {
                msg.setStatus("200");
                msg.setStatusMsg("通过角色ID删除用户角色关系成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("通过角色ID删除用户角色关系失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("通过角色ID删除用户角色关系异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/userrole/getrolebyuserid")
    public ReturnMsg getRoleByUserId(@RequestParam("uid") int uid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            List<String> permissionIdList = iUserRoleService.getRoleByUserId(uid);
            if (permissionIdList != null) {
                msg.setStatus("200");
                msg.setStatusMsg("查询用户角色关系成功");
                msg.setMsg(permissionIdList);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("查询用户角色关系失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("查询用户角色关系异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}
