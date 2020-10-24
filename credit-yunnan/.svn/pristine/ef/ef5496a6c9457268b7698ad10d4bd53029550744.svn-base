package com.ccb.portal.util;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ccb.portal.exception.ErrorEnumException;
import com.ccb.portal.vo.ErrorEnum;
import com.github.pagehelper.util.StringUtil;

/**
 * 调用安全接口的工具类
 * @author zhuronghua.zh
 *
 */
public class SecApiUtil {
	private final static Pattern paramPattern = Pattern.compile("\\{($\\w+)\\}");

	/**
	 * 根据路径配置规则，将路径中的参数替换为实际值
	 * @param path
	 * @param id
	 * @return
	 */
	public static String buildPath(String path, Object id) {
		if (path == null)
			throw new ErrorEnumException(ErrorEnum.SECURI_ERROR);
		if (id == null || id instanceof Map) {
			return buildPath(path, (Map) id);
		}
		if (id instanceof Integer) {
			return buildPath(path, (Integer) id);
		}
		if (id instanceof String) {
			return buildPath(path, (String) id);
		}

		throw new ErrorEnumException(ErrorEnum.SECPARAM_ERROR);
	}

	private static String buildPath(String path, Map map) {
		if (path == null)
			throw new ErrorEnumException(ErrorEnum.SECURI_ERROR);
		Matcher matcher = paramPattern.matcher(path);
		String result = path;
		while (matcher.find()) {
			String key = matcher.group(1);
			if (map == null || map.getOrDefault(key, null) == null) {
				throw new ErrorEnumException(ErrorEnum.SECPARAM_ERROR);
			} else {
				result = result.replace(matcher.group(0), String.valueOf(map.get(key)));
			}
		}
		return result;
	}

	private static String buildPath(String path, String id) {
		if (path == null)
			throw new ErrorEnumException(ErrorEnum.SECURI_ERROR);
		if (StringUtil.isEmpty(id)) {
			throw new ErrorEnumException(ErrorEnum.SECPARAM_ERROR);
		}
		return path + "/" + id;
	}

	private static String buildPath(String path, Integer id) {
		if (path == null)
			throw new ErrorEnumException(ErrorEnum.SECURI_ERROR);
		if (id == null) {
			throw new ErrorEnumException(ErrorEnum.SECPARAM_ERROR);
		}
		return path + "/" + id;
	}

}
