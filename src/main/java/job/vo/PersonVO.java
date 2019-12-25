package job.vo;

import job.entity.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户缓存信息
 * </p>
 *
 * @author 李鸿智
 * @since 2019-11-18
 */
@Data
@Accessors(chain = true)
public class PersonVO {

    private static final long serialVersionUID = 1L;

    /**
     * 用户信息
     *
     */
    private User user;

    /**
     * 简历信息
     */
    private PersonUser personUser;

    /**
     * 个人教育信息
     */
    private PersonEducationExperience personEducationExperience;

    /**
     * 个人信息
     */
    private PersonInfo personInfo;

    /**
     * 个人期望工作
     */
    private PersonJobWanted personJobWanted;

    /**
     * 个人工作经验
     */
    private PersonWorkExperience personWorkExperience;
}