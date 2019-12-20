package job.service.impl;

import job.entity.PersonJobWanted;
import job.mapper.PersonJobWantedMapper;
import job.service.IPersonJobWantedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-20
 */
@Service
public class PersonJobWantedServiceImpl extends ServiceImpl<PersonJobWantedMapper, PersonJobWanted> implements IPersonJobWantedService {

}
