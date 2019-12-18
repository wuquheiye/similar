package job.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import job.vo.PermissionVO;
import job.entity.Permission;
import job.mapper.PermissionMapper;
import job.service.IPermissionService;
import org.springframework.transaction.annotation.Transactional;
import job.mapper.RolePermissionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-20
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Transactional
    @Override
    public boolean removeById(Serializable pid) {
        int num = 0;
        num = permissionMapper.deleteById(pid);
        rolePermissionMapper.deleteByPermissionId((Integer) pid);
        List<PermissionVO> permissionVOList = permissionMapper.selectChild((Integer) pid);
        removeChild(permissionVOList);
        if (num > 0) {
            return true;
        }
        return false;
    }

    public List<PermissionVO> removeChild(List<PermissionVO> permissionVOList) {
        for (PermissionVO permissionVo : permissionVOList) {
            List<PermissionVO> childPermissionVO = permissionMapper.selectChild(permissionVo.getId());
            if (childPermissionVO != null && childPermissionVO.size() > 0) {
                permissionMapper.deleteById(permissionVo.getPid());
                rolePermissionMapper.deleteByPermissionId(permissionVo.getId());
                removeChild(childPermissionVO);
            } else {
                rolePermissionMapper.deleteByPermissionId(permissionVo.getId());
                permissionMapper.deleteById(permissionVo.getPid());
            }
        }
        return permissionVOList;
    }

    @Transactional
    @Override
    public List<PermissionVO> selecTree() {
        List<PermissionVO> permissionVOList = permissionMapper.selecGtrandfather();
        getChild(permissionVOList);
        return permissionVOList;
    }

    /**
     * 获取子类
     *
     * @param permissionVOList
     * @return
     */
    public List<PermissionVO> getChild(List<PermissionVO> permissionVOList) {
        for (PermissionVO permissionVo : permissionVOList) {
            List<PermissionVO> childPermissionVO = permissionMapper.selectChild(permissionVo.getId());
            if (childPermissionVO != null && childPermissionVO.size() > 0) {
                getChild(childPermissionVO);
            }
            permissionVo.setChildrenPermission(childPermissionVO);
        }
        return permissionVOList;
    }

    @Transactional
    @Override
    public List<Permission> list(Wrapper<Permission> queryWrapper) {
        return permissionMapper.selectList(null);
    }
}
