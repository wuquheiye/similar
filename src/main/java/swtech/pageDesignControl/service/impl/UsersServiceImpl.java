package swtech.pageDesignControl.service.impl;

import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.entity.Users;
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
    public Users selectById(int did) {
        return usersMapper.selectById(did);
    }

    @Transactional
    @Override
    public List<Users> selectByPageAndCondition(Users users, int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        return usersMapper.selectByPageAndCondition(users, pageStart, pageSize);
    }

    @Transactional
    @Override
    public int selectCount() {
        return usersMapper.selectCount();
    }
}
