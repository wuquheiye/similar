package swtech.pageDesignControl.entity;

import com.baomidou.mybatisplus.annotation.IdType;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 袁君选
 * @since 2019-11-18
 */
@Data
@Accessors(chain = true)
public class Journal  {

    private static final long serialVersionUID = 1L;

    /**
     * 日志表主键
     */
    @TableId(value = "jid", type = IdType.AUTO)
    private Integer jid;

    /**
     * 绑定用户id
     */
    private Integer uid;

    /**
     * 今日任务
     */
    private String jnowTask;

    /**
     * 明日任务
     */
    private String jtomorrowTask;

    /**
     * 未完成任务
     */
    private String jundoneTask;

    /**
     * 项目所属
     */
    private Integer pid;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime jcreateTime;


}
