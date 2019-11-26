package swtech.pageDesignControl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.common.vo.ReturnMsgPage;
import swtech.pageDesignControl.entity.Role;
import swtech.pageDesignControl.service.IRoleService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-20
 */
@Slf4j
@Controller
public class RoleController {
    @Autowired
    private IRoleService iRoleService;

    @ResponseBody
    @GetMapping("/manage/role/save")
    public ReturnMsg save(Role role) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iRoleService.save(role);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("新建角色成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("新建角色失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建角色异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/role/removebyid")
    public ReturnMsg removeById(@RequestParam("rid") int rid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iRoleService.removeById(rid);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("删除角色成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("删除角色失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("删除角色异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/role/updatebyid")
    public ReturnMsg updateById(Role role) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iRoleService.updateById(role);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("修改角色成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("修改角色失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("修改角色异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/role/getbyid")
    public ReturnMsg getById(@RequestParam("rid") int rid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            Role role = iRoleService.getById(rid);
            if (role != null) {
                msg.setStatus("200");
                msg.setStatusMsg("查询单个角色成功");
                msg.setMsg(role);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("查询单个角色失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("查询单个角色异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/role/selectbypageandcondition")
    public ReturnMsgPage selectByPageAndCondition(Role role, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ReturnMsgPage msg = new ReturnMsgPage();
        try {
            List<Role> roleList = iRoleService.selectByPageAndCondition(role, page, pageSize);
            int totalSize = iRoleService.selectCount();
            int totalPage = (int) Math.ceil(1.0 * totalSize / pageSize);
            int pageEnd = page * pageSize < pageSize ? page * pageSize :  pageSize;
            if (roleList != null ) {
                msg.setStatus("200");
                msg.setMsg(roleList);
                msg.setCurrentPage(page);
                msg.setPageSize(pageSize);
                msg.setStatusMsg("获取角色条件分页成功");
                msg.setTotalPage(totalPage);
                msg.setPageStart((page - 1) * pageSize);
                msg.setTotalSize(totalSize);
                msg.setPageEnd(page * pageSize);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("获取角色条件分页失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("获取角色条件分页异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}
