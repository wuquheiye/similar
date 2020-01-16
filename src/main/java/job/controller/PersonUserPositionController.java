package job.controller;


import job.service.IPersonUserPositionService;
import job.vo.ReturnMsg;
import job.vo.ReturnMsgPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李鸿智
 * @since 2020-01-06
 */
@CrossOrigin //跨域
@Slf4j
@Controller
@RequestMapping("/personuserposition")
public class PersonUserPositionController {
    @Autowired
    private IPersonUserPositionService iPersonUserPositionService;

    @ResponseBody
    @RequestMapping("/inappropriate")
    public ReturnMsg inappropriate(Integer id) {
        ReturnMsg msg = new ReturnMsg();
        if (id == null) {
            msg.setStatus("203");
            msg.setStatusMsg("id不能为空");
        }
        try {
            msg = iPersonUserPositionService.inappropriate(id, 3);
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("更改异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @RequestMapping("/informInterview")
    public ReturnMsg save(Integer id,String telephonenumber) {
        ReturnMsg msg = new ReturnMsg();
        if (id == null) {
            msg.setStatus("203");
            msg.setStatusMsg("id不能为空");
        }
        if (telephonenumber == null){
            msg.setStatus("204");
            msg.setStatusMsg("电话号码不能为空");
        }
        try {
            msg = iPersonUserPositionService.informInterview(id, 2,telephonenumber);
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("更改异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @RequestMapping("/save")
    public ReturnMsg save(String telephonenumber, int companyPositionId) {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iPersonUserPositionService.save(telephonenumber, companyPositionId);
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建投递简历异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @RequestMapping("/findbypersonuserid")
    public ReturnMsgPage findByPersonUserId(Integer personUserId, Integer companyPositionId, Integer state, Integer page, Integer size) {
        if (size == null || size <= 0) {
            size = 10;
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (state == null) {
            state = 0;
        }
        if (personUserId == null) {
            personUserId = 0;
        }
        if (companyPositionId == null) {
            companyPositionId = 0;
        }
        ReturnMsgPage msg = new ReturnMsgPage();
        try {
            msg = iPersonUserPositionService.findByPersonUserId(personUserId, companyPositionId, state, page, size);
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("查询个人投递情况成功异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}
