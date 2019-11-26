package swtech.pageDesignControl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import swtech.pageDesignControl.common.utils.DateUtil;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.common.vo.ReturnMsgPage;
import swtech.pageDesignControl.entity.Department;
import swtech.pageDesignControl.service.IDepartmentService;

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
public class DepartmentController {

    @Autowired
    private IDepartmentService iDepartmentService;

    @ResponseBody
    @GetMapping("/manage/department/save")
    public ReturnMsg save( Department department) {
        ReturnMsg msg = new ReturnMsg();
        try {
            department.setDcreateTime(DateUtil.getNewDate());
            boolean isTrue = iDepartmentService.save(department);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("新建部门成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("新建部门失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建部门异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/department/removebyid")
    public ReturnMsg removeById(@RequestParam("did") int did) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iDepartmentService.removeById(did);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("删除部门成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("删除部门失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("删除部门异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/department/updatebyid")
    public ReturnMsg updateById( Department department) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iDepartmentService.updateById(department);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("修改部门成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("修改部门失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("修改部门异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/department/getbyid")
    public ReturnMsg getById(@RequestParam("did") int did) {
        ReturnMsg msg = new ReturnMsg();
        try {
            Department department = iDepartmentService.getById(did);
            if (department != null) {
                msg.setStatus("200");
                msg.setStatusMsg("查询单个部门成功");
                msg.setMsg(department);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("查询单个部门失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("查询单个部门异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/department/selectbypageandcondition")
    public ReturnMsgPage selectByPageAndCondition(Department department, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ReturnMsgPage msg = new ReturnMsgPage();
        try {
            List<Department> departmentList = iDepartmentService.selectByPageAndCondition(department, page, pageSize);
            int totalSize = iDepartmentService.selectCount();
            int totalPage = (int) Math.ceil(1.0 * totalSize / pageSize);
            int pageEnd = page * pageSize < pageSize ? page * pageSize :  pageSize;
            if (departmentList != null ) {
                msg.setStatus("200");
                msg.setStatusMsg("获取部门条件分页成功");
                msg.setMsg(departmentList);
                msg.setCurrentPage(page);
                msg.setPageSize(pageSize);
                msg.setTotalPage(totalPage);
                msg.setTotalSize(totalSize);
                msg.setPageStart((page - 1) * pageSize);
                msg.setPageEnd(page * pageSize);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("获取部门条件分页失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("获取部门条件分页异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}
