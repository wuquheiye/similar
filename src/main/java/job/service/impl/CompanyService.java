package job.service.impl;

import job.entity.CompanyInfo;
import job.entity.User;
import job.mapper.*;
import job.service.ICompanyService;
import job.vo.CompanyVO;
import job.vo.ReturnMsg;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CompanyService implements ICompanyService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private CompanyInfoMapper companyInfoMapper;

    @Transactional
    @Override
    public ReturnMsg getCompany(User user) {
        ReturnMsg msg = new ReturnMsg();
        if (user == null){
            msg.setStatus("201");
            msg.setStatusMsg("获取公司信息失败，用户信息(user)不能为空");
            return msg;
        }
        if (user.getEmail() == null){
            msg.setStatus("202");
            msg.setStatusMsg("获取公司信息失败，用户信息邮箱(user.email)不能为null");
            return msg;
        }
        User userByEmail = userMapper.findUserByEmail(user.getEmail());
        if (userByEmail == null) {
            msg.setStatus("202");
            msg.setStatusMsg("录入公司信息失败，用户信息(user)不能为空");
            return msg;
        }
        // 获取公司信息
        CompanyInfo companyInfo = companyInfoMapper.findByUserId(userByEmail.getId());
        if (companyInfo == null){
            msg.setStatus("203");
            msg.setStatusMsg("获取公司信息失败，用户简历(personUser)不能为null");
            return msg;
        }
        CompanyVO companyVO = new CompanyVO();
        userByEmail.setPassword("");
        companyVO.setCompanyInfo(companyInfo);
        companyVO.setUser(userByEmail);
        msg.setStatus("200");
        msg.setStatusMsg("获取公司信息成功");
        msg.setMsg(companyVO);
        return msg;
    }

    @Transactional
    @Override
    public ReturnMsg save(CompanyVO companyVO) {
        ReturnMsg msg = new ReturnMsg();
        // 1.判断公司信息是否为空
        if (companyVO == null) {
            msg.setStatus("201");
            msg.setStatusMsg("录入公司信息失败，公司信息(company)不能为空");
            return msg;
        }
        // 2.判断用户信息是否为空
        if (companyVO.getUser() == null || companyVO.getUser().getEmail() == null) {
            msg.setStatus("202");
            msg.setStatusMsg("录入公司信息失败，用户信息(user)不能为空");
            return msg;
        }
        // 3.判断通过emil查询的用户信息是否为空
        User userByEmail = userMapper.findUserByEmail(companyVO.getUser().getEmail());
        if (userByEmail == null) {
            msg.setStatus("202");
            msg.setStatusMsg("录入公司信息失败，用户信息(user)不能为空");
            return msg;
        }
        // 4.设置公司信息所属用户
        companyVO.getCompanyInfo().setUserId(userByEmail.getId());
        // 5.判断是否插入
        int num = companyInfoMapper.insert(companyVO.getCompanyInfo());
        if (num <= 0) {
            msg.setStatus("203");
            msg.setStatusMsg("录入公司信息失败，公司信息(companyInfo)不能为空");
            return msg;
        }
        msg.setStatus("200");
        msg.setStatusMsg("录入公司信息成功");
        return msg;
    }
}
