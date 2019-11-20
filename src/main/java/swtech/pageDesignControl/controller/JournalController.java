package swtech.pageDesignControl.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.Journal;
import swtech.pageDesignControl.service.IJournalService;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-18
 */
@Controller
@CrossOrigin
@Slf4j
@RequestMapping("/journal")
public class JournalController{

    @Resource
    private IJournalService iJournalService;

    /**
     * 日志录入
     * @param journal
     * @return
     */
    @ResponseBody
    @PostMapping("/insertJournal")
    public ReturnMsg insertJournal(@RequestBody Journal journal){
        ReturnMsg msg = new ReturnMsg();
        try {
            msg= iJournalService.insertJournal(journal);
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("日志录入失败");
            msg.setMsg(e.getMessage());
        }
        return msg;
    }
}
