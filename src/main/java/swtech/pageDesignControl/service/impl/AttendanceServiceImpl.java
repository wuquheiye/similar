package swtech.pageDesignControl.service.impl;

import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.entity.Attendance;
import swtech.pageDesignControl.mapper.AttendanceMapper;
import swtech.pageDesignControl.service.IAttendanceService;
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
 * @since 2019-12-04
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements IAttendanceService {

    @Resource
    private AttendanceMapper attendanceMapper;

    @Transactional
    @Override
    public boolean save(Attendance attendance) {
        int num = attendanceMapper.insert(attendance);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean removeById(Serializable aid) {
        int num = attendanceMapper.deleteById(aid);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateById(Attendance attendance) {
        int num = attendanceMapper.updateById(attendance);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public Attendance getById(Serializable  aid) {
        return attendanceMapper.selectById(aid);
    }

    @Transactional
    @Override
    public List<Attendance> selectByPageAndCondition(Attendance attendance, int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        return attendanceMapper.selectByPageAndCondition(attendance, pageStart, pageSize);
    }

    @Transactional
    @Override
    public int selectCount() {
        return attendanceMapper.selectCount();
    }
}

