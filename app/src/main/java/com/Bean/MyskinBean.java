package com.Bean;

import java.util.List;

/**
 * Created by 狄飞 on 2017/4/20.
 */

public class MyskinBean {


    /**
     * skinList : {"datas":[{"skinId":1,"skinName":"1","skinPrice":100,
     * "skinImage":"http://182.254.243.200:8080/NightSleep/files/images/skins/11111","skinMeaning":"1231313"},
     * {"skinId":2,"skinName":"白色情人节","skinPrice":1000,"skinImage":"http://182.254.243.200:8080/NightSleep/files/images/skins/12","skinMeaning":"情人节快乐！"},{"skinId":3,"skinName":" ","skinPrice":0,"skinImage":"http://182.254.243.200:8080/NightSleep/files/images/skins/theme1","skinMeaning":" "}],"pageSize":10,"totalRecord":3,"pageNo":1,"totalPage":1}
     * status : true
     */

    private SkinListBean skinList;
    private boolean status;

    public SkinListBean getSkinList() {
        return skinList;
    }

    public void setSkinList(SkinListBean skinList) {
        this.skinList = skinList;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static class SkinListBean {
        /**
         * datas : [{"skinId":1,"skinName":"1","skinPrice":100,"skinImage":"http://182.254.243.200:8080/NightSleep/files/images/skins/11111","skinMeaning":"1231313"},{"skinId":2,"skinName":"白色情人节","skinPrice":1000,"skinImage":"http://182.254.243.200:8080/NightSleep/files/images/skins/12","skinMeaning":"情人节快乐！"},{"skinId":3,"skinName":" ","skinPrice":0,"skinImage":"http://182.254.243.200:8080/NightSleep/files/images/skins/theme1","skinMeaning":" "}]
         * pageSize : 10
         * totalRecord : 3
         * pageNo : 1
         * totalPage : 1
         */

        private int pageSize;
        private int totalRecord;
        private int pageNo;
        private int totalPage;
        private List<DatasBean> datas;

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getTotalRecord() {
            return totalRecord;
        }

        public void setTotalRecord(int totalRecord) {
            this.totalRecord = totalRecord;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public List<DatasBean> getDatas() {
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean {
            /**
             * skinId : 1
             * skinName : 1
             * skinPrice : 100
             * skinImage : http://182.254.243.200:8080/NightSleep/files/images/skins/11111
             * skinMeaning : 1231313
             */

            private int skinId;
            private String skinName;
            private int skinPrice;
            private String skinImage;
            private String skinMeaning;

            public int getSkinId() {
                return skinId;
            }

            public void setSkinId(int skinId) {
                this.skinId = skinId;
            }

            public String getSkinName() {
                return skinName;
            }

            public void setSkinName(String skinName) {
                this.skinName = skinName;
            }

            public int getSkinPrice() {
                return skinPrice;
            }

            public void setSkinPrice(int skinPrice) {
                this.skinPrice = skinPrice;
            }

            public String getSkinImage() {
                return skinImage;
            }

            public void setSkinImage(String skinImage) {
                this.skinImage = skinImage;
            }

            public String getSkinMeaning() {
                return skinMeaning;
            }

            public void setSkinMeaning(String skinMeaning) {
                this.skinMeaning = skinMeaning;
            }
        }
    }
}
