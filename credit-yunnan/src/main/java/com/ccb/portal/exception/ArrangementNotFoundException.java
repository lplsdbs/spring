package com.ccb.portal.exception;

import com.ccb.portal.vo.ErrorEnum;

public class ArrangementNotFoundException extends RuntimeException {

	private ErrorEnum error;
	
	public ArrangementNotFoundException(ErrorEnum error){
		super(error.getMsg());
		this.setError(error);
	}

	public ArrangementNotFoundException(ErrorEnum error, String msg){
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
