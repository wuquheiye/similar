package swtech.pageDesignControl.service;

import swtech.pageDesignControl.entity.AskLeave;
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
public interface IAskLeaveService extends IService<AskLeave> {

    /**
     * 分页查询
     *
     * @param askLeave
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<AskLeave> selectByPageAndCondition(AskLeave askLeave, int pageStart, int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();

    /**
     * 插入多条数据
     *
     * @param askLeaveList
     * @return
     */
    int insertList(List<AskLeave> askLeaveList);
}
