package com.Bean;

import java.util.List;

/**
 * Created by 狄飞 on 2017/4/7.
 */

public class ThemelistBean {

    /**
     * themeInvitationList : [{"id":1,"type":1,"lon":120.715203,"lat":30.763963,"theme":1,"hot":100,
     * "image":"http://182.254.243.200:8080/NightSleep/files/images/skins/11111.png"},
     * {"id":3,"type":1,"lon":120.7132,"lat":30.7639768,"theme":1,"hot":23,
     * "image":"http://182.254.243.200:8080/NightSleep/files/images/skins/11111.png"},
     * {"id":4,"type":1,"lon":120.713203,"lat":30.7639567,"theme":1,"hot":10,"image":"http://182.254.243.200:8080/NightSleep/files/images/skins/11111.png"},
     * {"id":7,"type":1,"lon":120.7155467,"lat":30.7633575,"theme":1,"hot":0,"image":"http://182.254.243.200:8080/NightSleep/files/images/skins/12.png"},
     * {"id":37,"type":1,"lon":120.396825,"lat":30.316363,"theme":1,"hot":0,"image":"http://182.254.243.200:8080/NightSleep/files/images/skins/11111.png"}]
     * status : true
     */

    private boolean status;
    private List<ThemeInvitationListBean> themeInvitationList;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ThemeInvitationListBean> getThemeInvitationList() {
        return themeInvitationList;
    }

    public void setThemeInvitationList(List<ThemeInvitationListBean> themeInvitationList) {
        this.themeInvitationList = themeInvitationList;
    }

    public static class ThemeInvitationListBean {
        /**
         * id : 1
         * type : 1
         * lon : 120.715203
         * lat : 30.763963
         * theme : 1
         * hot : 100
         * image : http://182.254.243.200:8080/NightSleep/files/images/skins/11111.png
         */

        private int id;
        private int type;
        private double lon;
        private double lat;
        private int theme;
        private int hot;
        private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public int getTheme() {
            return theme;
        }

        public void setTheme(int theme) {
            this.theme = theme;
        }

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
