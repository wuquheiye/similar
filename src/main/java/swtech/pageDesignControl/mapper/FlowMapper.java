package swtech.pageDesignControl.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import swtech.pageDesignControl.common.vo.FlowVO;
import swtech.pageDesignControl.entity.Flow;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2019-11-18
 */
public interface FlowMapper extends BaseMapper<Flow> {

    List<FlowVO> selectByUid(Integer uid);

}
