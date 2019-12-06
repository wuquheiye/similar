package swtech.pageDesignControl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.common.vo.ReturnMsgPage;
import swtech.pageDesignControl.entity.Tardy;
import swtech.pageDesignControl.service.ITardyService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁君选
 * @since 2019-12-06
 */
@RestController
@Slf4j
public class TardyController {

    @Autowired
    private ITardyService iTardyService;

    @ResponseBody
    @GetMapping("/manage/tardy/save")
    public ReturnMsg save(Tardy tardy) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iTardyService.save(tardy);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("新建迟到成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("新建迟到失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建迟到异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/tardy/removebyid")
    public ReturnMsg removeById(@RequestParam("tid") int tid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iTardyService.removeById(tid);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("删除迟到成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("删除迟到失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("删除迟到异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/tardy/updatebyid")
    public ReturnMsg updateById(Tardy tardy) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iTardyService.updateById(tardy);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("修改迟到成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("修改迟到失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("修改迟到异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/tardy/getbyid")
    public ReturnMsg getById(@RequestParam("tid") int tid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            Tardy tardy = iTardyService.getById(tid);
            if (tardy != null) {
                msg.setStatus("200");
                msg.setStatusMsg("查询单个迟到成功");
                msg.setMsg(tardy);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("查询单个迟到失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("查询单个迟到异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/tardy/selectbypageandcondition")
    public ReturnMsgPage selectByPageAndCondition(Tardy tardy, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ReturnMsgPage msg = new ReturnMsgPage();
        try {
            List<Tardy> departmentList = iTardyService.selectByPageAndCondition(tardy, page, pageSize);
            int totalSize = iTardyService.selectCount();
            int totalPage = (int) Math.ceil(1.0 * totalSize / pageSize);
            if (departmentList != null ) {
                msg.setStatus("200");
                msg.setStatusMsg("获取迟到条件分页成功");
                msg.setTotalPage(totalPage);
                msg.setMsg(departmentList);
                msg.setPageSize(pageSize);
                msg.setCurrentPage(page);
                msg.setPageStart((page - 1) * pageSize);
                msg.setTotalSize(totalSize);
                msg.setPageEnd(page * pageSize);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("获取迟到条件分页失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("获取迟到条件分页异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}
