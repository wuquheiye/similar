package job.controller.person;

import job.entity.PersonEducationExperience;
import job.service.IPersonEducationExperienceService;
import job.vo.ReturnMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 李鸿智
 * @since 2019-12-20
 */
@RequestMapping("/personeducationexperience")
@CrossOrigin //跨域
@Controller
@Slf4j
public class PersonEducationExperienceController  {

    @Resource
    private IPersonEducationExperienceService iPersonEducationExperienceService;

    @ResponseBody
    @RequestMapping("/update")
    public ReturnMsg updateById(@RequestBody PersonEducationExperience personEducationExperience) {
        ReturnMsg msg = new ReturnMsg();
        try {
            boolean isTrue = iPersonEducationExperienceService.updateById(personEducationExperience);
            if (isTrue) {
                msg.setStatus("200");
                msg.setStatusMsg("更新教育背景成功");
            } else {
                msg.setStatus("202");
                msg.setStatusMsg("更新教育背景失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("更新教育背景异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

}
