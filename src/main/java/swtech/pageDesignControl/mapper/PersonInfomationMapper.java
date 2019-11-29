package swtech.pageDesignControl.mapper;

import org.apache.ibatis.annotations.Param;
import swtech.pageDesignControl.entity.PersonInfomation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-28
 */
public interface PersonInfomationMapper extends BaseMapper<PersonInfomation> {

    /**
     * 分页查询
     *
     * @param personInfomation
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<PersonInfomation> selectByPageAndCondition(@Param("personInfomation") PersonInfomation personInfomation, @Param("pageStart") int pageStart, @Param("pageSize") int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();

}
