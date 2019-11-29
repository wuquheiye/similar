package swtech.pageDesignControl.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 自定义输出格式
 * yjx
 */
@Data
public class ReturnMsg<T> implements Serializable {
	private String status;
	private String statusMsg;
	private T msg;

}
