package job.controller;


import job.entity.CompanyPosition;
import job.service.ICompanyPositionService;
import job.vo.CompanyPositionVO;
import job.vo.ReturnMsg;
import job.vo.ReturnMsgPage;
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
 * @since 2019-12-26
 */
@CrossOrigin //跨域
@Slf4j
@Controller
@RequestMapping("/companyposition")
public class CompanyPositionController {

    @Autowired
    private ICompanyPositionService iCompanyPositionService;

    @ResponseBody
    @RequestMapping("/selectcompanypositionbycondition")
    public ReturnMsgPage selectCompanyPositionByCondition(@RequestBody CompanyPositionVO companyPositionVO) {
        ReturnMsgPage msg = new ReturnMsgPage();
        try {
            List<CompanyPositionVO> companyPositionVOList = iCompanyPositionService.selectCompanyPositionByCondition(companyPositionVO, companyPositionVO.getPage(), companyPositionVO.getPageSize());
            int totalSize = iCompanyPositionService.selectCount(companyPositionVO);
            msg.setStatus("200");
            msg.setStatusMsg("公司职位查询分页成功");
            if (companyPositionVOList != null) {
                msg.setTotalPage((int) (Math.ceil(1.0 * totalSize / companyPositionVO.getPageSize())));
            }
            msg.setCurrentPage(companyPositionVO.getPage());
            msg.setMsg(companyPositionVOList);
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("公司职位查询分页异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @RequestMapping("/save")
    public ReturnMsg save(@RequestBody CompanyPosition companyPosition) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iCompanyPositionService.save(companyPosition);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("公司职位保存成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("公司职位保存失败 ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("公司职位保存异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @RequestMapping("/update")
    public ReturnMsg updateById(@RequestBody CompanyPosition companyPosition) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iCompanyPositionService.updateById(companyPosition);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("公司职位修改成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("公司职位修改失败 ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("公司职位修改异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}
