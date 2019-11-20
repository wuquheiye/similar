package swtech.pageDesignControl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import swtech.pageDesignControl.common.exception.ServiceException;
import swtech.pageDesignControl.common.vo.ProjectAndScheduleVO;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.Project;
import swtech.pageDesignControl.entity.UpdateProject;
import swtech.pageDesignControl.service.IProjectService;
import swtech.pageDesignControl.service.IUpdateProjectService;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Wrapper;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-18
 */
@Slf4j
@Controller
@CrossOrigin
@RequestMapping("/project")
public class ProjectController {

    @Resource
    private IProjectService iProjectService;

    /**
     * 根据uid获取其所创建的项目信息
     * @param uid
     * @return
     */
    @ResponseBody
    @GetMapping("selectProjectMyAll")
    public ReturnMsg selectProjectMyAll(@RequestParam("uid") Integer uid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            QueryWrapper qw = new QueryWrapper<Project>();
            qw.eq("uid",uid);
            List list = iProjectService.list(qw);
            if(list ==null)throw new ServiceException("列表为空,无数据");
            msg.setStatus("200");
            msg.setStatusMsg("历史记录获取成功");
            msg.setMsg(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("历史记录获取失败");
            msg.setMsg(e.getMessage());
        }
        return msg;
    }

    /**
     * 创建项目
     * @param projectAndScheduleVO
     * @return
     */
    @ResponseBody
    @PostMapping("/insertProject")
    public ReturnMsg leaveInsert(@RequestBody ProjectAndScheduleVO projectAndScheduleVO){
        ReturnMsg msg =new ReturnMsg();
        try {
            msg = iProjectService.createProject(projectAndScheduleVO);
        }catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("项目创建失败");
            msg.setMsg(e.getMessage());
        }

        return  msg;

    }

    /**
     * 项目进度更新
     * @param projectAndScheduleVO
     * @return
     */
    @ResponseBody
    @PostMapping("/updateProject")
    public  ReturnMsg updateProject(@RequestBody ProjectAndScheduleVO projectAndScheduleVO) throws IOException {
        ReturnMsg msg =new ReturnMsg();
        try {
            msg = iProjectService.updateProject(projectAndScheduleVO);
        }catch (Exception e){
            e.printStackTrace();
            log.info(e.getMessage());
            msg.setStatus("201");
            msg.setStatusMsg("项目创建失败");
            msg.setMsg(e.getMessage());
        }
        return  msg;
    }

}
