package swtech.pageDesignControl.service.impl;

import swtech.pageDesignControl.entity.Project;
import swtech.pageDesignControl.mapper.ProjectMapper;
import swtech.pageDesignControl.service.IProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-11-15
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

}
