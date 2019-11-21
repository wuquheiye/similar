package swtech.pageDesignControl.service.impl;

import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.entity.RolePermission;
import swtech.pageDesignControl.mapper.RolePermissionMapper;
import swtech.pageDesignControl.service.IRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-21
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Transactional
    @Override
    public boolean save(RolePermission rolePermission) {
        int num = rolePermissionMapper.insert(rolePermission);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean removeById(Serializable rpid) {
        int num = rolePermissionMapper.deleteById(rpid);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateById(RolePermission rolePermission) {
        int num = rolePermissionMapper.updateById(rolePermission);
        if (num > 0) {
            return true;
        }
        return false;
    }
}
