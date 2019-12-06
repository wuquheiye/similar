package swtech.pageDesignControl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.ibatis.javassist.bytecode.Descriptor;
import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.common.exception.ServiceException;
import swtech.pageDesignControl.common.utils.ComparatorTime;
import swtech.pageDesignControl.common.vo.ProjectAndScheduleVO;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.common.websocket.WebSocketServer;
import swtech.pageDesignControl.common.websocket.WebsocketServerTwo;
import swtech.pageDesignControl.entity.Journal;
import swtech.pageDesignControl.entity.Project;
import swtech.pageDesignControl.entity.UpdateProject;
import swtech.pageDesignControl.enums.Role;
import swtech.pageDesignControl.mapper.JournalMapper;
import swtech.pageDesignControl.mapper.ProjectMapper;
import swtech.pageDesignControl.mapper.UpdateProjectMapper;
import swtech.pageDesignControl.service.IProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-18
 */
@Service
@Slf4j
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

    @Resource
    private  ProjectMapper projectMapper ;
    @Resource
    private UpdateProjectMapper updateProjectMapper;
    @Resource
    private JournalMapper journalMapper;

    @Override
    @Transactional
    public ReturnMsg createProject(ProjectAndScheduleVO projectAndScheduleVO) throws IOException {
        ReturnMsg msg = new ReturnMsg();
        if(projectAndScheduleVO==null)throw  new ServiceException("参数不能为空");
        int insert = projectMapper.insert(projectAndScheduleVO.getProject());
        if(insert ==0) throw  new ServiceException("project表插入失败");
//        projectAndScheduleVO.getUpdateProject()
//                            .setPid(projectAndScheduleVO.getProject().getPid());
//        int insert1 = updateProjectMapper.insert(projectAndScheduleVO.getUpdateProject());
//        if(insert1 == 0) throw  new ServiceException("updateProject表插入失败");
        WebSocketServer.sendInfo(
                JSONObject.fromObject(
                        projectAndScheduleVO).toString(),"0");
        log.info("项目创建成功，向前台返回判断");
        msg.setStatus("200");
        msg.setMsg(insert);
        msg.setStatusMsg("项目创建成功");
        return msg;
    }

    @Override
    @Transactional
    public ReturnMsg updateProject(ProjectAndScheduleVO projectAndScheduleVO) throws IOException {
        ReturnMsg msg = new ReturnMsg();
        if(projectAndScheduleVO==null)throw  new ServiceException("参数不能为空");
        if(projectAndScheduleVO.getUpdateProject().getUpProgress()==100){
            Date date =new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            System.out.println("当前时间"+simpleDateFormat.format(date));
            projectAndScheduleVO.getProject().setPendTime(simpleDateFormat.format(date));
            int i1 = projectMapper.updateById(projectAndScheduleVO.getProject());
        }
        int i = updateProjectMapper.insert(projectAndScheduleVO.getUpdateProject());
        if(i == 0) throw  new ServiceException("项目更新失败");
        WebSocketServer.sendInfo(
                JSONObject.fromObject(
                        projectAndScheduleVO).toString(),"0");
        msg.setStatus("200");
        msg.setMsg(i);
        msg.setStatusMsg("项目跟更新成功");
        return msg;
    }

    @Override
    @Transactional
    public Map<String, Object> selectProjectByid(Integer pid) {
        Map<String,Object> map = new HashMap<>();
        if(pid == null) throw new ServiceException("参数不能为空");
        //查询获取项目信息
        Project project = projectMapper.selectById(pid);
        if(project == null) throw  new ServiceException("项目表中pid参数无效");
        //查询项目进度
        QueryWrapper qw =new QueryWrapper();
           qw.eq("pid",pid);
           qw.orderByDesc("up_id");
           qw.last("limit 1");
        UpdateProject updateProject = updateProjectMapper.selectOne(qw);
        if(updateProject==null){
            UpdateProject updateProject1 = new UpdateProject();
            updateProject1.setUpProgress(0);
            map.put("updateProject",updateProject1);
        }else {
            map.put("updateProject",updateProject);
        }
        map.put("project",project);

        log.info(JSONObject.fromObject(map).toString());
        return map;
    }

    @Override
    @Transactional
    public ReturnMsg selectProjectJournal(Integer uid ,Integer rtype) {
        ReturnMsg msg =new ReturnMsg();
        QueryWrapper qw = new QueryWrapper();
        QueryWrapper qw2=new QueryWrapper();
        List<Journal> list1Journal = new ArrayList<>();
//        List<Project> listProject1 = projectMapper.selectList(qw);
//        if(rtype== Role.EMPLOYEES.getCode()||rtype==Role.FINANCE.getCode()||rtype==Role.ADMINISTRATIVE.getCode()){//职工，人事，财务
//        }
        if(rtype==Role.GM.getCode()||rtype==Role.LJGM.getCode()||rtype==Role.MANAGE.getCode()){//总经理，利捷，经理
            list1Journal= journalMapper.selectList(qw2);
        }else if(rtype==Role.GOVERNOR.getCode()){
            qw2.eq("fuid_Charge",uid);
            list1Journal=journalMapper.selectList(qw2);
        }
        List<UpdateProject> listProject = updateProjectMapper.selectList(qw);

        List list =new ArrayList();
        Iterator project1=listProject.iterator();
        while (project1.hasNext()){
            list.add(project1.next());
        }
        Iterator journal = list1Journal.iterator();
        while (journal.hasNext()){
            list.add(journal.next());
        }
//        Iterator project = listProject1.iterator();
//        while (project.hasNext()){
//            list.add(project.next());
//        }
//        //排序前
//        System.out.println("排序前："+new Gson().toJson(list));
//        //排序后
        ComparatorTime comparator=new ComparatorTime();
        Collections.sort(list, comparator);
//        System.out.println("正序："+new Gson().toJson(list));
        //排序后逆序
        Collections.reverse(list);
//        System.out.println("逆序："+new Gson().toJson(list));
        msg.setStatus("200");
        msg.setMsg(list);
        msg.setStatusMsg("查询所有项目和日志信息成功");
        return msg;
    }



}
