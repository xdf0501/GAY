package com.Bean;

/**
 * Created by 狄飞 on 2017/4/7.
 */

public class ThemeBean {


    /**
     * themeInvitation : {"lon":120.394013,"images":"1|2|3|","schoolid":1,
     * "collectionNum":0,"content":"你猜你猜2","empiricValue":0,"praiseNum":0,
     *"createtime":1490680659000,"id":4,"hot":10,"visitNum":0,"topic":"浙江工商大学主题帖3","lat":30.315778,"level":0}
     * status : true
     * IfCollect : false
     */

    private ThemeInvitationBean themeInvitation;
    private boolean status;
    private boolean IfCollect;

    public ThemeInvitationBean getThemeInvitation() {
        return themeInvitation;
    }

    public void setThemeInvitation(ThemeInvitationBean themeInvitation) {
        this.themeInvitation = themeInvitation;
    }

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

    public static class ThemeInvitationBean {
        /**
         * lon : 120.394013
         * images : 1|2|3|
         * schoolid : 1
         * collectionNum : 0
         * content : 你猜你猜2
         * empiricValue : 0
         * praiseNum : 0
         * createtime : 1490680659000
         * id : 4
         * hot : 10
         * visitNum : 0
         * topic : 浙江工商大学主题帖3
         * lat : 30.315778
         * level : 0
         */

        private double lon;
        private String images;
        private int schoolid;
        private int collectionNum;
        private String content;
        private int empiricValue;
        private int praiseNum;
        private long createtime;
        private int id;
        private int hot;
        private int visitNum;
        private String topic;
        private double lat;
        private int level;

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getSchoolid() {
            return schoolid;
        }

        public void setSchoolid(int schoolid) {
            this.schoolid = schoolid;
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

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}
