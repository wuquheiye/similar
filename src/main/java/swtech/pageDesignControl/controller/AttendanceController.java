package swtech.pageDesignControl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.common.vo.ReturnMsgPage;
import swtech.pageDesignControl.entity.Attendance;
import swtech.pageDesignControl.service.IAttendanceService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁君选
 * @since 2019-12-04
 */
@Slf4j
@RestController
public class AttendanceController {
    @Autowired
    private IAttendanceService iAttendanceService;

    @ResponseBody
    @GetMapping("/manage/attendance/save")
    public ReturnMsg save(Attendance attendance) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iAttendanceService.save(attendance);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("新建考勤成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("新建考勤失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建考勤异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/attendance/removebyid")
    public ReturnMsg removeById(@RequestParam("aid") int aid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iAttendanceService.removeById(aid);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("删除考勤成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("删除考勤失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("删除考勤异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/attendance/updatebyid")
    public ReturnMsg updateById(Attendance attendance) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iAttendanceService.updateById(attendance);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("修改考勤成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("修改考勤失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("修改考勤异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/attendance/getbyid")
    public ReturnMsg getById(@RequestParam("aid") int aid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            Attendance attendance = iAttendanceService.getById(aid);
            if (attendance != null) {
                msg.setStatus("200");
                msg.setStatusMsg("查询单个考勤成功");
                msg.setMsg(attendance);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("查询单个考勤失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("查询单个考勤异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/attendance/selectbypageandcondition")
    public ReturnMsgPage selectByPageAndCondition(Attendance attendance, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ReturnMsgPage msg = new ReturnMsgPage();
        try {
            List<Attendance> attendanceList = iAttendanceService.selectByPageAndCondition(attendance, page, pageSize);
            int totalSize = iAttendanceService.selectCount();
            int totalPage = (int) Math.ceil(1.0 * totalSize / pageSize);
            if (attendanceList != null ) {
                msg.setStatus("200");
                msg.setStatusMsg("获取考勤条件分页成功");
                msg.setMsg(attendanceList);
                msg.setCurrentPage(page);
                msg.setPageSize(pageSize);
                msg.setTotalPage(totalPage);
                msg.setTotalSize(totalSize);
                msg.setPageStart((page - 1) * pageSize);
                msg.setPageEnd(page * pageSize);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("获取考勤条件分页失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("获取考勤条件分页异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}
