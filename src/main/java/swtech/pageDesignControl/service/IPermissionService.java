package swtech.pageDesignControl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import swtech.pageDesignControl.entity.Permission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2019-11-15
 */
public interface IPermissionService extends IService<Permission> {
    /**
     * 查询单个
     *
     * @param pid
     * @return
     */
    Permission selectById(int pid);

    /**
     * 分页查询
     *
     * @param permission
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<Permission> selectByPageAndCondition(Permission permission, int pageStart, int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();

}
