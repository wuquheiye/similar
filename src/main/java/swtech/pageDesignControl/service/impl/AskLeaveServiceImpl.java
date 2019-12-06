package swtech.pageDesignControl.service.impl;

import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.entity.AskLeave;
import swtech.pageDesignControl.mapper.AskLeaveMapper;
import swtech.pageDesignControl.service.IAskLeaveService;
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
 * @author 袁君选
 * @since 2019-12-06
 */
@Service
public class AskLeaveServiceImpl extends ServiceImpl<AskLeaveMapper, AskLeave> implements IAskLeaveService {
    @Resource
    private AskLeaveMapper askLeaveMapper;

    @Transactional
    @Override
    public boolean save(AskLeave askLeave) {
        int num = askLeaveMapper.insert(askLeave);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean removeById(Serializable did) {
        int num = askLeaveMapper.deleteById(did);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateById(AskLeave askLeave) {
        int num = askLeaveMapper.updateById(askLeave);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public AskLeave getById(Serializable  lid) {
        return askLeaveMapper.selectById(lid);
    }

    @Transactional
    @Override
    public List<AskLeave> selectByPageAndCondition(AskLeave askLeave, int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        return askLeaveMapper.selectByPageAndCondition(askLeave, pageStart, pageSize);
    }

    @Transactional
    @Override
    public int selectCount() {
        return askLeaveMapper.selectCount();
    }
}
