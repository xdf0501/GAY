package com.Bean;

/**
 * Created by 狄飞 on 2017/3/19.
 */

public class emailBean {

    /**
     * message : 发送验证码失败
     * status : false
     */

    private String message;
    private boolean status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
