package swtech.pageDesignControl.common.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;
import swtech.pageDesignControl.entity.Project;
import swtech.pageDesignControl.entity.UpdateProject;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class ProjectAndScheduleVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Project project;
    private UpdateProject updateProject ;

}
