package com.ccb.portal.util;

import com.ccb.portal.vo.Result;

/**
 * 返回结果的封装
 * @author zhuronghua.zh
 *
 */
public class ResultUtil {
	private static final String sucCode = "OK";
	
	public static Result success(Object object) {
        Result result = new Result();
        result.setCode(sucCode);
        result.setData(object);
        result.setMsg(sucCode);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
