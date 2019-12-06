package swtech.pageDesignControl.service;

import swtech.pageDesignControl.common.vo.ProjectAndScheduleVO;
import swtech.pageDesignControl.common.vo.ReturnMsg;
import swtech.pageDesignControl.entity.Project;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-18
 */
public interface IProjectService extends IService<Project> {

    ReturnMsg createProject(ProjectAndScheduleVO projectAndScheduleVO) throws IOException;

    ReturnMsg updateProject(ProjectAndScheduleVO projectAndScheduleVO) throws IOException;

    Map<String,Object> selectProjectByid(Integer pid);

    ReturnMsg selectProjectJournal(Integer uid ,Integer rtype);
}
