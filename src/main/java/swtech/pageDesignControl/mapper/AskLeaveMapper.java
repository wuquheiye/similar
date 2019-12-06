package swtech.pageDesignControl.mapper;

import org.apache.ibatis.annotations.Param;
import swtech.pageDesignControl.entity.AskLeave;
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
public interface AskLeaveMapper extends BaseMapper<AskLeave> {
    /**
     * 分页查询
     *
     * @param askLeave
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<AskLeave> selectByPageAndCondition(@Param("askLeave") AskLeave askLeave, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();
}
