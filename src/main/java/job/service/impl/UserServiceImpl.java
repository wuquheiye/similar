package job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import job.entity.Permission;
import job.entity.Role;
import job.entity.User;
import job.mapper.PermissionMapper;
import job.mapper.RoleMapper;
import job.mapper.UserMapper;
import job.service.IUserService;
import job.vo.LoginVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    @Override
    public LoginVO getLoginVO(String email) {
        if ("".equals(email)) {
            return null;
        }
        LoginVO loginVO = new LoginVO();
        // 根据用户名查询实体类
        User user = userMapper.findUserByEmail(email);
        if (user == null) {
            return null;
        }
        loginVO.setUser(user);
        return loginVO;
    }

    @Override
    public List<User> selectByPageAndCondition(User user, int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        return userMapper.selectByPageAndCondition(user, pageStart, pageSize);
    }

    @Override
    public int selectCount() {
        return userMapper.selectCount();
    }

    @Override
    public boolean updateState(int id, int state) {
        int num = userMapper.updateState(id, state);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Permission> getPermission(String email) {
        List<Permission> permissions = new ArrayList<>();
        // 根据邮箱获取角色
        Role role = roleMapper.getRoleByEmail(email);
        if (role == null) {
            return null;
        }
        // 根据角色名获取权限
        List<Permission> permissionList = permissionMapper.getPermissionByRoleName(role.getName());
        for (Permission permission : permissionList) {
            permissions.add(permission);
        }
        return permissions;
    }
}
