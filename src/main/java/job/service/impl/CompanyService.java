package job.service.impl;

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
    public ReturnMsg save(CompanyVO companyVO) {
        ReturnMsg msg = new ReturnMsg();
        if (companyVO == null) {
            msg.setStatus("201");
            msg.setStatusMsg("录入公司信息失败，公司信息(company)不能为空");
            return msg;
        }
        if (companyVO.getUser() == null || companyVO.getUser().getEmail() == null) {
            msg.setStatus("202");
            msg.setStatusMsg("录入公司信息失败，用户信息(user)不能为空");
            return msg;
        }
        User userByEmail = userMapper.findUserByEmail(companyVO.getUser().getEmail());
        if (userByEmail == null) {
            msg.setStatus("202");
            msg.setStatusMsg("录入公司信息失败，用户信息(user)不能为空");
            return msg;
        }
        int num = companyInfoMapper.insert(companyVO.getCompanyInfo());
        if (num <= 0) {
            msg.setStatus("203");
            msg.setStatusMsg("录入个人信息失败，公司信息(companyInfo)不能为空");
            return msg;
        }
        msg.setStatus("200");
        msg.setStatusMsg("录入公司信息成功");
        return msg;
    }
}
