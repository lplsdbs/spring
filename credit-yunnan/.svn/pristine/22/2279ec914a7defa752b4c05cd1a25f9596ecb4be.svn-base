package com.ccb.portal.vo;

import com.ccb.portal.util.ResultUtil;

public enum ErrorEnum {
	SUCCESS("OK",null),
	SYSTEM_ERROR("X001", "系统错误，请重试!"),
	

	RESTCLIENT_ERROR("X101", "安全接口调用错误！"),
	SECAPI_ERROR("X102", "安全接口调用错误！"),
	SECURI_ERROR("X103", "系统参数错误"),
	
	SECPARAM_ERROR("X104", "用户参数错误"),
	
	DB_TYPE_MISMATCH("X801", "数据操作失败"),//java类型与db类型不匹配
	DB_BAD_SQL("X802", "数据操作失败"),//SQL语句有问题
	DB_INVALID_ACCESS("X803", "数据操作失败"),//数据资源访问出错，如SQL语句有问题
	DB_ACCESS_FAILURE("X804", "数据操作失败"),//数据资源访问失败，如数据库连接出错
	DB_INTEGRITY_VIOLATION("X805", "插入或修改的数据与已有数据冲突"),//数据完整性冲突
	DB_UNKNOW_ERROR("X899", "未知错误"),
	
	UNKNOW_ERROR("X999", "未知错误"), 
	
	ERROR_404("404", "访问资源不存在"),
	ERROR_PARAM("p401","参数为空/为json格式"),
	USER_NOT_FOUND("Y001", "用户不存在"),
	PARAM_ERROR("Y002", "输入参数不合法"),

	ARR_NOT_FOUND_ERROR("ARR01", "没有找到合约信息"),
;

	private String code;

	private String msg;

	ErrorEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	public Result result(){
		return ResultUtil.error(this.code, this.msg);
	}

}
