package com.ccb.portal.exception;

import com.ccb.portal.vo.ErrorEnum;

/**
 * 把ErrorEnum专门定义一个Exception
 * @author zhuronghua.zh
 *
 */
public class ErrorEnumException extends RuntimeException {

	private ErrorEnum error;
	
	public ErrorEnumException(ErrorEnum error){
		super(error.getMsg());
		this.setError(error);
	}

	public ErrorEnumException(ErrorEnum error, String msg){
		super(msg);
		this.setError(error);
	}

	public ErrorEnum getError() {
		return error;
	}

	public void setError(ErrorEnum error) {
		this.error = error;
	}
}
