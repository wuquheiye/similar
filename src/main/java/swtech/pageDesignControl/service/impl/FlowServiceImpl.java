package swtech.pageDesignControl.service.impl;

import com.sun.org.apache.bcel.internal.generic.ATHROW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.Flow;
import swtech.pageDesignControl.mapper.FlowMapper;
import swtech.pageDesignControl.service.IFlowService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Resource
    private FlowMapper flowMapper;

    @Transactional
    @Override
    public ReturnMsg selectLeaveAll(Integer fid) {
        ReturnMsg msg = new ReturnMsg();
//        if(fid==null || fid ==0)new throw

        return null;
    }
}
