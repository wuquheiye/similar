package job.service.impl;

import job.entity.*;
import job.mapper.*;
import job.service.IPersonUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import job.vo.PersonVO;
import job.vo.ReturnMsg;
import job.vo.ReturnMsgPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-24
 */
@Service
public class PersonUserServiceImpl extends ServiceImpl<PersonUserMapper, PersonUser> implements IPersonUserService {

    @Resource
    private PersonUserMapper personUserMapper;

    @Resource
    private PersonEducationExperienceMapper personEducationExperienceMapper;

    @Resource
    private PersonInfoMapper personInfoMapper;

    @Resource
    private PersonJobWantedMapper personJobWantedMapper;

    @Resource
    private PersonWorkExperienceMapper personWorkExperienceMapper;

    @Override
    public ReturnMsgPage findAll(Integer page, Integer pageSize) {
        int pageStart = (page - 1) * pageSize;
        ReturnMsgPage msg = new ReturnMsgPage();
        List<PersonUser> personUserList = personUserMapper.findAll(pageStart, pageSize);
        List<PersonVO> personVOList = new ArrayList<>();
        for(PersonUser personUser:personUserList){
            // 获取教育经验
            PersonEducationExperience personEducationExperience = personEducationExperienceMapper.selectByPersonUserId(personUser.getId());
            // 获取个人信息
            PersonInfo personInfo = personInfoMapper.selectByPersonUserId(personUser.getId());
            // 获取期望工作
            PersonJobWanted personJobWanted = personJobWantedMapper.selectByPersonUserId(personUser.getId());
            // 获取工作经验
            PersonWorkExperience personWorkExperience = personWorkExperienceMapper.selectByPersonUserId(personUser.getId());
            PersonVO personVO = new PersonVO();
            personVO.setPersonUser(personUser);
            personVO.setPersonEducationExperience(personEducationExperience);
            personVO.setPersonInfo(personInfo);
            personVO.setPersonJobWanted(personJobWanted);
            personVO.setPersonWorkExperience(personWorkExperience);
            personVOList.add(personVO);
        }
        int totalSize = personUserMapper.selectCount();
        msg.setStatus("200");
        msg.setStatusMsg("获取简历列表成功");
        if (personUserList != null) {
            msg.setTotalPage((int) (Math.ceil(1.0 * totalSize / pageSize)));
        }
        msg.setCurrentPage(page);
        msg.setMsg(personVOList);
        msg.setTotalSize(totalSize);
        return msg;
    }
}
