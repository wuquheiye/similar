package swtech.pageDesignControl.service;

import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.Journal;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-18
 */
public interface IJournalService extends IService<Journal> {
    ReturnMsg insertJournal(Journal journal) throws IOException;
}
