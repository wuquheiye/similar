package job.service.impl;

import job.entity.*;
import job.mapper.*;
import job.service.IPersonUserPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import job.utils.MailUTil;
import job.vo.PersonUserPositionVO;
import job.vo.PersonVO;
import job.vo.ReturnMsg;
import job.vo.ReturnMsgPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 李鸿智
 * @since 2020-01-06
 */
@Service
public class PersonUserPositionServiceImpl extends ServiceImpl<PersonUserPositionMapper, PersonUserPosition> implements IPersonUserPositionService {

    @Autowired
    private MailUTil mailUTil;

    @Autowired
    private TemplateEngine templateEngine;

    @Resource
    private PersonUserPositionMapper personUserPositionMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PersonUserMapper personUserMapper;

    @Resource
    private CompanyInfoMapper companyInfoMapper;

    @Resource
    private CompanyPositionMapper companyPositionMapper;

    @Resource
    private PersonEducationExperienceMapper personEducationExperienceMapper;

    @Resource
    private PersonInfoMapper personInfoMapper;

    @Resource
    private PersonJobWantedMapper personJobWantedMapper;

    @Resource
    private PersonWorkExperienceMapper personWorkExperienceMapper;


    @Override
    @Transactional
    public ReturnMsg save(String telephonenumber, int companyPositionId) {
        ReturnMsg msg = new ReturnMsg();
        PersonUserPosition personUserPosition = new PersonUserPosition();
        // 1.根据传过来的简历查询用户
        User userByEmail = userMapper.findUserByTelephonenumber(telephonenumber);
        if (userByEmail == null) {
            msg.setStatus("201");
            msg.setStatusMsg("投递简历失败，用户信息(user)不能为空");
            return msg;
        }
        // 2.根据用户查询简历
        PersonUser personUser = personUserMapper.getPersonUser(userByEmail.getId());
        if (userByEmail == null) {
            msg.setStatus("202");
            msg.setStatusMsg("投递简历失败，简历信息(personUser)不能为空");
            return msg;
        }
        personUserPosition.setPersonUserId(personUser.getId());
        personUserPosition.setCompanyPositionId(companyPositionId);
        // 3.判断此简历是否投递该公司
        PersonUserPosition personUserPositionMapperBy = personUserPositionMapper.findByPersonUserIdAndCompanyPositionId(personUser.getId(), companyPositionId);
        if (personUserPositionMapperBy != null) {
            msg.setStatus("203");
            msg.setStatusMsg("该职位您投递过，请勿重复投递。");
            return msg;
        }
        int num = personUserPositionMapper.insert(personUserPosition);
        if (num <= 0) {
            msg.setStatus("204");
            msg.setStatusMsg("投递简历失败");
            return msg;
        }
        msg.setStatus("200");
        msg.setStatusMsg("投递简历成功");
        return msg;
    }

    @Override
    @Transactional
    public ReturnMsgPage findByPersonUserId(int personUserId, int companyPositionId, int state, int page, int pageSize) {
        int pageStart = (page - 1) * pageSize;
        ReturnMsgPage msg = new ReturnMsgPage();
        List<PersonUserPositionVO> personUserPositionVOList = new ArrayList<>();
        // 1.根据传过来的简历id查询投递关系
        List<PersonUserPosition> personUserPositionList = personUserPositionMapper.findByPersonUserIdAndState(personUserId, companyPositionId, state, pageStart, pageSize);
        // 2.循环组装
        for (PersonUserPosition personUserPosition : personUserPositionList) {
            PersonUserPositionVO personUserPositionVO = new PersonUserPositionVO();
            // 4.1 添加投递关系
            personUserPositionVO.setPersonUserPosition(personUserPosition);
            // 4.2 添加简历
            PersonUser personUser = personUserMapper.selectById(personUserPosition.getPersonUserId());
            personUserPositionVO.setPersonUser(personUser);
            if (personUser != null) {
                // 4.4.1获取教育经验
                PersonEducationExperience personEducationExperience = personEducationExperienceMapper.selectByPersonUserId(personUser.getId());
                // 4.4.2获取个人信息
                PersonInfo personInfo = personInfoMapper.selectByPersonUserId(personUser.getId());
                // 4.4.3获取期望工作
                PersonJobWanted personJobWanted = personJobWantedMapper.selectByPersonUserId(personUser.getId());
                // 4.4.4获取工作经验
                PersonWorkExperience personWorkExperience = personWorkExperienceMapper.selectByPersonUserId(personUser.getId());
                PersonVO personVO = new PersonVO();
                personVO.setPersonEducationExperience(personEducationExperience);
                personVO.setPersonInfo(personInfo);
                personVO.setPersonJobWanted(personJobWanted);
                personVO.setPersonWorkExperience(personWorkExperience);
                personUserPositionVO.setPersonVO(personVO);
            }
            // 4.3 添加公司职位信息
            CompanyPosition companyPosition = companyPositionMapper.selectById(personUserPosition.getCompanyPositionId());
            if (companyPosition == null) {
                msg.setStatus("201");
                msg.setStatusMsg("查询个人投递箱失败，公司职位(companyPosition)不能为空");
                return msg;
            }
            personUserPositionVO.setCompanyPosition(companyPosition);
            // 4.5 添加公司信息
            personUserPositionVO.setCompanyInfo(companyInfoMapper.selectById(companyPosition.getCompanyInfoId()));
            // 5.组装
            personUserPositionVOList.add(personUserPositionVO);
        }
        int totalSize = personUserPositionMapper.selectCount(personUserId, companyPositionId, state);
        msg.setStatus("200");
        msg.setStatusMsg("查询个人投递箱成功");
        if (personUserPositionVOList != null) {
            msg.setTotalPage((int) (Math.ceil(1.0 * totalSize / pageSize)));
        }
        msg.setCurrentPage(page);
        msg.setMsg(personUserPositionVOList);
        msg.setTotalSize(totalSize);
        return msg;
    }

    @Override
    public ReturnMsg inappropriate(int id, int state) {
        ReturnMsg msg = new ReturnMsg();
        boolean isTrue = personUserPositionMapper.updateState(id, state);
        if (isTrue) {
            msg.setStatus("200");
            msg.setStatusMsg("更改成功");
        } else {
            msg.setStatus("202");
            msg.setStatusMsg("更改失败");
        }
        return msg;
    }

    @Override
    @Transactional
    public ReturnMsg informInterview(int id, int state ,String email) {
        ReturnMsg msg = new ReturnMsg();
        boolean isTrue = personUserPositionMapper.updateState(id, state);
        if (isTrue) {
            // 2.获取邮件模板
            Context context = new Context();
            // 3.设置随机数
            context.setVariable("verificationCode", "123");
            // 4.设置模板文件
            String emailContent = templateEngine.process("interviewEmailTemplate", context);
            // 5.发生模板邮件
            mailUTil.sendHtmlMail(email, "面试邀请", emailContent);
            msg.setStatus("200");
            msg.setStatusMsg("更改成功");
        } else {
            msg.setStatus("202");
            msg.setStatusMsg("更改失败");
        }
        return msg;
    }

}
