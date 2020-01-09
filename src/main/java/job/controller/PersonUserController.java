package job.controller;


import job.entity.PersonUser;
import job.service.IPersonUserService;
import job.vo.ReturnMsg;
import job.vo.ReturnMsgPage;
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
 * @since 2019-12-24
 */
@RequestMapping("/personuser")
@CrossOrigin //跨域
@Controller
@Slf4j
public class PersonUserController {
    @Resource
    private IPersonUserService iPersonUserService;

    @ResponseBody
    @RequestMapping("/update")
    public ReturnMsg updateById(@RequestBody PersonUser personUser) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iPersonUserService.updateById(personUser);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("更新简历成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("更新简历失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("更新简历异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @RequestMapping("/findall")
    public ReturnMsgPage findAll(Integer page, Integer size) {
        if (size == null || size <= 0) {
            size = 10;
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        ReturnMsgPage msg = new ReturnMsgPage();
        try {
            msg = iPersonUserService.findAll(page, size);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("查询所有简历异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    @ResponseBody
    @RequestMapping("/selectpersonuserbycondition")
    public ReturnMsgPage selectPersonUserByCondition(PersonUser personUser, Integer page, Integer size) {
        if (size == null || size <= 0) {
            size = 10;
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        ReturnMsgPage msg = new ReturnMsgPage();
        try {
            msg = iPersonUserService.selectPersonUserByCondition(personUser,page,size);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("查询所有简历异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}
