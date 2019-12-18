package job.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 自定义输出格式分页
 * yjx
 */
@Data
public class ReturnMsgPage<T> implements Serializable {
	private String status;
	private String statusMsg;
	private T msg;
	private int currentPage;
	private int totalPage;
	private int pageSize;
    private int pageStart;
    private int pageEnd;
    private int totalSize;
}
