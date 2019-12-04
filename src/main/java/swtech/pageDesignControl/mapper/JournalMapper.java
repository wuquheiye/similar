package swtech.pageDesignControl.mapper;

import swtech.pageDesignControl.common.vo.JournalVO;
import swtech.pageDesignControl.entity.Journal;
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
public interface JournalMapper extends BaseMapper<Journal> {

    List<JournalVO> selectByCharge(Integer uid,Integer rtype);

}
