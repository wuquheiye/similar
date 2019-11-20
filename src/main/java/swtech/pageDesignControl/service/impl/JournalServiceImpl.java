package swtech.pageDesignControl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.common.exception.ServiceException;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.Journal;
import swtech.pageDesignControl.mapper.JournalMapper;
import swtech.pageDesignControl.service.IJournalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-18
 */
@Service
@Slf4j
public class JournalServiceImpl extends ServiceImpl<JournalMapper, Journal> implements IJournalService {

    @Resource
    private  JournalMapper journalMapper;

    @Override
    @Transactional
    public ReturnMsg insertJournal(Journal journal) {
        ReturnMsg msg = new ReturnMsg();
        if(journal == null ) throw  new ServiceException("日志填写参数为空");
        int insert = journalMapper.insert(journal);
        if(insert == 0) throw  new ServiceException("日志录入失败");
        msg.setStatus("200");
        msg.setMsg(insert);
        msg.setStatusMsg("日志录入成功");
        return msg;
    }
}
