package job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import job.entity.Permission;
import job.vo.PermissionVO;

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
    List<PermissionVO> selecTree();
}
