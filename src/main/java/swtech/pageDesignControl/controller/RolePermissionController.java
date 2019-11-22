package swtech.pageDesignControl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.RolePermission;
import swtech.pageDesignControl.service.IRolePermissionService;

import java.util.List;

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
public class RolePermissionController {

    @Autowired
    private IRolePermissionService iRolePermissionService;

    @ResponseBody
    @GetMapping("/manage/rolepermission/save")
    public ReturnMsg save(RolePermission rolePermission) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iRolePermissionService.save(rolePermission);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("新建角色权限关系成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("新建角色权限关系失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建角色权限关系异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/rolepermission/removebyid")
    public ReturnMsg removeById(@RequestParam("rpid") int rpid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iRolePermissionService.removeById(rpid);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("删除角色权限关系成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("删除角色权限关系失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("删除角色权限关系异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/rolepermission/updatebyid")
    public ReturnMsg updateById(RolePermission rolePermission) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iRolePermissionService.updateById(rolePermission);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("修改角色权限关系成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("修改角色权限关系失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("修改角色权限关系异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/rolepermission/getpermissionbyroleid")
    public ReturnMsg updateById(@RequestParam("rid") int rid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            List<String> permissionIdList = iRolePermissionService.getPermissionByRoleId(rid);
            if (permissionIdList!=null) {
                msg.setStatus("200");
                msg.setStatusMsg("查询角色权限关系成功");
                msg.setMsg(permissionIdList);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("查询角色权限关系失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("查询角色权限关系异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}

