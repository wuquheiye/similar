package swtech.pageDesignControl.service;

import swtech.pageDesignControl.entity.Attendance;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁君选
 * @since 2019-12-04
 */
public interface IAttendanceService extends IService<Attendance> {
    /**
     * 分页查询
     *
     * @param attendance
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<Attendance> selectByPageAndCondition(Attendance attendance, int pageStart, int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();
}
