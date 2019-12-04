package swtech.pageDesignControl.service.impl;

import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.common.vo.*;
import swtech.pageDesignControl.entity.Department;
import swtech.pageDesignControl.entity.Permission;
import swtech.pageDesignControl.entity.Role;
import swtech.pageDesignControl.entity.Users;
import swtech.pageDesignControl.mapper.*;
import swtech.pageDesignControl.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
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
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public LoginVO getLoginVO(String uusername) {
        if ("".equals(uusername)) {
            return null;
        }
        LoginVO loginVO = new LoginVO();
        // 根据用户名查询实体类
        Users users = usersMapper.findUsersByName(uusername);
        if (users == null) {
            return null;
        }
        // 根据用户名查询角色
        Role role = roleMapper.getRoleByUsername(uusername);
        // 根据用户部门id查询部门
        Department department = departmentMapper.selectById(users.getDid());
        int fuidCharge = 0;
        int fuidManager = 0;
        LoginUsersVO loginUsersVO = new LoginUsersVO();
        loginUsersVO.setUid(users.getUid());
        loginUsersVO.setDid(users.getDid());
        loginUsersVO.setUusername(users.getUusername());
        // 查询本部门主管
        List<Users> chargeUser = usersMapper.findUsersByDepartmentAndRole(users.getDid(), 3);
        // 查询本部门经理
        List<Users> manageUser = usersMapper.findUsersByDepartmentAndRole(users.getDid(), 4);
        if (manageUser != null && manageUser.size() > 0 && manageUser.get(0) != null && manageUser.get(0).getUid() > 0) {
            fuidCharge = manageUser.get(0).getUid();
        }
        if (chargeUser != null && chargeUser.size() > 0 && chargeUser.get(0) != null && chargeUser.get(0).getUid() > 0) {
            fuidManager = chargeUser.get(0).getUid();
        }
        if (role.getRtype() == 1 || role.getRtype() == 2) {
            loginUsersVO.setFuidCharge(fuidCharge);
            loginUsersVO.setFuidManager(fuidCharge);
        } else if (role.getRtype() == 3) {
            loginUsersVO.setFuidCharge(0);
            loginUsersVO.setFuidManager(fuidCharge);
        } else if (role.getRtype() == 4) {
            loginUsersVO.setFuidCharge(0);
            loginUsersVO.setFuidManager(0);
        }
        loginVO.setUsers(loginUsersVO);
        loginVO.setDepartment(department);
        loginVO.setRole(role);
        return loginVO;
    }

    @Transactional
    @Override
    public boolean save(Users users) {
        int num = usersMapper.insert(users);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean removeById(Serializable uid) {
        int num = usersMapper.deleteById(uid);
        userRoleMapper.deleteByUserId((Integer) uid);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateById(Users users) {
        int num = usersMapper.updateById(users);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Users getById(Serializable did) {
        return usersMapper.selectById(did);
    }

    @Transactional
    @Override
    public List<UsersVO> selectByPageAndCondition(Users users, int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        List<UsersVO> usersVOList = usersMapper.selectByPageAndCondition(users, pageStart, pageSize);
        for (UsersVO usersVo : usersVOList) {
            Department department = departmentMapper.selectById(usersVo.getDid());
            usersVo.setDepartment(department);
        }
        return usersVOList;
    }

    @Transactional
    @Override
    public int selectCount() {
        return usersMapper.selectCount();
    }

    @Override
    public boolean updateUustate(int uid, int ustate) {
        int num = usersMapper.updateUustate(uid, ustate);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<Permission> getPermission(String uusername) {
        List<Permission> permissions = new ArrayList<>();
        Role role = roleMapper.getRoleByUsername(uusername);
        if (role == null) {
            return null;
        }
        List<Permission> permissionList = permissionMapper.getPermissionByRoleName(role.getRname());
        for (Permission permission : permissionList) {
            permissions.add(permission);
        }
        return permissions;
    }

    @Override
    public List<Users> selectUsersByRid(int rid) {
        return usersMapper.selectUsersByRid(rid);
    }
}
