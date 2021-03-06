package job.controller.shiro;

import job.vo.PermissionVO;
import job.vo.ReturnMsg;
import job.entity.Permission;
import job.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-20
 */
@CrossOrigin //跨域
@Slf4j
@Controller
public class PermissionController {

    @Autowired
    private IPermissionService iPermissionService;

    @ResponseBody
    @GetMapping("/permission/save")
    public ReturnMsg save(Permission permission) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iPermissionService.save(permission);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("新建权限成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("新建权限失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建权限异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/permission/removebyid")
    public ReturnMsg removeById(@RequestParam("id") int id) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iPermissionService.removeById(id);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("删除权限成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("删除权限失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("删除权限异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/permission/updatebyid")
    public ReturnMsg updateById(Permission permission) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iPermissionService.updateById(permission);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("修改权限成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("修改权限失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("修改权限异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/permission/getbyid")
    public ReturnMsg selectById(@RequestParam("id") int id) {
        ReturnMsg msg = new ReturnMsg();
        try {
            Permission permission = iPermissionService.getById(id);
            if (permission != null) {
                msg.setStatus("200");
                msg.setStatusMsg("查询单个权限成功");
                msg.setMsg(permission);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("查询单个权限失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("查询单个权限异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/permission/selecttree")
    public ReturnMsg selectTree() {
        ReturnMsg msg = new ReturnMsg();
        try {
            List<PermissionVO> permissionVOList = iPermissionService.selecTree();
            if (permissionVOList != null ) {
                msg.setStatus("200");
                msg.setStatusMsg("获取权限树成功");
                msg.setMsg(permissionVOList);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("获取权限树失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("获取权限树异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/permission/list")
    public ReturnMsg list() {
        ReturnMsg msg = new ReturnMsg();
        try {
            List<Permission> permissionList = iPermissionService.list(null);
            if (permissionList != null ) {
                msg.setStatus("200");
                msg.setStatusMsg("获取所有权限成功");
                msg.setMsg(permissionList);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("获取所有权限失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("获取所有权限异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}
