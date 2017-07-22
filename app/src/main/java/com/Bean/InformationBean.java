package com.Bean;

/**
 * Created by 狄飞 on 2017/3/19.
 */

public class InformationBean {

    /**
     * brief : 1
     * birthday : 2017-03-04
     * followers : 0
     * money : 120
     * sex : 1
     * nickname : 天王盖地虎
     * empiric : 200
     * location : 1
     * username : 110@qq.com
     * concern : 0
     * status : true
     */

    private String brief;
    private String birthday;
    private int followers=1;
    private int money=1;
    private int sex;
    private String nickname;
    private int empiric;
    private String location;
    private String username;
    private int concern;
    private boolean status;

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getEmpiric() {
        return empiric;
    }

    public void setEmpiric(int empiric) {
        this.empiric = empiric;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getConcern() {
        return concern;
    }

    public void setConcern(int concern) {
        this.concern = concern;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
