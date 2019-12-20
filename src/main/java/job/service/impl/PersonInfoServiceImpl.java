package job.service.impl;

import job.entity.PersonInfo;
import job.mapper.PersonInfoMapper;
import job.service.IPersonInfoService;
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
public class PersonInfoServiceImpl extends ServiceImpl<PersonInfoMapper, PersonInfo> implements IPersonInfoService {

}
