package swtech.pageDesignControl.service.impl;

import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.entity.Role;
import swtech.pageDesignControl.mapper.RoleMapper;
import swtech.pageDesignControl.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Transactional
    @Override
    public boolean save(Role role) {
        int num = roleMapper.insert(role);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean removeById(Serializable did) {
        int num = roleMapper.deleteById(did);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateById(Role role) {
        int num = roleMapper.updateById(role);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Role selectById(int did) {
        return roleMapper.selectById(did);
    }

    @Transactional
    @Override
    public List<Role> selectByPageAndCondition(Role role, int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        return roleMapper.selectByPageAndCondition(role, pageStart, pageSize);
    }

    @Transactional
    @Override
    public int selectCount() {
        return roleMapper.selectCount();
    }
}
