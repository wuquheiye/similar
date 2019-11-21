package swtech.pageDesignControl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.common.exception.ServiceException;
import swtech.pageDesignControl.common.vo.FlowVO;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.common.websocket.WebSocketServer;
import swtech.pageDesignControl.entity.Flow;
import swtech.pageDesignControl.enums.Flow.Fstatus;
import swtech.pageDesignControl.mapper.FlowMapper;
import swtech.pageDesignControl.service.IFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-18
 */
@Slf4j
@Service
public class FlowServiceImpl extends ServiceImpl<FlowMapper, Flow> implements IFlowService {

    //	 定义一个静态的日志器变量
    private static final Logger logger = LogManager.getLogger(FlowServiceImpl.class);


    @Resource
    private FlowMapper flowMapper;

    @Override
    @Transactional
    public ReturnMsg leaveInsert(Flow leave) throws IOException {
        ReturnMsg msg = new ReturnMsg();
        if(leave ==null) throw new ServiceException("申请参数为空");
        int insert = flowMapper.insert(leave);
        if(insert == 0) throw  new ServiceException("申请录入失败");
        QueryWrapper qw = new QueryWrapper();
        qw.eq("uid",leave.getUid());
        qw.eq("fstatus", Fstatus.UNTREATED.getCode());
        List<Flow> list = flowMapper.selectList(qw);
        JSONArray jsonArray = new JSONArray(list);
//        String sid =Integer.toString(leave.getFuidCharge()) ;
        WebSocketServer.sendInfo(jsonArray.toString(),"3");
        msg.setStatus("200");
        msg.setMsg(insert);
        msg.setStatusMsg("请假申请录入成功");
        return  msg;
    }

    @Transactional
    @Override
    public ReturnMsg selectLeaveAll(Integer uid,Integer ftype) {
        ReturnMsg msg = new ReturnMsg();
        if(uid==null || uid ==0) throw  new SecurityException("fid为空");
        if(ftype==null || uid ==0) throw  new SecurityException("ftype为空");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("uid",uid);
        queryWrapper.eq("ftype",ftype);
        List<FlowVO> list = flowMapper.selectList(queryWrapper);
        logger.info(list);
        if(list!=null){
            msg.setStatus("200");
            msg.setMsg(list);
            msg.setStatusMsg("获取历史纪录成功");
        }
        return msg;
    }

    @Override
    @Transactional
    public ReturnMsg fuidCharge(Flow flow) throws IOException {
        ReturnMsg msg = new ReturnMsg();
        if(flow == null) throw  new ServiceException("审批参数为空");
        int i = flowMapper.updateById(flow);
        if(i == 0) throw  new ServiceException("审批数据库操作失败");
        //备用查询 如果 测试时获取不到数据 启用下面两行代码
//        Flow flow1 = flowMapper.selectById(flow.getFid());
//        if(flow1 == null) throw new ServiceException("获取申请信息失败");
        if(flow.getFstatus().equals(Fstatus.CHARGEPASS.getCode())){
            log.info("主管推送内容"+JSONObject.fromObject(flow).toString()+",推送给 "+flow.getFuidManager());
            //主管审批通过
            WebSocketServer.sendInfo(
                    JSONObject.fromObject(flow).toString()
                    ,Integer.toString(flow.getFuidManager()));
        }else if(flow.getFstatus().equals(Fstatus.CHARGEREFUSE.getCode())){
            //主管审批拒绝
            WebSocketServer.sendInfo(
                    JSONObject.fromObject(flow).toString()
                    ,Integer.toString(flow.getUid()));
        }else if(flow.getFstatus().equals(Fstatus.MANAGERPASS.getCode())){
            //经理审批通过
            WebSocketServer.sendInfo(
                    JSONObject.fromObject(flow).toString()
                    ,Integer.toString(flow.getFuidStaffing()));
        }else if(flow.getFstatus().equals(Fstatus.MANAGERREFUSE.getCode())){
            //经理审批拒绝
            WebSocketServer.sendInfo(
                    JSONObject.fromObject(flow).toString()
                    ,Integer.toString(flow.getUid()));
        }else if(flow.getFstatus().equals(Fstatus.STAFFINGAFFIRM.getCode())){
            //人事确认
            WebSocketServer.sendInfo(
                    JSONObject.fromObject(flow).toString()
                    ,Integer.toString(flow.getUid()));
        }
        msg.setStatus("200");
        msg.setMsg(i);
        msg.setStatusMsg("审批成功");
        return msg;
    }
}
