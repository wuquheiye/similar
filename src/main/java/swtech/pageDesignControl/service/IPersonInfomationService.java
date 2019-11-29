package swtech.pageDesignControl.service;

import swtech.pageDesignControl.entity.PersonInfomation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-28
 */
public interface IPersonInfomationService extends IService<PersonInfomation> {

    /**
     * 分页查询
     *
     * @param personInfomation
     * @param pageStart
     * @param pageSize
     * @return
     */
    List<PersonInfomation> selectByPageAndCondition(PersonInfomation personInfomation, int pageStart, int pageSize);

    /**
     * 查询个数
     *
     * @return
     */
    int selectCount();

}
