package swtech.pageDesignControl.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.common.exception.ServiceException;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.User;
import swtech.pageDesignControl.mapper.UserMapper;
import swtech.pageDesignControl.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-10-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    //	 定义一个静态的日志器变量
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;


    @Override
    @Transactional
    public ReturnMsg selectJobWanted(Integer id) {
        ReturnMsg msg =new ReturnMsg();
        if(id ==null) throw new ServiceException("参数不能为空");
        User userVO = userMapper.selectJobWanted(id);
        if(userVO !=null){
            msg.setStatus("200");
            msg.setStatusMsg("操作成功");
            msg.setMsg(userVO);
        }
        return msg;
    }


}
