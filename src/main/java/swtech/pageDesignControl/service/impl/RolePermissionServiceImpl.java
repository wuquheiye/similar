package swtech.pageDesignControl.service.impl;

import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.entity.RolePermission;
import swtech.pageDesignControl.mapper.RolePermissionMapper;
import swtech.pageDesignControl.service.IRolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
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
    public List<String> getPermissionByRoleId(int rid) {
        return rolePermissionMapper.getPermissionByRoleId(rid);
    }

    @Transactional
    @Override
    public int insertList(List<RolePermission> rolePermissionList) {
        return rolePermissionMapper.insertList(rolePermissionList);
    }

    @Transactional
    @Override
    public int deleteByRoleId(int rid) {
        return rolePermissionMapper.deleteByRoleId(rid);
    }

    @Transactional
    @Override
    public int deleteByPermissionId(int pid) {
        return rolePermissionMapper.deleteByPermissionId(pid);
    }
}
