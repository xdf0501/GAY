package com.Bean;

import java.util.List;

/**
 * Created by 狄飞 on 2017/4/6.
 */

public class MytieziBean {


    /**
     * status : true
     * IfCollect : true
     * userInvitation : {"lon":120.397672,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1491550077901.png"],"nickname":"用户4","collectionNum":0,"content":"","userid":17,"empiricValue":0,"praiseNum":0,"createtime":1491550077000,"id":39,"hot":0,"visitNum":0,"topic":"","userimg":"http://182.254.243.200:8080/NightSleep/files/images/users/17.png","imagesName":"1491550077901|","lat":30.316188,"type":1,"level":0}
     */

    private boolean status;
    private boolean IfCollect;
    private UserInvitationBean userInvitation;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isIfCollect() {
        return IfCollect;
    }

    public void setIfCollect(boolean IfCollect) {
        this.IfCollect = IfCollect;
    }

    public UserInvitationBean getUserInvitation() {
        return userInvitation;
    }

    public void setUserInvitation(UserInvitationBean userInvitation) {
        this.userInvitation = userInvitation;
    }

    public static class UserInvitationBean {
        /**
         * lon : 120.397672
         * images : ["http://182.254.243.200:8080/NightSleep/files/images/invitations/1491550077901.png"]
         * nickname : 用户4
         * collectionNum : 0
         * content :
         * userid : 17
         * empiricValue : 0
         * praiseNum : 0
         * createtime : 1491550077000
         * id : 39
         * hot : 0
         * visitNum : 0
         * topic :
         * userimg : http://182.254.243.200:8080/NightSleep/files/images/users/17.png
         * imagesName : 1491550077901|
         * lat : 30.316188
         * type : 1
         * level : 0
         */

        private double lon;
        private String nickname;
        private int collectionNum;
        private String content;
        private int userid;
        private int empiricValue;
        private int praiseNum;
        private long createtime;
        private int id;
        private int hot;
        private int visitNum;
        private String topic;
        private String userimg;
        private String imagesName;
        private double lat;
        private int type;
        private int level;
        private List<String> images;

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getCollectionNum() {
            return collectionNum;
        }

        public void setCollectionNum(int collectionNum) {
            this.collectionNum = collectionNum;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public int getEmpiricValue() {
            return empiricValue;
        }

        public void setEmpiricValue(int empiricValue) {
            this.empiricValue = empiricValue;
        }

        public int getPraiseNum() {
            return praiseNum;
        }

        public void setPraiseNum(int praiseNum) {
            this.praiseNum = praiseNum;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }

        public int getVisitNum() {
            return visitNum;
        }

        public void setVisitNum(int visitNum) {
            this.visitNum = visitNum;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getUserimg() {
            return userimg;
        }

        public void setUserimg(String userimg) {
            this.userimg = userimg;
        }

        public String getImagesName() {
            return imagesName;
        }

        public void setImagesName(String imagesName) {
            this.imagesName = imagesName;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
