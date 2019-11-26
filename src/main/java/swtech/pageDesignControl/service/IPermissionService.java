package swtech.pageDesignControl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import swtech.pageDesignControl.common.vo.PermissionVo;
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
     * 查询权限树
     *
     * @return
     */
    List<PermissionVo> selecTree();
}
