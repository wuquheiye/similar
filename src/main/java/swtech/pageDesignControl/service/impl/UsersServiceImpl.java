package swtech.pageDesignControl.service.impl;

import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.common.vo.UsersVo;
import swtech.pageDesignControl.entity.Department;
import swtech.pageDesignControl.entity.Users;
import swtech.pageDesignControl.mapper.DepartmentMapper;
import swtech.pageDesignControl.mapper.UsersMapper;
import swtech.pageDesignControl.service.IUsersService;
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
 * @since 2019-11-18
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private DepartmentMapper departmentMapper;

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
    public List<UsersVo> selectByPageAndCondition(Users users, int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        List<UsersVo> usersVoList = usersMapper.selectByPageAndCondition(users, pageStart, pageSize);
        for (UsersVo usersVo: usersVoList){
            Department department = departmentMapper.selectById(usersVo.getDid());
            usersVo.setDepartment(department);
        }
        return usersVoList;
    }

    @Transactional
    @Override
    public int selectCount() {
        return usersMapper.selectCount();
    }
}
