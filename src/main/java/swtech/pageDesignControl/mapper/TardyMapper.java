package swtech.pageDesignControl.mapper;

import org.apache.ibatis.annotations.Param;
import swtech.pageDesignControl.entity.Tardy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 袁君选
 * @since 2019-12-06
 */
public interface TardyMapper extends BaseMapper<Tardy> {
    /**
     * 分页查询
     *
     * @param tardy
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<Tardy> selectByPageAndCondition(@Param("tardy") Tardy tardy, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();
}
