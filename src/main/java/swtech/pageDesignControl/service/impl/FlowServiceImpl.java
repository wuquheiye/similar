package swtech.pageDesignControl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import swtech.pageDesignControl.common.exception.ServiceException;
import swtech.pageDesignControl.common.vo.*;
import swtech.pageDesignControl.common.websocket.WebSocketServer;
import swtech.pageDesignControl.entity.Flow;
import swtech.pageDesignControl.entity.OnbusInessFlow;
import swtech.pageDesignControl.entity.ServeFlow;
import swtech.pageDesignControl.enums.Flow.Fstatus;
import swtech.pageDesignControl.enums.Judge;
import swtech.pageDesignControl.enums.Role;
import swtech.pageDesignControl.mapper.FlowMapper;
import swtech.pageDesignControl.mapper.OnbusInessFlowMapper;
import swtech.pageDesignControl.mapper.ServeFlowMapper;
import swtech.pageDesignControl.service.IFlowService;
import swtech.pageDesignControl.enums.Flow.Ftype;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    @Resource
    private OnbusInessFlowMapper onbusInessFlowMapper;
    @Resource
    private ServeFlowMapper serveFlowMapper;


    @Override
    @Transactional
    public ReturnMsg leaveInsert(Flow leave) throws IOException {
        ReturnMsg msg = new ReturnMsg();
        if(leave ==null) throw new ServiceException("申请参数为空");
        //给时间赋值
        if(leave.getFendTime()==null){
            leave.setFendTime(LocalDateTime.of(2000,10,10,0,00));
        }
        if(leave.getFstartTime()==null){
            leave.setFstartTime(LocalDateTime.of(2000,10,10,0,00));
        }
        int insert = flowMapper.insert(leave);
        if(insert == 0) throw  new ServiceException("申请录入失败");
        QueryWrapper qw = new QueryWrapper();
        qw.eq("uid",leave.getUid());
        String  sid;
        //判断申请人是职工，还是主管
        if(leave.getFuidCharge()==0){
            qw.eq("fstatus",Fstatus.CHARGEPASS.getCode());
            sid=Integer.toString(leave.getFuidStaffing()) ;
        }else {
            qw.eq("fstatus", Fstatus.UNTREATED.getCode());
            sid =Integer.toString(leave.getFuidCharge()) ;
        }
        List<Flow> list = flowMapper.selectList(qw);
        JSONArray jsonArray = new JSONArray(list);

        WebSocketServer.sendInfo(jsonArray.toString(),sid);
        msg.setStatus("200");
        msg.setMsg(insert);
        msg.setStatusMsg("请假申请录入成功");
        return  msg;
    }

    @Override
    @Transactional
    public ReturnMsg OnbusInessInsert(FlowOnbusIness flowOnbusIness) throws IOException {
        Flow leave = new Flow();
        leave=flowOnbusIness.getLeave();
        ReturnMsg msg = new ReturnMsg();
        if(leave ==null) throw new ServiceException("申请参数为空");

        //给时间赋值
        if(leave.getFendTime()==null){
            leave.setFendTime(LocalDateTime.of(2000,10,10,0,00));
        }
        if(leave.getFstartTime()==null){
            leave.setFstartTime(LocalDateTime.of(2000,10,10,0,00));
        }

        int insert = flowMapper.insert(leave);
        //        //根据请假类型进行业务
        if(leave.getFtype().equals(Ftype.ONBUSINESS.getCode())){
            flowOnbusIness.getOnbusInessFlow().setFid(leave.getFid());
            int insert1 = onbusInessFlowMapper.insert(flowOnbusIness.getOnbusInessFlow());
            if(insert1==0) throw  new ServiceException("外出申请表录入失败");
        }else if(leave.getFtype().equals(Ftype.SERVE.getCode())){
            flowOnbusIness.getServeFlow().setFid(leave.getFid());
            int insert1 = serveFlowMapper.insert(flowOnbusIness.getServeFlow());
            if(insert1==0) throw  new ServiceException("业务招待申请录入失败");
        }
        if(insert == 0) throw  new ServiceException("申请录入失败");
        QueryWrapper qw = new QueryWrapper();
        qw.eq("uid",leave.getUid());
        String  sid;
        //判断申请人是职工，还是主管
        if(leave.getFuidCharge()==0){
            qw.eq("fstatus",Fstatus.CHARGEPASS.getCode());
            sid=Integer.toString(leave.getFuidStaffing()) ;
        }else {
            qw.eq("fstatus", Fstatus.UNTREATED.getCode());
            sid =Integer.toString(leave.getFuidCharge()) ;
        }
        List<Flow> list = flowMapper.selectList(qw);
        JSONArray jsonArray = new JSONArray(list);

        WebSocketServer.sendInfo(jsonArray.toString(),sid);
        msg.setStatus("200");
        msg.setMsg(insert);
        msg.setStatusMsg("请假申请录入成功");
        return  msg;
    }

    @Override
    @Transactional
    public ReturnMsg seletByUid(Integer uid) {
        ReturnMsg msg =new ReturnMsg();
        List<FlowOnbusIness> list = new ArrayList<>();
        if(uid == null ) throw new ServiceException("uid参数不能为空");
//        QueryWrapper qw = new QueryWrapper();
//        qw.eq("uid",uid);
        List<FlowVO> listflow = flowMapper.selectByUid(uid);
        for (FlowVO flow: listflow) {
            QueryWrapper wq = new QueryWrapper();
            wq.eq("fid",flow.getFid());
            if(flow.getFtype()==Ftype.SERVE.getCode()){
                ServeFlow serveFlow = serveFlowMapper.selectOne(wq);
                flow.setServeFlow(serveFlow);
            }else if(flow.getFtype() == Ftype.ONBUSINESS.getCode()){
                OnbusInessFlow onbusInessFlow = onbusInessFlowMapper.selectOne(wq);
                flow.setOnbusInessFlow(onbusInessFlow);
            }
        }
        msg.setStatus("200");
        msg.setStatusMsg("查询个人申请成功");
        msg.setMsg(listflow);
        return msg;
    }

    @Override
    @Transactional
    public ReturnMsg selectChargeHistory(Integer uid, Integer rid,Integer ArtsVision) {
        ReturnMsg msg = new ReturnMsg();
        if(uid == null) throw new ServiceException("uid参数不能为空");
        if(rid == null) throw  new ServiceException("rid 参数不能为空");
        if(ArtsVision == null) throw  new ServiceException("rid 参数不能为空");
        QueryWrapper qw = new QueryWrapper();
        if(ArtsVision.equals(Judge.YES.getCode())){ //利捷
            switch (Role.getByCode(rid)){
                case MANAGE:
                    qw.gt("fstatus",0);
                    qw.eq("fuid_manager",uid);
                    break;
                case GOVERNOR:
                    qw.lt("fstatus",0);
                    qw.eq("fuid_charge",uid);
                    break;
                case ADMINISTRATIVE:
                    qw.between("fstatus",5,6);
                    break;
                default:
                    break;
            }
        }
        else if(ArtsVision.equals(Judge.NO.getCode())){
            switch (Role.getByCode(rid)){
                case MANAGE:
                    qw.gt("fstatus",2);
                    qw.eq("fuid_manager",uid);
                    break;
                case GOVERNOR:
                    qw.gt("fstatus",0);
                    qw.eq("fuid_charge",uid);
                    break;
                case ADMINISTRATIVE:
                    qw.eq("fstatus",9);
                    qw.ne("ftype",3);
                    qw.ne("ftype",7);
                    break;
                default:
                    break;

            }
        }

        List list = flowMapper.selectList(qw);
        List<FlowVO> listtwo = getFlowVOInfo(list);
        msg.setStatus("200");
        msg.setMsg(listtwo);
        msg.setStatusMsg("获取当前人员历史审批记录成功");
        return msg;
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
        queryWrapper.eq("fstatus",Fstatus.STAFFINGAFFIRM.getCode());
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
    public ReturnMsg fuidCharge(FlowApproval flowApproval) throws IOException {
        ReturnMsg msg = new ReturnMsg();
        Flow flow = new Flow();
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        flow.setFid(flowApproval.getFid());
        if(flowApproval == null) throw  new ServiceException("审批参数为空");
        switch (flowApproval.getArtsVision()){
            case 0: //利捷
                if(flowApproval.getFstatus().equals(Fstatus.UNTREATED.getCode())){ //未处理
                    flow.setFuidManagerHand(dateFormat.format(date));
                    if(flowApproval.getStatus()== Judge.YES.getCode()){
                        flow.setFstatus(Fstatus.MANAGERPASS.getCode());
                        flow.setFrid(Role.ADMINISTRATIVE.getCode());
                    }else if(flowApproval.getStatus()== Judge.NO.getCode()){
                        flow.setFstatus(Fstatus.MANAGERPASS.getCode());
                        flow.setFuidManagerRefuse(flowApproval.getHand());
                    }
                }
                else  if(flowApproval.getFstatus().equals(Fstatus.MANAGERPASS.getCode())){//经理通过
                    flow.setFuidStaffingHand(dateFormat.format(date));
                    flow.setFstatus(Fstatus.STAFFINGAFFIRM.getCode());
                }
                break;
            case 1: //广空
                if(flowApproval.getFstatus().equals(Fstatus.UNTREATED.getCode())){ //未处理
                    flow.setFuidChargeHand(dateFormat.format(date));
                    if(flowApproval.getStatus()== Judge.YES.getCode()){
                        flow.setFstatus(Fstatus.CHARGEPASS.getCode());
                    }else if(flowApproval.getStatus()== Judge.NO.getCode()){
                        flow.setFstatus(Fstatus.CHARGEREFUSE.getCode());
                        flow.setFuidChargeRefuse(flowApproval.getHand());
                    }
                }
                else if(flowApproval.getFstatus().equals(Fstatus.CHARGEPASS.getCode())){//主管通过
                    flow.setFuidManagerHand(dateFormat.format(date));
                    if(flowApproval.getStatus()== Judge.YES.getCode()){
                        flow.setFstatus(Fstatus.MANAGERPASS.getCode());
                    }else if(flowApproval.getStatus()== Judge.NO.getCode()){
                        flow.setFstatus(Fstatus.MANAGERREFUSE.getCode());
                        flow.setFuidManagerRefuse(flowApproval.getHand());
                    }
                }
                else  if(flowApproval.getFstatus().equals(Fstatus.MANAGERPASS.getCode())){//经理通过
                    flow.setFuidStaffingHand(dateFormat.format(date));
                    flow.setFstatus(Fstatus.STAFFINGAFFIRM.getCode());
                }
                break;
        }


        int i = flowMapper.updateById(flow);
        if(i == 0) throw  new ServiceException("审批数据库操作失败");
        //备用查询 如果 测试时获取不到数据 启用下面两行代码
        Flow flow1 = flowMapper.selectById(flow.getFid());
        if(flow1 == null) throw new ServiceException("获取申请信息失败");
        if(flow1.getFstatus().equals(Fstatus.CHARGEPASS.getCode())){
            log.info("主管推送内容"+JSONObject.fromObject(flow1).toString()+",推送给 "+flow1.getFuidManager());
            //主管审批通过
            WebSocketServer.sendInfo(
                    JSONObject.fromObject(flow1).toString()
                    ,Integer.toString(flow1.getFuidManager()));
        }else if(flow1.getFstatus().equals(Fstatus.CHARGEREFUSE.getCode())){
            //主管审批拒绝
            WebSocketServer.sendInfo(
                    JSONObject.fromObject(flow1).toString()
                    ,Integer.toString(flow1.getUid()));
        }else if(flow1.getFstatus().equals(Fstatus.MANAGERPASS.getCode())){
            //经理审批通过
            WebSocketServer.sendInfo(
                    JSONObject.fromObject(flow1).toString()
                    ,Integer.toString(flow1.getFuidStaffing()));
        }else if(flow1.getFstatus().equals(Fstatus.MANAGERREFUSE.getCode())){
            //经理审批拒绝
            WebSocketServer.sendInfo(
                    JSONObject.fromObject(flow1).toString()
                    ,Integer.toString(flow1.getUid()));
        }else if(flow1.getFstatus().equals(Fstatus.STAFFINGAFFIRM.getCode())){
            //人事确认
            WebSocketServer.sendInfo(
                    JSONObject.fromObject(flow1).toString()
                    ,Integer.toString(flow1.getUid()));
        }
        msg.setStatus("200");
        msg.setMsg(i);
        msg.setStatusMsg("审批成功");
        return msg;
    }

    @Override
    @Transactional
    public ReturnMsg selectBacklog(Integer uid,Integer rid) {
        ReturnMsg msg  = new ReturnMsg();
        if(uid == null)throw  new  ServiceException("参数Uid为空，无法获取当前用户需要办理的信息");
        if(rid == null)throw  new  ServiceException("参数rid为空");
        QueryWrapper qw = new QueryWrapper();
        if(rid == Role.GOVERNOR.getCode()){
            qw.eq("fuid_charge",uid);
            qw.eq("fstatus",Fstatus.UNTREATED.getCode());
            qw.eq("frid",Role.GOVERNOR.getCode());
        }else if(rid == Role.MANAGE.getCode()){
            qw.eq("fuid_manager",uid);
            qw.eq("fstatus",Fstatus.CHARGEPASS.getCode());
            qw.eq("frid",Role.MANAGE.getCode());
        }else if(rid == Role.ADMINISTRATIVE.getCode()){
            qw.eq("fuid_staffing",uid);
            qw.eq("fstatus",Fstatus.MANAGERPASS.getCode());
            qw.eq("frid",Role.ADMINISTRATIVE.getCode());
        }else if(rid == Role.EMPLOYEES.getCode()){
            qw.eq("uid",uid);
        }
        List<Flow> list =flowMapper.selectList(qw);;
        List<FlowVO> listtwo = new ArrayList<FlowVO>();
        listtwo= list.stream().map(e ->convert(e)).collect(Collectors.toList());
        System.out.println("list"+list);
        System.out.println("listtwo"+listtwo);
        for (FlowVO flow:listtwo){
            QueryWrapper wq = new QueryWrapper();
            wq.eq("fid",flow.getFid());
            if(flow.getFtype()==Ftype.SERVE.getCode()){
                ServeFlow serveFlow = serveFlowMapper.selectOne(wq);
                flow.setServeFlow(serveFlow);
            }else if(flow.getFtype() == Ftype.ONBUSINESS.getCode()){
                OnbusInessFlow onbusInessFlow = onbusInessFlowMapper.selectOne(wq);
                flow.setOnbusInessFlow(onbusInessFlow);
            }
        }
        msg.setStatus("200");
        msg.setMsg(listtwo);
        msg.setStatusMsg("获取当前人员审批信息成功");
        return msg;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public  List<FlowVO> getFlowVOInfo(List<Flow> list){
        List<FlowVO> listtwo = new ArrayList<FlowVO>();
        listtwo= list.stream().map(e ->convert(e)).collect(Collectors.toList());
        System.out.println("list"+list);
        System.out.println("listtwo"+listtwo);
        for (FlowVO flow:listtwo){
            QueryWrapper wq = new QueryWrapper();
            wq.eq("fid",flow.getFid());
            if(flow.getFtype()==Ftype.SERVE.getCode()){
                ServeFlow serveFlow = serveFlowMapper.selectOne(wq);
                flow.setServeFlow(serveFlow);
            }else if(flow.getFtype() == Ftype.ONBUSINESS.getCode()){
                OnbusInessFlow onbusInessFlow = onbusInessFlowMapper.selectOne(wq);
                flow.setOnbusInessFlow(onbusInessFlow);
            }
        }
        return  listtwo;
    }

    public static  FlowVO convert(Flow flow){
        if(flow==null){
            return null;
        }
        FlowVO vo = new FlowVO();
        BeanUtils.copyProperties(flow,vo);
        return vo;
    }



}
