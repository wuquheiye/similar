package swtech.pageDesignControl.service.impl;

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
        projectAndScheduleVO.getUpdateProject()
                            .setPid(projectAndScheduleVO.getProject().getPid());
        int insert1 = updateProjectMapper.insert(projectAndScheduleVO.getUpdateProject());
        if(insert1 == 0) throw  new ServiceException("updateProject表插入失败");
        WebSocketServer.sendInfo(
                JSONObject.fromObject(
                        projectAndScheduleVO).toString(),"2");
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
        int i1 = projectMapper.updateById(projectAndScheduleVO.getProject());
        int i = updateProjectMapper.updateById(projectAndScheduleVO.getUpdateProject());
        if(i == 0) throw  new ServiceException("项目更新失败");

        WebSocketServer.sendInfo(
                JSONObject.fromObject(
                        projectAndScheduleVO).toString(),"2");
        msg.setStatus("200");
        msg.setMsg(i);
        msg.setStatusMsg("项目跟更新成功");
        return msg;
    }


}
