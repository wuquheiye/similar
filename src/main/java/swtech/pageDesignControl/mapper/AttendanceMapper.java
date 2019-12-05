package swtech.pageDesignControl.mapper;

import org.apache.ibatis.annotations.Param;
import swtech.pageDesignControl.entity.Attendance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 袁君选
 * @since 2019-12-04
 */
public interface AttendanceMapper extends BaseMapper<Attendance> {
    /**
     * 分页查询
     *
     * @param attendance
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<Attendance> selectByPageAndCondition(@Param("attendance") Attendance attendance, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();
}
