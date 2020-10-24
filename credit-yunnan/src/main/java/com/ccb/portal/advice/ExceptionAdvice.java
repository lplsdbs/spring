package com.ccb.portal.advice;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.dao.TypeMismatchDataAccessException;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.ccb.portal.exception.ArrangementNotFoundException;
import com.ccb.portal.exception.ErrorEnumException;
import com.ccb.portal.util.ResultUtil;
import com.ccb.portal.vo.ErrorEnum;
import com.ccb.portal.vo.Result;

/**
 * 统一处理异常，其中ErrorEnum是定义的错误码及a错误信息
 * @author zhuronghua.zh
 *
 */
@RestControllerAdvice
public class ExceptionAdvice {

	/**
	 * 统一处理数据库访问错误
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(DataAccessException.class)
	public Result handleDataAccessException(DataAccessException e) {
		if (e instanceof DataIntegrityViolationException) {
			return ErrorEnum.DB_INTEGRITY_VIOLATION.result();
		}
		if (e instanceof DataAccessResourceFailureException) {
			return ErrorEnum.DB_INVALID_ACCESS.result();
		}
		if (e instanceof BadSqlGrammarException) {
			return ErrorEnum.DB_BAD_SQL.result();
		}
		if (e instanceof TypeMismatchDataAccessException) {
			return ErrorEnum.DB_TYPE_MISMATCH.result();
		}
		if (e instanceof InvalidDataAccessResourceUsageException) {
			return ErrorEnum.DB_INVALID_ACCESS.result();
		}

		return ErrorEnum.DB_UNKNOW_ERROR.result();
	}

	/**
	 * 统一处理运行时错误
	 * @param e
	 * @return
	 */
	@ExceptionHandler(RuntimeException.class)
	public Result handleRuntimeException(RuntimeException e) {
		if (e instanceof HttpMessageConversionException) {
			return ErrorEnum.UNKNOW_ERROR.result();
		}
		if (e instanceof RestClientException) {
			return ErrorEnum.RESTCLIENT_ERROR.result();
		}
		if (e instanceof ErrorEnumException) {
			return ((ErrorEnumException) e).getError().result();
		}

		if (StringUtils.contains(e.getMessage(), "未找到")) {
			return ErrorEnum.ERROR_404.result();
		}
		if (e instanceof HttpMessageNotReadableException) {
			return ErrorEnum.ERROR_PARAM.result();
		}
		return ErrorEnum.UNKNOW_ERROR.result();
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public Result handleNoHandlerFoundException(NoHandlerFoundException e) {
		return ErrorEnum.ERROR_404.result();
	}

	@ExceptionHandler(ArrangementNotFoundException.class)
	public Result handleArrangementNotFoundException(ArrangementNotFoundException e) {
		return ErrorEnum.ARR_NOT_FOUND_ERROR.result();
	}

	@ExceptionHandler(Exception.class)
	public Result handleException(Exception e) {
		System.out.println("============================================");
		if (e instanceof HttpMessageNotReadableException) {
			return ErrorEnum.ERROR_PARAM.result();
		}else{
			String msg = StringUtils.isNotBlank(e.getMessage()) ? e.getMessage() : ErrorEnum.UNKNOW_ERROR.getMsg();
			return ResultUtil.error(ErrorEnum.UNKNOW_ERROR.getCode(), msg);
		}

	}

}
