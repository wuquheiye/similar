package job.controller.show;


import job.service.ICompanyService;
import job.vo.CompanyVO;
import job.vo.ReturnMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-23
 */
@CrossOrigin //跨域
@Controller
@Slf4j
@RequestMapping("/company")
public class CompanyController {

    @Resource
    private ICompanyService iCompanyService;

    @ResponseBody
    @RequestMapping("/save")
    public ReturnMsg save(@RequestBody CompanyVO companyVO) {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iCompanyService.save(companyVO);
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
