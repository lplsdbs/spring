/**
 * 
 */
package com.ccb.portal.util;

import com.ccb.portal.entity.Page;
import org.springframework.stereotype.Repository;

/**
 * 消息返回类
 * 
 * @author Administrator
 * 
 */
@Repository
public class Tidings<T> {
	private String status;
	private String msg;
//	private String token;
	private T t;
	private Page page;

	public Page getPage() {
		return page;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getT() {
		return t;
	}

	public Tidings<T> setT(T t) {
		this.t = t;
		return this;
	}


	public void setPage(Page page) {
		this.page = page;
	}



	@Override
	public String toString() {
		return "Tidings{" +
				"status='" + status + '\'' +
				", msg='" + msg + '\'' +
				'}';
	}

//	public String getToken() {
//		return token;
//	}
//
//	public void setToken(String token) {
//		this.token = token;
//	}
}
