package swtech.pageDesignControl.mapper;

import org.springframework.stereotype.Repository;
import swtech.pageDesignControl.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李鸿智
 * @since 2019-10-24
 */
public interface UserMapper extends BaseMapper<User> {

    //通过id获取简历信息
    User selectJobWanted(Integer id);




}
