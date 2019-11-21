package swtech.pageDesignControl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import swtech.pageDesignControl.common.exception.ServiceException;
import swtech.pageDesignControl.common.vo.PageVO;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.common.vo.ReturnMsgPage;
import swtech.pageDesignControl.entity.Journal;
import swtech.pageDesignControl.service.IJournalService;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialException;
import java.util.List;

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
     * 根据id获取日志信息
     * @param jid
     * @return
     */
     @GetMapping("/selectJournal/{jid}")
     public String selectJournal(@PathVariable("jid") Integer jid, Model model){
         System.out.println("进入日志");
         Journal byId = iJournalService.getById(jid);
         if(byId==null) throw  new ServiceException("参数错误，此id作废，或无值");
         model.addAttribute("journal",byId);
         return  "./daily/dailyinfo.html";
     }


    /**
     * 查看当前用户的所有日志信息
     * @return
     */
    @ResponseBody
    @PostMapping("/selectJournalAll")
    public ReturnMsg selectJournalAll(@RequestBody PageVO<Journal> pageVO){
        QueryWrapper qw  = new QueryWrapper();
        qw.eq("uid",pageVO.getJournal().getUid());
        qw.orderByDesc("jid");
        ReturnMsg msg = new ReturnMsg();
        IPage<Journal> page1 = iJournalService.page(pageVO.getPage(), qw);
        print(page1.getRecords());
        if(page1!=null){
            msg.setStatus("200");
            msg.setStatusMsg("查询日志成功");
            msg.setMsg(page1);
        }
        return  msg;
    }

    private <T> void print(List<T> list) {
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(System.out::println);
        }
    }

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
