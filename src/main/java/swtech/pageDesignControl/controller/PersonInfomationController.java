package swtech.pageDesignControl.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.common.vo.ReturnMsgPage;
import swtech.pageDesignControl.entity.PersonInfomation;
import swtech.pageDesignControl.service.IPersonInfomationService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-28
 */
@Slf4j
@RestController
public class PersonInfomationController {
    @Autowired
    private IPersonInfomationService iPersonInfomationService;

    @ResponseBody
    @RequestMapping("/manage/personinfomation/save")
    public ReturnMsg save(@RequestBody PersonInfomation personInfomation) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iPersonInfomationService.save(personInfomation);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("新建个人档案成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("新建个人档案失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建个人档案异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/personinfomation/removebyid")
    public ReturnMsg removeById(@RequestParam("pid") int pid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iPersonInfomationService.removeById(pid);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("删除个人档案成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("删除个人档案失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("删除个人档案异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @RequestMapping("/manage/personinfomation/updatebyid")
    public ReturnMsg updateById(@RequestBody PersonInfomation personInfomation) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iPersonInfomationService.updateById(personInfomation);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("修改个人档案成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("修改个人档案失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("修改个人档案异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/personinfomation/getbyid")
    public ReturnMsg getById(@RequestParam("pid") int pid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            PersonInfomation personInfomation = iPersonInfomationService.getById(pid);
            if (personInfomation != null) {
                msg.setStatus("200");
                msg.setStatusMsg("查询单个个人档案成功");
                msg.setMsg(personInfomation);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("查询单个个人档案失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("查询单个个人档案异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @GetMapping("/manage/personinfomation/selectbypageandcondition")
    public ReturnMsgPage selectByPageAndCondition(PersonInfomation personInfomation, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        ReturnMsgPage msg = new ReturnMsgPage();
        try {
            List<PersonInfomation> personInfomationList = iPersonInfomationService.selectByPageAndCondition(personInfomation, page, pageSize);
            int totalSize = iPersonInfomationService.selectCount();
            int totalPage = (int) Math.ceil(1.0 * totalSize / pageSize);
            int pageEnd = page * pageSize < pageSize ? page * pageSize :  pageSize;
            if (personInfomationList != null ) {
                msg.setPageSize(pageSize);
                msg.setStatusMsg("获取个人档案条件分页成功");
                msg.setTotalPage(totalPage);
                msg.setPageStart((page - 1) * pageSize);
                msg.setStatus("200");
                msg.setMsg(personInfomationList);
                msg.setCurrentPage(page);
                msg.setTotalSize(totalSize);
                msg.setPageEnd(page * pageSize);
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("获取个人档案条件分页失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("获取个人档案条件分页异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}
