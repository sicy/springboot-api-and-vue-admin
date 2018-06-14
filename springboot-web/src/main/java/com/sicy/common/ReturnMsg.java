package com.sicy.common;

/**
 * 扩展的消息返回类
 *
 * @author github.com/sicy
 * @version 1.0
 *          <p>
 *          <br/>
 *          <br/>修订人		修订时间			描述信息
 *          <br/>-----------------------------------------------------
 *          <br/>github.com/sicy		2018/5/9		初始创建
 */
public class ReturnMsg {

    private Long timestamp;
    private int status;
    private boolean success;
    private String message;
    private Object data;

    public ReturnMsg(boolean success){
        this(success, "");
    }

    /**
     * 默认status为0
     * @param success
     * @param message
     */
    public ReturnMsg(boolean success, String message){
        this(success, message, null);
    }

    /**
     * 默认status为0
     * @param success
     * @param message
     * @param data
     */
    public ReturnMsg(boolean success, String message, Object data){
        this(0, success, message, data);
    }

    /**
     * 完整构造方式
     * @param status
     * @param success
     * @param message
     * @param data
     */
    public ReturnMsg(int status, boolean success, String message, Object data) {
        this.timestamp = System.currentTimeMillis();
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
