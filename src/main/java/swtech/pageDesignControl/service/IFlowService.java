package swtech.pageDesignControl.service;

import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.Flow;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-18
 */
public interface IFlowService extends IService<Flow> {

    ReturnMsg  selectLeaveAll(Integer fid,Integer ftype);

}
