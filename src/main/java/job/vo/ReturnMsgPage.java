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
	// 当前第几页
	private int currentPage;
	// 总共多少页
	private int totalPage;
	// 每页的个数
	private int pageSize;
	// 开始个数
    private int pageStart;
    // 结束个数
    private int pageEnd;
    // 总个数
    private int totalSize;
}
