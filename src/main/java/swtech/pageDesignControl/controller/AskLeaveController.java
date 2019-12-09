package swtech.pageDesignControl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.common.vo.ReturnMsgPage;
import swtech.pageDesignControl.entity.AskLeave;
import swtech.pageDesignControl.service.IAskLeaveService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁君选
 * @since 2019-12-06
 */
@Slf4j
@RestController
public class AskLeaveController {

    @Autowired
    private IAskLeaveService iAskLeaveService;

    @ResponseBody
    @GetMapping("/manage/askleave/save")
    public ReturnMsg save(AskLeave askLeave) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iAskLeaveService.save(askLeave);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("新建请假成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("新建请假失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建请假异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/askleave/removebyid")
    public ReturnMsg removeById(@RequestParam("lid") int lid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iAskLeaveService.removeById(lid);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("删除请假成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("删除请假失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("删除请假异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/askleave/updatebyid")
    public ReturnMsg updateById(AskLeave askLeave) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iAskLeaveService.updateById(askLeave);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("修改请假成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("修改请假失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("修改请假异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/askleave/getbyid")
    public ReturnMsg getById(@RequestParam("lid") int lid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            AskLeave askLeave = iAskLeaveService.getById(lid);
            if (askLeave != null) {
                msg.setStatus("200");
                msg.setStatusMsg("查询单个请假成功");
                msg.setMsg(askLeave);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("查询单个请假失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("查询单个请假异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/askleave/selectbypageandcondition")
    public ReturnMsgPage selectByPageAndCondition(AskLeave askLeave, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ReturnMsgPage msg = new ReturnMsgPage();
        try {
            List<AskLeave> departmentList = iAskLeaveService.selectByPageAndCondition(askLeave, page, pageSize);
            int totalSize = iAskLeaveService.selectCount();
            int totalPage = (int) Math.ceil(1.0 * totalSize / pageSize);
            if (departmentList != null ) {
                msg.setStatus("200");
                msg.setStatusMsg("获取请假条件分页成功");
                msg.setTotalPage(totalPage);
                msg.setMsg(departmentList);
                msg.setPageSize(pageSize);
                msg.setCurrentPage(page);
                msg.setTotalSize(totalSize);
                msg.setPageStart((page - 1) * pageSize);
                msg.setPageEnd(page * pageSize);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("获取请假条件分页失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("获取请假条件分页异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}
