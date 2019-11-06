package swtech.pageDesignControl.common.vo;

import java.io.Serializable;

/**
 * 自定义输出格式
 * yjx
 */
public class ReturnMsg<T> implements Serializable {
	private String status;
	private String statusMsg;
	private T msg;

	public ReturnMsg() {
	}

	public ReturnMsg(String status) {
		this.status = status;
	}

	public ReturnMsg(String status, String statusMsg) {
		super();
		this.status = status;
		this.statusMsg = statusMsg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getMsg() {
		return msg;
	}

	public void setMsg(T msg) {
		this.msg = msg;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

}
