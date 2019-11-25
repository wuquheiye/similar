package swtech.pageDesignControl.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.common.vo.PermissionVo;
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
        List<PermissionVo> permissionVoList = permissionMapper.selectChild((Integer) did);
        removeChild(permissionVoList);
        if (num > 0) {
            return true;
        }
        return false;
    }

    public List<PermissionVo> removeChild(List<PermissionVo> permissionVoList){
        for (PermissionVo permissionVo : permissionVoList){
            List<PermissionVo> childPermissionVo = permissionMapper.selectChild(permissionVo.getPid());
            if(childPermissionVo!=null&&childPermissionVo.size()>0){
                permissionMapper.deleteById(permissionVo.getPid());
                removeChild(childPermissionVo);
            }else{
                permissionMapper.deleteById(permissionVo.getPid());
            }
        }
        return permissionVoList;
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
    public List<PermissionVo> selecTree() {
        List<PermissionVo> permissionVoList = permissionMapper.selecGtrandfather();
        getChild(permissionVoList);
        return permissionVoList;
    }

    /**
     * 获取子类
     *
     * @param permissionVoList
     * @return
     */
    public List<PermissionVo> getChild(List<PermissionVo> permissionVoList) {
        for (PermissionVo permissionVo : permissionVoList) {
            List<PermissionVo> childPermissionVo = permissionMapper.selectChild(permissionVo.getPid());
            if (childPermissionVo != null && childPermissionVo.size() > 0) {
                getChild(childPermissionVo);
            }
            permissionVo.setChildrenPermission(childPermissionVo);
        }
        return permissionVoList;
    }

    @Transactional
    @Override
    public List<Permission> list(Wrapper<Permission> queryWrapper) {
        return permissionMapper.selectList(null);
    }
}
