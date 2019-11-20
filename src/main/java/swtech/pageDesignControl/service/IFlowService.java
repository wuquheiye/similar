package swtech.pageDesignControl.service;

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

    ReturnMsg fuidCharge(Flow flow) throws IOException;

}
