package swtech.pageDesignControl.service;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import swtech.pageDesignControl.common.vo.FlowApproval;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.Flow;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-18
 */
public interface IFlowService extends IService<Flow> {

    ReturnMsg leaveInsert(Flow leave) throws IOException;

    ReturnMsg  selectLeaveAll(Integer fid,Integer ftype);

    ReturnMsg fuidCharge( FlowApproval flowApproval) throws IOException;

    ReturnMsg selectBacklog(Integer uid ,  Integer rid);

}
