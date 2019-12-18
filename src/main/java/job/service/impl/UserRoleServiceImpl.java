package job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import job.entity.UserRole;
import job.mapper.UserRoleMapper;
import job.service.IUserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public List<String> getRoleByUserId(int uid) {
        return userRoleMapper.getRoleByUserId(uid);
    }


    @Override
    public int deleteByUserId(int uid) {
        return userRoleMapper.deleteByUserId(uid);
    }

    @Override
    public int deleteByRoleId(int rid) {
        return userRoleMapper.deleteByUserId(rid);
    }
}
