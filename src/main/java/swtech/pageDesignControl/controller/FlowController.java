package swtech.pageDesignControl.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.Flow;
import swtech.pageDesignControl.service.IFlowService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-18
 */
@Slf4j
@Controller
@RequestMapping("/flow")
public class FlowController  {

    @Autowired
    private IFlowService iFlowService;

    /**
     * 根据id查询历史请假记录
     * @param fid id
     * @param ftype 申请类型
     * @return
     */
    @ResponseBody
    @GetMapping("selectLeaveAll")
    public  ReturnMsg  selectLeaveAll (@RequestParam("fid") Integer fid,@RequestParam("ftype") Integer ftype){
        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iFlowService.selectLeaveAll(fid,ftype);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("历史记录获取失败");
            msg.setMsg(e.getMessage());
        }
        return msg;
    }


    /**
     * 流程申请
     * @param leave
     * @return
     */
    @ResponseBody
    @PostMapping("leaveInsert")
    public ReturnMsg leaveInsert(@RequestBody Flow leave){
        ReturnMsg msg =new ReturnMsg();
        boolean save = iFlowService.save(leave);
        if(save==true){
           msg.setStatus("200");
           msg.setMsg(save);
            msg.setStatusMsg("请假申请录入成功");
        }else{
            msg.setStatus("201");
            msg.setMsg(save);
            msg.setStatusMsg("请假申请录入失败");
        }
        return  msg;

    }

}
