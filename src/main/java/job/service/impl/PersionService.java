package job.service.impl;

import job.entity.*;
import job.mapper.*;
import job.service.IPersionService;
import job.vo.PersonVO;
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

    @Resource
    private PersonUserMapper personUserMapper;

    @Transactional
    @Override
    public ReturnMsg getPserson(User user) {
        ReturnMsg msg = new ReturnMsg();
        if (user == null) {
            msg.setStatus("201");
            msg.setStatusMsg("获取个人信息失败，用户信息(user)不能为空");
            return msg;
        }
        if (user.getEmail() == null) {
            msg.setStatus("202");
            msg.setStatusMsg("获取个人信息失败，用户信息邮箱(user.email)不能为null");
            return msg;
        }
        User userByEmail = userMapper.findUserByEmail(user.getEmail());
        if (userByEmail == null) {
            msg.setStatus("202");
            msg.setStatusMsg("获取个人信息失败，用户信息(user)不能为空");
            return msg;
        }
        PersonUser personUser = userMapper.getPersonUser(user.getId());
        if (personUser == null) {
            msg.setStatus("203");
            msg.setStatusMsg("获取个人信息失败，用户简历(personUser)不能为null");
            return msg;
        }
        // 获取教育经验
        PersonEducationExperience personEducationExperience = personEducationExperienceMapper.selectByPersonUserId(personUser.getId());
        // 获取个人信息
        PersonInfo personInfo = personInfoMapper.selectByPersonUserId(personUser.getId());
        // 获取期望工作
        PersonJobWanted personJobWanted = personJobWantedMapper.selectByPersonUserId(personUser.getId());
        // 获取工作经验
        PersonWorkExperience personWorkExperience = personWorkExperienceMapper.selectByPersonUserId(personUser.getId());
        PersonVO personVO = new PersonVO();
        userByEmail.setPassword("");
        personVO.setUser(userByEmail);
        personVO.setPersonUser(personUser);
        personVO.setPersonEducationExperience(personEducationExperience);
        personVO.setPersonInfo(personInfo);
        personVO.setPersonJobWanted(personJobWanted);
        personVO.setPersonWorkExperience(personWorkExperience);
        msg.setStatus("200");
        msg.setStatusMsg("获取个人信息成功");
        msg.setMsg(personVO);
        return msg;
    }

    @Transactional
    @Override
    public ReturnMsg save(PersonVO personVO) {
        ReturnMsg msg = new ReturnMsg();
        // 1.判断个人信息是否为空
        if (personVO == null) {
            msg.setStatus("201");
            msg.setStatusMsg("录入个人信息失败，个人信息(person)不能为空");
            return msg;
        }
        // 2.判断用户信息是否为空
        if (personVO.getUser() == null || personVO.getUser().getEmail() == null) {
            msg.setStatus("202");
            msg.setStatusMsg("录入个人信息失败，用户信息(user)不能为空");
            return msg;
        }
        // 3.通过邮箱查找用户，判断用户是否为空
        User userByEmail = userMapper.findUserByEmail(personVO.getUser().getEmail());
        if (userByEmail == null) {
            msg.setStatus("202");
            msg.setStatusMsg("录入个人信息失败，用户信息(user)不能为空");
            return msg;
        }
        // 4.生成简历，判断简历是否生成
        PersonUser personUser = new PersonUser();
        personUser.setName(userByEmail.getUsername() + "的简历");
        personUser.setUserId(userByEmail.getId());
        int num = personUserMapper.insert(personUser);
        if (num <= 0 || personUser.getId() <= 0) {
            msg.setStatus("207");
            msg.setStatusMsg("录入个人信息失败，生成简历失败");
            return msg;
        }
        // 5.添加简历id
        personVO.getPersonEducationExperience().setPersonUserId(personUser.getId());
        personVO.getPersonInfo().setPersonUserId(personUser.getId());
        personVO.getPersonJobWanted().setPersonUserId(personUser.getId());
        personVO.getPersonWorkExperience().setPersonUserId(personUser.getId());
        // 6.插入简历列表所属项
        int num1 = personEducationExperienceMapper.insert(personVO.getPersonEducationExperience());
        if (num1 <= 0) {
            msg.setStatus("203");
            msg.setStatusMsg("录入个人信息失败，教育经验(personEducationExperience)不能为空");
            return msg;
        }
        int num2 = personInfoMapper.insert(personVO.getPersonInfo());
        if (num2 <= 0) {
            msg.setStatus("204");
            msg.setStatusMsg("录入个人信息失败，个人信息详情(personInfo)不能为空");
            return msg;
        }
        int num3 = personJobWantedMapper.insert(personVO.getPersonJobWanted());
        if (num3 <= 0) {
            msg.setStatus("205");
            msg.setStatusMsg("录入个人信息失败，期望工作(personJobWanted)不能为空");
            return msg;
        }
        int num4 = personWorkExperienceMapper.insert(personVO.getPersonWorkExperience());
        if (num4 <= 0) {
            msg.setStatus("206");
            msg.setStatusMsg("录入个人信息失败，工作经验(personWorkExperience)不能为空");
            return msg;
        }
        msg.setStatus("200");
        msg.setStatusMsg("录入个人信息成功");
        return msg;
    }

}
