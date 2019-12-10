package com.example.mybatisplus.utils.rst;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * @param <T> 泛型
 * @author Lkx
 * @version V2.1 * Update Logs: * Name: lkx Date: 2017年10月13日 下午1:24:39
 * Description: 初始化
 * @ClassName: Result
 * @Description: 返回结果封装bean
 * @date 2017年10月13日 下午1:24:39
 */
@SuppressWarnings("serial")
public class Result<T> implements Serializable {
    /**
     * 处理成功返回状态码
     */
    public static final int CODE_SUCCESS = 0;
    /**
     * 接口调用失败返回状态码
     */
    public static final int CODE_FAIL = -1;

    /**
     * 状态码
     */
    private int code;
    /**
     * 返回结果
     */
    private T object;
    /**
     * 异常描述
     */
    private String error;
    /**
     * 返回描述
     */
    private String message;
    /**
     * 异常
     */
    private Exception exception;

    /**
     * @param <T> 泛型
     * @return <T> Result<T> 泛型
     * @throws
     * @Title: newSuccess
     * @Description: 接口调用成功，不需要返回对象
     * @author Lkx
     */
    public static <T> Result<T> newSuccess() {
        Result<T> result = new Result<>();
        result.setCode(CODE_SUCCESS);
        return result;
    }

    /**
     * @param object 结果数据
     * @param <T>    泛型
     * @return <T> Result<T> 返回结果对象
     * @throws
     * @Title: newSuccess
     * @Description: 接口调用成功，有返回对象
     * @author Lkx
     */
    public static <T> Result<T> newSuccess(T object) {
        Result<T> result = new Result<>();
        result.setCode(CODE_SUCCESS);
        result.setObject(object);
        return result;
    }

    /**
     * @param code    状态码
     * @param message 描述
     * @param <T>     泛型
     * @return <T> Result<T> 返回结果对象
     * @throws
     * @Title: newFailure
     * @Description: 接口调用失败，有错误码和描述，没有返回对象
     * @author Lkx
     */
    public static <T> Result<T> newFailure(int code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code != CODE_SUCCESS ? code : CODE_FAIL);
        result.setMessage(message);
        return result;
    }

    /**
     * @param code    状态码
     * @param message 描述
     * @param <T>     泛型
     * @return <T> Result<T> 返回结果对象
     * @throws
     * @Title: notFoundBizNo
     * @Description:没有查询到流水账号
     * @author lilisong
     */
    public static <T> Result<T> notFoundBizNo(int code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * @param error   异常描述
     * @param message 返回描述
     * @param <T>     泛型
     * @return <T> Result<T>
     * @throws
     * @Title: newFailure
     * @Description: 接口调用失败，有错误字符串码和描述，没有返回对象
     * @author Lkx
     */
    public static <T> Result<T> newFailure(String error, String message) {
        Result<T> result = new Result<T>();
        result.setCode(CODE_FAIL);
        result.setError(error);
        result.setMessage(message);
        return result;
    }

    /**
     * @param failure 返回结果
     * @param <T>     泛型
     * @return <T> Result<T> 返回对象
     * @throws
     * @Title: newFailure
     * @Description: 转换或复制错误结果
     * @author Lkx
     */
    public static <T> Result<T> newFailure(Result<?> failure) {
        Result<T> result = new Result<>();
        result.setCode(failure.getCode() != CODE_SUCCESS ? failure.getCode()
                : CODE_FAIL);
        result.setError(failure.getError());
        result.setMessage(failure.getMessage());
        result.setException(failure.getException());
        return result;
    }

    /**
     * @param e   异常
     * @param <T> 泛型
     * @return <T> Result<T>
     * @throws
     * @Title: newException
     * @Description: 接口调用失败，返回异常信息
     * @author Lkx
     */
    public static <T> Result<T> newException(Exception e) {
        Result<T> result = new Result<>();
        result.setCode(CODE_FAIL);
        result.setException(e);
        result.setMessage(e.getMessage());
        return result;
    }

    /**
     * @param e     异常
     * @param error 异常描述
     * @param <T>   泛型
     * @return <T> Result<T>
     * @throws
     * @Title: newException
     * @Description: 接口调用失败，返回异常信息
     * @author Lkx
     */
    public static <T> Result<T> newException(String error, Exception e) {
        Result<T> result = new Result<T>();
        result.setCode(CODE_FAIL);
        result.setException(e);
        result.setError(error);
        result.setMessage(e.getMessage());
        return result;
    }

    /**
     * @return boolean
     * @throws
     * @Title: success
     * @Description: 判断返回结果是否成功
     * @author Lkx
     */
    public boolean success() {
        return code == CODE_SUCCESS;
    }

    /**
     * @return boolean
     * @throws
     * @Title: hasObject
     * @Description: 判断返回结果是否有结果对象
     * @author Lkx
     */
    public boolean hasObject() {
        return code == CODE_SUCCESS && object != null;
    }

    /**
     * @return boolean
     * @throws
     * @Title: hasException
     * @Description: 判断返回结果是否有异常
     * @author Lkx
     */
    public boolean hasException() {
        return exception != null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    /**
     * @return String
     * @Description:重写 tostring
     */
    public String toString() {
        StringBuilder result = new StringBuilder("Result");
        if (object != null) {
            result.append("<" + object.getClass().getSimpleName() + ">");
        }
        result.append(": {code=" + code);
        if (object != null) {
            if (object.getClass().isArray()) {
                result.append(", object=" + Arrays.toString((Object[]) object));
            } else {
                result.append(", object=" + object);
            }
        }
        if (error != null) {
            result.append(", error=" + error);
        }
        if (message != null) {
            result.append(", message=" + message);
        }
        if (exception != null) {
            StringWriter stringWriter = new StringWriter();
            exception.printStackTrace(new PrintWriter(stringWriter));
            result.append(", exception=" + stringWriter.toString());
        }
        result.append(" }");
        return result.toString();
    }
}
