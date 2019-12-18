package job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import job.entity.Role;

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
