package com.Bean;

/**
 * Created by 狄飞 on 2017/4/8.
 */

public class SignBean {

    /**
     * msg : 你已成功签到3天
     * rewards : +15金币，+150经验
     * status : true
     */

    private String msg;
    private String rewards;
    private boolean status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRewards() {
        return rewards;
    }

    public void setRewards(String rewards) {
        this.rewards = rewards;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
