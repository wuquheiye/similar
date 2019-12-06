package swtech.pageDesignControl.service;

import swtech.pageDesignControl.entity.Tardy;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁君选
 * @since 2019-12-06
 */
public interface ITardyService extends IService<Tardy> {

    /**
     * 分页查询
     *
     * @param tardy
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<Tardy> selectByPageAndCondition(Tardy tardy, int pageStart, int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();
}
