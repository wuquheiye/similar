package job.service.impl;

import job.entity.User;
import job.mapper.*;
import job.service.IPersionService;
import job.vo.Person;
import job.vo.ReturnMsg;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class PersionService implements IPersionService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PersonEducationExperienceMapper personEducationExperienceMapper;

    @Resource
    private PersonInfoMapper personInfoMapper;

    @Resource
    private PersonJobWantedMapper personJobWantedMapper;

    @Resource
    private PersonWorkExperienceMapper personWorkExperienceMapper;

    @Transactional
    @Override
    public ReturnMsg save(Person person) {
        ReturnMsg msg = new ReturnMsg();
        if (person == null) {
            msg.setStatus("201");
            msg.setStatusMsg("录入个人信息失败，个人信息(person)不能为空");
            return msg;
        }
        if (person.getUser() == null || person.getUser().getEmail() == null) {
            msg.setStatus("202");
            msg.setStatusMsg("录入个人信息失败，用户信息(user)不能为空");
            return msg;
        }
        User userByEmail = userMapper.findUserByEmail(person.getUser().getEmail());
        if (userByEmail == null) {
            msg.setStatus("202");
            msg.setStatusMsg("录入个人信息失败，用户信息(user)不能为空");
            return msg;
        }
        int num1 = personEducationExperienceMapper.insert(person.getPersonEducationExperience());
        if (num1 <= 0) {
            msg.setStatus("203");
            msg.setStatusMsg("录入个人信息失败，教育经验(personEducationExperience)不能为空");
            return msg;
        }
        int num2 = personInfoMapper.insert(person.getPersonInfo());
        if (num2 <= 0) {
            msg.setStatus("204");
            msg.setStatusMsg("录入个人信息失败，个人信息详情(personInfo)不能为空");
            return msg;
        }
        int num3 = personJobWantedMapper.insert(person.getPersonJobWanted());
        if (num3 <= 0) {
            msg.setStatus("205");
            msg.setStatusMsg("录入个人信息失败，期望工作(personJobWanted)不能为空");
            return msg;
        }
        int num4 = personWorkExperienceMapper.insert(person.getPersonWorkExperience());
        if (num4 <= 0) {
            msg.setStatus("206");
            msg.setStatusMsg("录入个人信息失败，工作经验(personWorkExperience)不能为空");
            return msg;
        }
        msg.setStatus("202");
        msg.setStatusMsg("录入个人信息成功");
        return msg;
    }
}
