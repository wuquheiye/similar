package job.controller;

import job.entity.CompanyInfo;
import job.entity.Permission;
import job.service.ICompanyInfoService;
import job.service.IPermissionService;
import job.vo.PermissionVO;
import job.vo.ReturnMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-20
 */
@CrossOrigin //跨域
@Slf4j
@Controller
@RequestMapping("/companyinfo")
public class CompanyInfoController {

    @Autowired
    private ICompanyInfoService iCompanyInfoService;

    @ResponseBody
    @RequestMapping("/save")
    public ReturnMsg save(@RequestBody CompanyInfo companyInfo) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iCompanyInfoService.updateById(companyInfo);
            if(isTrue){
                msg.setStatus("200");
                msg.setStatusMsg("公司信息修改成功");
            }else {
                msg.setStatus("202");
                msg.setStatusMsg("公司信息修改失败 ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建公司信息异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}
