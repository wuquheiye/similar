package swtech.pageDesignControl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.bcel.internal.generic.ATHROW;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.Flow;
import swtech.pageDesignControl.mapper.FlowMapper;
import swtech.pageDesignControl.service.IFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-18
 */
@Service
public class FlowServiceImpl extends ServiceImpl<FlowMapper, Flow> implements IFlowService {

    //	 定义一个静态的日志器变量
    private static final Logger logger = LogManager.getLogger(FlowServiceImpl.class);


    @Resource
    private FlowMapper flowMapper;

    @Transactional
    @Override
    public ReturnMsg selectLeaveAll(Integer fid,Integer ftype) {
        ReturnMsg msg = new ReturnMsg();
        if(fid==null || fid ==0) throw  new SecurityException("fid为空");
        if(ftype==null || fid ==0) throw  new SecurityException("ftype为空");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("fid",fid);
        List list = flowMapper.selectList(queryWrapper);
        logger.info(list);
        if(list!=null){
            msg.setStatus("201");
            msg.setMsg(list);
            msg.setStatusMsg("获取历史纪录成功");
        }
        return msg;
    }
}
