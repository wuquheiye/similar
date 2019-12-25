package job.controller.show;

import job.entity.User;
import job.service.IPersionService;
import job.vo.PersonVO;
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
 * @since 2019-11-19
 */
@CrossOrigin //跨域
@Controller
@Slf4j
@RequestMapping("/person")
public class PersonController {

    @Resource
    private IPersionService iPersionService;

    @ResponseBody
    @RequestMapping("/getperson")
    public ReturnMsg getPerson(@RequestBody User user){
        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iPersionService.getPserson(user);
        }catch (Exception e){
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("获取个人信息异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

    /**
     * 注册开始新建个人信息（简历）
     *
     * @param personVO
     * @return
     */
    @RequestMapping("/save")
    @ResponseBody
    public ReturnMsg save(@RequestBody PersonVO personVO) {
        ReturnMsg msg = new ReturnMsg();
        try {
            msg = iPersionService.save(personVO);
        } catch (Exception e) {
            e.printStackTrace();
            msg.setStatus("201");
            msg.setStatusMsg("新建个人信息异常");
            msg.setMsg(e.getMessage());
        }
        log.info(String.valueOf(msg));
        return msg;
    }

}

