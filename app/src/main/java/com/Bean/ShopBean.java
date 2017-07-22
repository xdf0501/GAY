package com.Bean;

import java.util.List;

/**
 * Created by 狄飞 on 2017/4/6.
 */

public class ShopBean {

    /**
     * data : [{"skinId":1,"skinName":"1","skinPrice":100,"skinImage":"http://182.254.243.200:8080/NightSleep/files/images/11111.png","skinMeaning":"1231313"},{"skinId":2,"skinName":"白色情人节","skinPrice":1000,"skinImage":"http://182.254.243.200:8080/NightSleep/files/images/11111.png","skinMeaning":"情人节快乐！"}]
     * status : true
     */

    private boolean status;
    private List<DataBean> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * skinId : 1
         * skinName : 1
         * skinPrice : 100
         * skinImage : http://182.254.243.200:8080/NightSleep/files/images/11111.png
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
