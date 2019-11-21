package swtech.pageDesignControl.service.impl;

import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.entity.Permission;
import swtech.pageDesignControl.mapper.PermissionMapper;
import swtech.pageDesignControl.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-20
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Transactional
    @Override
    public boolean save(Permission permission) {
        int num = permissionMapper.insert(permission);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean removeById(Serializable did) {
        int num = permissionMapper.deleteById(did);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateById(Permission permission) {
        int num = permissionMapper.updateById(permission);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Permission selectById(int did) {
        return permissionMapper.selectById(did);
    }

    @Transactional
    @Override
    public List<Permission> selectByPageAndCondition(Permission permission, int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        return permissionMapper.selectByPageAndCondition(permission, pageStart, pageSize);
    }

    @Transactional
    @Override
    public int selectCount() {
        return permissionMapper.selectCount();
    }
}
