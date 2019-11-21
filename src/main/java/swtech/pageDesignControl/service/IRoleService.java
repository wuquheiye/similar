package swtech.pageDesignControl.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import swtech.pageDesignControl.entity.Role;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-15
 */
public interface IRoleService extends IService<Role> {

    /**
     * 查询单个
     *
     * @param rid
     * @return
     */
    Role selectById(int rid);

    /**
     * 分页查询
     *
     * @param role
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<Role> selectByPageAndCondition(Role role, int pageStart, int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();

}
