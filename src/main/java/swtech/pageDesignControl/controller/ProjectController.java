package swtech.pageDesignControl.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import swtech.pageDesignControl.common.exception.ServiceException;
import swtech.pageDesignControl.common.vo.ProjectAndScheduleVO;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.Journal;
import swtech.pageDesignControl.entity.Project;
import swtech.pageDesignControl.entity.UpdateProject;
import swtech.pageDesignControl.service.IProjectService;
import swtech.pageDesignControl.service.IUpdateProjectService;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLOutput;
import java.sql.Wrapper;
import java.util.List;
import java.util.Map;


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

    @Resource
    private IUpdateProjectService iUpdateProjectService;



    /**
     * 根据pid获取相关信息
     * @param pid
     * @param model
     * @return
     */
    @GetMapping("/selectProjectByid/{pid}/{status}")
    public  String selectProjectByid(@PathVariable("pid") Integer pid,@PathVariable("status") Integer status, Model model){

            Map<String, Object> stringObjectMap = iProjectService.selectProjectByid(pid);
            model.addAllAttributes(stringObjectMap);
            log.info(JSONObject.fromObject(stringObjectMap).toString());
        if(status == 0){
            return "./use/create/selectProject";
        }else {
            return  "./use/create/updatProject";
        }

    }
    /**
     * 通过uid查询未完成的项目
     * @param uid
     * @return
     */
    @ResponseBody
    @GetMapping("selectUndone/{uid}")
    public ReturnMsg selectUndone(@PathVariable("uid") Integer uid){
        ReturnMsg msg = new ReturnMsg();
        if(uid==null) throw  new ServiceException("参数不能为空");
        QueryWrapper qw = new QueryWrapper();
        qw.eq("uid",uid);
        qw.isNull("pend_time");
        qw.orderByDesc("pid");
        List<Project> list = iProjectService.list(qw);
        if(list != null){
            msg.setStatus("200");
            msg.setMsg(list);
            msg.setStatusMsg("查询未完成项目成功");
        }else {
            msg.setStatus("201");
            msg.setMsg(list);
            msg.setStatusMsg("没有未完成项目");
        }
            return msg ;
    }

    /**
     * 根据uid获取其所创建的all项目信息
     * @param uid
     * @return
     */
    @ResponseBody
    @GetMapping("selectProjectMyAll")
    public ReturnMsg selectProjectMyAll(@RequestParam("uid") Integer uid) {
        ReturnMsg msg = new ReturnMsg();
        try {
            QueryWrapper qw = new QueryWrapper<Project>();
            if(uid != null){
                qw.eq("uid",uid);
            }
//            List list = iProjectService.list(qw);
            List list = iProjectService.list();
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
            msg.setStatusMsg("项目进度更新失败");
            msg.setMsg(e.getMessage());
        }
        return  msg;
    }


    /**
     * 查询当前用户参与过的项目信息
     * @param uid
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectAllProject")
    public ReturnMsg selectAllProject(@RequestParam("uid")Integer uid){
        ReturnMsg msg =new ReturnMsg();
        if(uid==null)throw  new ServiceException("参数uid不能为空");
        String peam=","+uid+",";
        QueryWrapper qw = new QueryWrapper();
        qw.isNull("pend_time");
        qw.like("pteam",peam);
        List<Project> list = iProjectService.list(qw);
        if(list ==null)throw new ServiceException("列表为空,无数据");
        msg.setStatus("200");
        msg.setStatusMsg("所有项目获取成功");
        msg.setMsg(list);
        return msg;
    }

    /**
     * 查询所有项目和日志信息
     * @return
     */
    @ResponseBody
    @GetMapping("selectProjectJournal")
    public ReturnMsg  selectProjectJournal(@RequestParam("uid")Integer uid , @RequestParam("rtype")Integer rtype){
        ReturnMsg msg =new ReturnMsg();
            try {
                msg = iProjectService.selectProjectJournal(uid,rtype);
            }catch (Exception e){
                e.printStackTrace();
                log.info(e.getMessage());
                msg.setStatus("201");
                msg.setStatusMsg("查询所有项目和日志信息失败");
                msg.setMsg(e.getMessage());
            }
        return msg;
    }

    /**
     *
     * @param upId
     * @return
     */

    @GetMapping("selectUpByUpId/{upId}")
    public String selectUpByUpId(@PathVariable("upId") Integer upId,Model model){
        if(upId==null)throw new ServiceException("参数不能为空");
        UpdateProject byId = iUpdateProjectService.getById(upId);
        Project byId1 = iProjectService.getById(byId.getPid());
        model.addAttribute("updateProject",byId);
        model.addAttribute("project",byId1);
        if(byId!=null){
            return "./use/create/selectUp";
        }else {
            return "./use/403";
        }
    }
}
