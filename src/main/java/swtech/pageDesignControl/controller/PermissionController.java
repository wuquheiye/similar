package swtech.pageDesignControl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import swtech.pageDesignControl.common.vo.PermissionVo;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.common.vo.ReturnMsgPage;
import swtech.pageDesignControl.entity.Permission;
import swtech.pageDesignControl.service.IPermissionService;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-20
 */
@Slf4j
@Controller
public class PermissionController {

    @Autowired
    private IPermissionService iPermissionService;

    @ResponseBody
    @GetMapping("/manage/permission/save")
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
    @GetMapping("/manage/permission/removebyid")
    public ReturnMsg removeById(@RequestParam("pid") int pid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iPermissionService.removeById(pid);
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
    @GetMapping("/manage/permission/updatebyid")
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
    @GetMapping("/manage/permission/selectbyid")
    public ReturnMsg selectById(@RequestParam("pid") int pid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            Permission permission = iPermissionService.selectById(pid);
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

    /**
     * 获取权限树
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/manage/permission/selecttree")
    public ReturnMsg selectTree() {
        ReturnMsg msg = new ReturnMsg();
        try {
            List<PermissionVo> permissionVoList = iPermissionService.selecTree();
            if (permissionVoList != null ) {
                msg.setStatus("200");
                msg.setStatusMsg("获取权限条件分页成功");
                msg.setMsg(permissionVoList);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("获取权限条件分页失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("获取权限条件分页异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}
