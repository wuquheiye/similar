package job.controller.person;


import job.entity.PersonJobWanted;
import job.service.IPersonJobWantedService;
import job.vo.ReturnMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-20
 */
@RequestMapping("/personjobwanted")
@CrossOrigin //跨域
@Controller
@Slf4j
public class PersonJobWantedController {

    @Resource
    private IPersonJobWantedService iPersonJobWantedService;

    @ResponseBody
    @RequestMapping("/update")
    public ReturnMsg updateById(@RequestBody PersonJobWanted personJobWanted) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iPersonJobWantedService.updateById(personJobWanted);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("更新期望工作成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("更新期望工作失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("更新期望工作异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }
}
