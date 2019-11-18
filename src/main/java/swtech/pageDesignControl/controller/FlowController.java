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

    @ResponseBody
    @RequestMapping("selectLeaveAll")
    public  ReturnMsg  selectLeaveAll (@RequestParam("fid") Integer fid){
        return iFlowService.selectLeaveAll(fid);
    }


    /**
     * 流程申请
     * @param leave
     * @return
     */
    @ResponseBody
    @RequestMapping("leaveInsert")
    public ReturnMsg leaveInsert(@RequestBody Flow leave){
        ReturnMsg msg =new ReturnMsg();
        boolean save = iFlowService.save(leave);
        if(save==true){
           msg.setStatus("200");
           msg.setMsg(save);
        }else{
            msg.setStatus("201");
            msg.setMsg(save);
        }
        return  msg;

    }

}
