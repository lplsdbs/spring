package com.ccb.portal.vo;

import org.springframework.util.StringUtils;

public class SecBase {
	private String error;
	private String msg;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getErrMsg(){
		StringBuilder errMsg = new StringBuilder();
		if(StringUtils.isEmpty(this.error)){
			errMsg.append("[").append(this.error).append("] ");
		}
		if(StringUtils.isEmpty(this.msg)){
			errMsg.append(this.msg);
		}
		return errMsg.toString();
	}

}
