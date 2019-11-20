package swtech.pageDesignControl.mapper;

import org.apache.ibatis.annotations.Param;
import swtech.pageDesignControl.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-19
 */
public interface DepartmentMapper extends BaseMapper<Department> {
    /**
     * 分页查询
     *
     * @param department
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<Department> selectByPageAndCondition(@Param("department") Department department, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();
}
