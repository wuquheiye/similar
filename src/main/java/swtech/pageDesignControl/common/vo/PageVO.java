package swtech.pageDesignControl.common.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import swtech.pageDesignControl.entity.Journal;

import java.io.Serializable;

@Data
public class PageVO<T> implements Serializable {
    private Journal journal;
    private Page<T> page;
}
