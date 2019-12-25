package job.controller;


import job.entity.PersonUser;
import job.service.IPersonUserService;
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

}
