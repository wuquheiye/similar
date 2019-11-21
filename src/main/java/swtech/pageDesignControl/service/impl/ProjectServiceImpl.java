package swtech.pageDesignControl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;
import swtech.pageDesignControl.common.exception.ServiceException;
import swtech.pageDesignControl.common.vo.ProjectAndScheduleVO;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.common.websocket.WebSocketServer;
import swtech.pageDesignControl.common.websocket.WebsocketServerTwo;
import swtech.pageDesignControl.entity.Project;
import swtech.pageDesignControl.entity.UpdateProject;
import swtech.pageDesignControl.mapper.ProjectMapper;
import swtech.pageDesignControl.mapper.UpdateProjectMapper;
import swtech.pageDesignControl.service.IProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
        }
        int i1 = projectMapper.updateById(projectAndScheduleVO.getProject());
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


}
