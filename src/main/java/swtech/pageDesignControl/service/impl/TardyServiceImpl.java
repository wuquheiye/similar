package swtech.pageDesignControl.service.impl;

import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.entity.Tardy;
import swtech.pageDesignControl.mapper.TardyMapper;
import swtech.pageDesignControl.service.ITardyService;
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
public class TardyServiceImpl extends ServiceImpl<TardyMapper, Tardy> implements ITardyService {
    @Resource
    private TardyMapper tardyMapper;

    @Transactional
    @Override
    public boolean save(Tardy tardy) {
        int num = tardyMapper.insert(tardy);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean removeById(Serializable tid) {
        int num = tardyMapper.deleteById(tid);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateById(Tardy tardy) {
        int num = tardyMapper.updateById(tardy);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Tardy getById(Serializable  tid) {
        return tardyMapper.selectById(tid);
    }

    @Transactional
    @Override
    public List<Tardy> selectByPageAndCondition(Tardy tardy, int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        return tardyMapper.selectByPageAndCondition(tardy, pageStart, pageSize);
    }

    @Transactional
    @Override
    public int selectCount() {
        return tardyMapper.selectCount();
    }
}
