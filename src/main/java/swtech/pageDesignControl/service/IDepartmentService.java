package swtech.pageDesignControl.service;

import org.apache.ibatis.annotations.Param;
import swtech.pageDesignControl.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-19
 */
public interface IDepartmentService extends IService<Department> {
    /**
     * 查询单个
     *
     * @param did
     * @return
     */
    Department selectById(int did);

    /**
     * 分页查询
     *
     * @param department
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<Department> selectByPageAndCondition(Department department, int pageStart, int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();
}
