package job.controller;

import job.vo.ReturnMsg;
import job.entity.RolePermission;
import job.service.IRolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-21
 */
@CrossOrigin //跨域
@Slf4j
@RestController
public class RolePermissionController {

    @Autowired
    private IRolePermissionService iRolePermissionService;

    @ResponseBody
    @RequestMapping("/rolepermission/insertlist")
    public ReturnMsg save(@RequestBody List<RolePermission> rolePermissionList) {
        ReturnMsg msg = new ReturnMsg();
        try {
            int num = iRolePermissionService.insertList(rolePermissionList);
            if (num > 0) {
                msg.setStatus("200");
                msg.setStatusMsg("新建角色权限关系列表成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("新建角色权限关系列表失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建角色权限关系列表异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/rolepermission/deletebyroleid")
    public ReturnMsg deleteByRoleId(@RequestParam("rid") int rid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            int num = iRolePermissionService.deleteByRoleId(rid);
            if (num > 0) {
                msg.setStatus("200");
                msg.setStatusMsg("通过角色ID删除角色权限关系成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("通过角色ID删除角色权限关系失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("通过角色ID删除角色权限关系异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/rolepermission/deletebypermissionid")
    public ReturnMsg deleteByPermissionId(@RequestParam("pid") int pid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            int num = iRolePermissionService.deleteByPermissionId(pid);
            if (num > 0) {
                msg.setStatus("200");
                msg.setStatusMsg("通过权限ID删除角色权限关系成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("通过权限ID删除角色权限关系失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("通过权限ID删除角色权限关系异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/rolepermission/getpermissionbyroleid")
    public ReturnMsg getPermissionByRoleId(@RequestParam("rid") int rid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            List<String> permissionIdList = iRolePermissionService.getPermissionByRoleId(rid);
            if (permissionIdList != null) {
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

