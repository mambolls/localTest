package com.example.mybatisplus.utils.rst;

import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 客户接口处理结果
 *
 * @param <T> 返回数据类型
 * @author liuxing
 * @version 2018-07-05
 */
public class CustomerResult<T> implements Serializable {

    /**
     * 处理成功返回状态码
     */
    public static final String CODE_SUCCESS = "0";
    /**
     * 接口调用失败返回状态码
     */
    public static final String CODE_FAIL = "-1";
    /**
     * 状态信息
     */
    public static final String MSG = "ok";

    /**
     * 返回状态正常为:0，其他情况为异常
     */
    private String ret;

    /**
     * 异常情况说明
     */
    private String msg;

    /**
     * 数据集合
     */
    private List<T> dataLists;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getDataLists() {
        return dataLists;
    }

    public void setDataLists(List<T> dataLists) {
        this.dataLists = dataLists;
    }


    /**
     * @param <T> 泛型
     * @return <T> Result<T> 泛型
     * @throws
     * @Title: newSuccess
     * @Description: 接口调用成功，不需要返回对象
     * @author Lkx
     */
    public static <T> CustomerResult<T> newSuccess() {
        CustomerResult<T> result = new CustomerResult<>();
        result.setRet(CODE_SUCCESS);
        result.setMsg(MSG);
        return result;
    }


    /**
     * @param dataLists 结果数据
     * @param <T>       泛型
     * @return <T> Result<T> 返回结果对象
     * @throws
     * @Title: newSuccess
     * @Description: 接口调用成功，有返回对象
     * @author Lkx
     */
    public static <T> CustomerResult<T> newSuccess(List<T> dataLists) {
        CustomerResult<T> result = new CustomerResult<>();
        result.setRet(CODE_SUCCESS);
        result.setMsg(MSG);
        result.setDataLists(dataLists);
        return result;
    }

    /**
     * @param ret 状态码
     * @param msg 描述
     * @param <T> 泛型
     * @return <T> Result<T> 返回结果对象
     * @throws
     * @Title: newFailure
     * @Description: 接口调用失败，有错误码和描述，没有返回对象
     * @author Lkx
     */
    public static <T> CustomerResult<T> newFailure(String ret, String msg) {
        CustomerResult<T> result = new CustomerResult<>();
        result.setRet(ret != CODE_SUCCESS ? ret : CODE_FAIL);
        result.setMsg(msg);
        return result;
    }

    /**
     * @return com.example.mybatisplus.utils.rst.CustomerResult<T>
     * @MethodName newFailure
     * @Author lilinsong
     * @Description TODO
     * @Param [msg]
     * @Date 2019/12/9 14:39
     **/
    public static <T> CustomerResult<T> newFailure(String msg) {
        CustomerResult<T> result = new CustomerResult<>();
        result.setRet(CODE_FAIL);
        result.setMsg(msg);
        return result;
    }


    /**
     * @return boolean
     * @throws
     * @Title: isSuccess
     * @Description: 判断返回结果是否成功
     * @author lilinsong
     */
    public boolean isSuccess() {
        return ret == CODE_SUCCESS;
    }


    /**
     * @return boolean
     * @throws
     * @Title: hasData
     * @Description: 判断返回结果是否有结果对象
     * @author lilinsong
     */
    public boolean hasData() {
        return ret == CODE_SUCCESS && !CollectionUtils.isEmpty(dataLists);
    }


    public String toString() {
        StringBuilder result = new StringBuilder("Result");
        if (dataLists != null) {
            result.append("<" + dataLists.getClass().getSimpleName() + ">");
        }
        result.append(": {ret=" + ret);
        if (dataLists != null) {
            result.append(", dataLists=" + dataLists);
        }
        if (msg != null) {
            result.append(", msg=" + msg);
        }
        result.append(" }");
        return result.toString();
    }

}
