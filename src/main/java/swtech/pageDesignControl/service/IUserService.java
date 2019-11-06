package swtech.pageDesignControl.service;

import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁君选
 * @since 2019-10-24
 */
public interface IUserService extends IService<User> {
    ReturnMsg selectJobWanted(Integer id);
}
