package swtech.pageDesignControl.service.impl;

import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.entity.UserRole;
import swtech.pageDesignControl.entity.Users;
import swtech.pageDesignControl.mapper.UserRoleMapper;
import swtech.pageDesignControl.service.IUserRoleService;
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
 * @since 2019-11-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Transactional
    @Override
    public List<String> getRoleByUserId(int uid) {
        return userRoleMapper.getRoleByUserId(uid);
    }

    @Transactional
    @Override
    public boolean save(UserRole userRole) {
        int num = userRoleMapper.insert(userRole);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public int deleteByUserId(int uid) {
        return userRoleMapper.deleteByUserId(uid);
    }

    @Transactional
    @Override
    public int deleteByRoleId(int rid) {
        return userRoleMapper.deleteByUserId(rid);
    }
}
