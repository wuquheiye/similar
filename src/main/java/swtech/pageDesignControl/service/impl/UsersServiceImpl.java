package swtech.pageDesignControl.service.impl;

import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.common.vo.UsersVO;
import swtech.pageDesignControl.common.vo.LoginVO;
import swtech.pageDesignControl.entity.Department;
import swtech.pageDesignControl.entity.Role;
import swtech.pageDesignControl.entity.Users;
import swtech.pageDesignControl.mapper.DepartmentMapper;
import swtech.pageDesignControl.mapper.RoleMapper;
import swtech.pageDesignControl.mapper.UserRoleMapper;
import swtech.pageDesignControl.mapper.UsersMapper;
import swtech.pageDesignControl.service.IUsersService;
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

    @Override
    public LoginVO getLoginVO(String uusername) {
        LoginVO loginVO = new LoginVO();
        Users users = usersMapper.findUsersByName(uusername);
        if (users == null) {
            return null;
        }
        Role role = roleMapper.getRoleByUsername(uusername);
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
}
