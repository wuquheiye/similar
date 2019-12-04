package swtech.pageDesignControl.service.impl;

import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.entity.PersonInfomation;
import swtech.pageDesignControl.mapper.PersonInfomationMapper;
import swtech.pageDesignControl.service.IPersonInfomationService;
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
 * @author 袁君选
 * @since 2019-11-28
 */
@Service
public class PersonInfomationServiceImpl extends ServiceImpl<PersonInfomationMapper, PersonInfomation> implements IPersonInfomationService {
    @Resource
    private PersonInfomationMapper personInfomationMapper;

    @Transactional
    @Override
    public boolean save(PersonInfomation personInfomation) {
        int num = personInfomationMapper.insert(personInfomation);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean removeById(Serializable pid) {
        int num = personInfomationMapper.deleteById(pid);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateById(PersonInfomation personInfomation) {
        int num = personInfomationMapper.updateById(personInfomation);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public PersonInfomation getById(Serializable pid) {
        return personInfomationMapper.selectById(pid);
    }

    @Transactional
    @Override
    public List<PersonInfomation> selectByPageAndCondition(PersonInfomation personInfomation, int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        return personInfomationMapper.selectByPageAndCondition(personInfomation, pageStart, pageSize);
    }

    @Transactional
    @Override
    public int selectCount() {
        return personInfomationMapper.selectCount();
    }

    @Override
    public PersonInfomation selectByUid(int uid) {
        return personInfomationMapper.selectByUid(uid);
    }
}
