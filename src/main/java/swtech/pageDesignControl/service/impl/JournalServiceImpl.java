package swtech.pageDesignControl.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.common.exception.ServiceException;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.common.websocket.WebSocketServer;
import swtech.pageDesignControl.entity.Journal;
import swtech.pageDesignControl.entity.Users;
import swtech.pageDesignControl.mapper.JournalMapper;
import swtech.pageDesignControl.mapper.UsersMapper;
import swtech.pageDesignControl.service.IJournalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    @Resource
    private UsersMapper usersMapper;




    @Override
    @Transactional
    public ReturnMsg insertJournal(Journal journal) throws IOException {
        ReturnMsg msg = new ReturnMsg();
        if(journal == null ) throw  new ServiceException("日志填写参数为空");
        int insert = journalMapper.insert(journal);
        if(insert == 0) throw  new ServiceException("日志录入失败");
        //暂时推送到公共窗口测试 测试后推送给指定窗口
        Users users = usersMapper.selectById(journal.getUid());
        Map<String,Object> map = new HashMap<>();
        map.put("journal",journal);
        map.put("users",users);
        String sid =String.valueOf(journal.getFuidCharge());

        String sidtwo=String.valueOf(journal.getFuidManager());
        WebSocketServer.sendInfo(JSONObject.fromObject(map).toString(),sid);
        WebSocketServer.sendInfo(JSONObject.fromObject(map).toString(),"33");//主管
        msg.setStatus("200");
        msg.setMsg(insert);
        msg.setStatusMsg("日志录入成功");
        return msg;
    }
}
