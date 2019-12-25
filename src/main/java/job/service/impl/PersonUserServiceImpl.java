package job.service.impl;

import job.entity.PersonUser;
import job.mapper.PersonUserMapper;
import job.service.IPersonUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-24
 */
@Service
public class PersonUserServiceImpl extends ServiceImpl<PersonUserMapper, PersonUser> implements IPersonUserService {

}
