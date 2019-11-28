package swtech.pageDesignControl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import swtech.pageDesignControl.common.vo.FlowApproval;
import swtech.pageDesignControl.common.vo.FlowOnbusIness;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.Flow;
import swtech.pageDesignControl.service.IFlowService;

import java.io.IOException;
import java.util.List;

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
@CrossOrigin
public class FlowController  {

    @Autowired
    private IFlowService iFlowService;

    /**
     * 查看历史办理信息记录
     * @param uid
     * @param rid
     * @return
     */
    public  ReturnMsg selectChargeHistory(@RequestParam("uid") Integer uid ,
                                          @RequestParam("rid") Integer rid,
                                          @RequestParam("ArtsVision") Integer ArtsVision){
        ReturnMsg msg = new ReturnMsg();
        iFlowService.selectChargeHistory(uid,rid,ArtsVision);
        return msg;
    }

    /**
     * 根据id查询历史请假记录
     * @param uid  uid
     * @param ftype 申请类型
     * @return
     */
    @ResponseBody
    @GetMapping("selectLeaveAll")
    public  ReturnMsg  selectLeaveAll (@RequestParam("uid") Integer uid,@RequestParam("ftype") Integer ftype){
        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iFlowService.selectLeaveAll(uid,ftype);
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
        try {
            msg = iFlowService.leaveInsert(leave);
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("流程申请录入失败");
            msg.setMsg(e.getMessage());
        }
        return  msg;
    }

    /**
     * 出差申请
     * @param flowOnbusIness
     * @return
     */
    @ResponseBody
    @PostMapping("onbusInessInsert")
    public ReturnMsg OnbusInessInsert(@RequestBody FlowOnbusIness flowOnbusIness){
        ReturnMsg msg =new ReturnMsg();
        try {
            msg = iFlowService.OnbusInessInsert(flowOnbusIness);
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("出差申请录入失败");
            msg.setMsg(e.getMessage());
        }
        return  msg;
    }

    /**
     * 查询当前用户需要待办的信息
     * uid
     * rid
     * @return
     */
    @ResponseBody
    @GetMapping("selectBacklog")
    public  ReturnMsg selectBacklog(@RequestParam("uid") Integer uid ,@RequestParam("rid") Integer rid){
        ReturnMsg msg =new ReturnMsg();
        try {
            msg = iFlowService.selectBacklog(uid, rid);
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("查询待办信息失败");
            msg.setMsg(e.getMessage());
        }
        return  msg;
    }

    /**
     * 查询个人申请信息
     * @param uid
     * @return
     */
    @ResponseBody
    @GetMapping("seletByUid")
    public ReturnMsg seletByUid(@RequestParam("uid") Integer uid){
        ReturnMsg msg =new ReturnMsg();
        try {
             msg = iFlowService.seletByUid(uid);
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("查询个人申请信息");
            msg.setMsg(e.getMessage());
        }
        return  msg;
    }

    /**
     * 流程审批
     * @param flowApproval
     * @return
     */
    @ResponseBody
    @PostMapping("fuidCharge")
    public  ReturnMsg fuidCharge(@RequestBody FlowApproval flowApproval){
        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iFlowService.fuidCharge(flowApproval);
        } catch (IOException e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("审批流程功能失败");
            msg.setMsg(e.getMessage());
        }
        return msg;
    }

}
