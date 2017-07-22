package com.Bean;

import java.util.List;

/**
 * Created by 狄飞 on 2017/4/27.
 */

public class MyinvilistBean {

    /**
     * status : true
     * InviList : {"totalRecord":8,
     * "datas":[{"lon":120.604417,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1492938559357.png"],
     * "collectionNum":0,"content":"这真是一个1非常好玩的游戏啊\n必须好评1啊","empiricValue":0,"praiseNum":0,
     * "createtime":1492938559000,"id":43,"hot":0,"visitNum":0,"topic":"好评","imagesName":"1492938559357|",
     * "lat":30.083932,"type":1,"level":0},
     * {"lon":120.604229,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1492938335215.png"],
     * "collectionNum":0,"content":"","empiricValue":0,"praiseNum":0,"createtime":1492938335000,
     * "id":42,"hot":0,"visitNum":0,"topic":"据说这是一个有很多人关注的1软件\n",
     * "imagesName":"1492938335215|","lat":30.083798,"type":1,"level":0},{"lon":0,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1492689880905.png"],"collectionNum":0,"content":"大福源","empiricValue":0,"praiseNum":0,"createtime":1492689880000,"id":41,"hot":0,"visitNum":0,"topic":"高鸿股份","imagesName":"1492689880905|","lat":0,"type":1,"level":0},{"lon":120.392649,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1491636547979.png"],"collectionNum":0,"content":"","empiricValue":0,"praiseNum":0,"createtime":1491636548000,"id":40,"hot":0,"visitNum":0,"topic":"","imagesName":"1491636547979|","lat":30.316056,"type":1,"level":0},{"lon":120.397672,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1491550077901.png"],"collectionNum":0,"content":"","empiricValue":0,"praiseNum":0,"createtime":1491550077000,"id":39,"hot":0,"visitNum":0,"topic":"","imagesName":"1491550077901|","lat":30.316188,"type":1,"level":0},{"lon":120.396363,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1491531196181.png"],"collectionNum":0,"content":"","empiricValue":0,"praiseNum":0,"createtime":1491531196000,"id":38,"hot":0,"visitNum":0,"topic":"领学姐的秘密","imagesName":"1491531196181|","lat":30.31351,"type":1,"level":0},{"lon":120.396425,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1491527461971.png"],"collectionNum":0,"content":"","empiricValue":0,"praiseNum":0,"createtime":1491527461000,"id":36,"hot":0,"visitNum":0,"topic":"","imagesName":"1491527461971|","lat":30.313363,"type":1,"level":0},{"lon":120.396444,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1491527306840.png"],"collectionNum":0,"content":"法国人刚刚","empiricValue":0,"praiseNum":0,"createtime":1491527306000,"id":35,"hot":0,"visitNum":0,"topic":"过热头发恶","imagesName":"1491527306840|","lat":30.313403,"type":1,"level":0}],"pageSize":10,"totalPage":1,"pageNo":1}
     */

    private boolean status;
    private InviListBean InviList;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public InviListBean getInviList() {
        return InviList;
    }

    public void setInviList(InviListBean InviList) {
        this.InviList = InviList;
    }

    public static class InviListBean {
        /**
         * totalRecord : 8
         * datas : [{"lon":120.604417,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1492938559357.png"],
         * "collectionNum":0,"content":"这真是一个1非常好玩的游戏啊\n必须好评1啊","empiricValue":0,"praiseNum":0,"createtime":1492938559000,
         * "id":43,"hot":0,"visitNum":0,"topic":"好评","imagesName":"1492938559357|","lat":30.083932,"type":1,"level":0},{"lon":120.604229,
         * "images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1492938335215.png"],"collectionNum":0,"content":"",
         * "empiricValue":0,"praiseNum":0,"createtime":1492938335000,"id":42,"hot":0,"visitNum":0,"topic":"据说这是一个有很多人关注的1软件\n",
         * "imagesName":"1492938335215|","lat":30.083798,"type":1,"level":0},{"lon":0,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1492689880905.png"],"collectionNum":0,"content":"大福源","empiricValue":0,"praiseNum":0,"createtime":1492689880000,"id":41,"hot":0,"visitNum":0,"topic":"高鸿股份","imagesName":"1492689880905|","lat":0,"type":1,"level":0},{"lon":120.392649,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1491636547979.png"],"collectionNum":0,"content":"","empiricValue":0,"praiseNum":0,"createtime":1491636548000,"id":40,"hot":0,"visitNum":0,"topic":"","imagesName":"1491636547979|","lat":30.316056,"type":1,"level":0},{"lon":120.397672,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1491550077901.png"],"collectionNum":0,"content":"","empiricValue":0,"praiseNum":0,"createtime":1491550077000,"id":39,"hot":0,"visitNum":0,"topic":"","imagesName":"1491550077901|","lat":30.316188,"type":1,"level":0},{"lon":120.396363,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1491531196181.png"],"collectionNum":0,"content":"","empiricValue":0,"praiseNum":0,"createtime":1491531196000,"id":38,"hot":0,"visitNum":0,"topic":"领学姐的秘密","imagesName":"1491531196181|","lat":30.31351,"type":1,"level":0},{"lon":120.396425,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1491527461971.png"],"collectionNum":0,"content":"","empiricValue":0,"praiseNum":0,"createtime":1491527461000,"id":36,"hot":0,"visitNum":0,"topic":"","imagesName":"1491527461971|","lat":30.313363,"type":1,"level":0},{"lon":120.396444,"images":["http://182.254.243.200:8080/NightSleep/files/images/invitations/1491527306840.png"],"collectionNum":0,"content":"法国人刚刚","empiricValue":0,"praiseNum":0,"createtime":1491527306000,"id":35,"hot":0,"visitNum":0,"topic":"过热头发恶","imagesName":"1491527306840|","lat":30.313403,"type":1,"level":0}]
         * pageSize : 10
         * totalPage : 1
         * pageNo : 1
         */

        private int totalRecord;
        private int pageSize;
        private int totalPage;
        private int pageNo;
        private List<DatasBean> datas;

        public int getTotalRecord() {
            return totalRecord;
        }

        public void setTotalRecord(int totalRecord) {
            this.totalRecord = totalRecord;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * lon : 120.604417
             * images : ["http://182.254.243.200:8080/NightSleep/files/images/invitations/1492938559357.png"]
             * collectionNum : 0
             * content : 这真是一个1非常好玩的游戏啊
             必须好评1啊
             * empiricValue : 0
             * praiseNum : 0
             * createtime : 1492938559000
             * id : 43
             * hot : 0
             * visitNum : 0
             * topic : 好评
             * imagesName : 1492938559357|
             * lat : 30.083932
             * type : 1
             * level : 0
             */

            private double lon;
            private int collectionNum=0;
            private String content;
            private int empiricValue=0;
            private int praiseNum=0;
            private long createtime;
            private int id=0;
            private int hot=0;
            private int visitNum=0;
            private String topic;
            private String imagesName;
            private double lat;
            private int type=0;
            private int level=0;
            private List<String> images;

            public double getLon() {
                return lon;
            }

            public void setLon(double lon) {
                this.lon = lon;
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
}
