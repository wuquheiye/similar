package job.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import job.entity.Role;
import job.mapper.RoleMapper;
import job.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
