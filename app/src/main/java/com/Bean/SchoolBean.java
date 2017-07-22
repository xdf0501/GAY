package com.Bean;

import java.util.List;

/**
 * Created by 狄飞 on 2017/4/6.
 */

public class SchoolBean {

    /**
     * lists : {"datas":[{"schoolId":1,"schoolName":"浙江工商大学",
     * "schoolLon":"120.12","schoolLat":"30.28","schoolInfo":"全球某工商"},
     * {"schoolId":2,"schoolName":"浙江大学","schoolLon":"120.08","schoolLat":"30.30","schoolInfo":"全国前几的课"}],"pageSize":10,"totalRecord":2,"pageNo":0,"totalPage":0}
     * status : true
     */

    private ListsBean lists;
    private boolean status;

    public ListsBean getLists() {
        return lists;
    }

    public void setLists(ListsBean lists) {
        this.lists = lists;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static class ListsBean {
        /**
         * datas : [{"schoolId":1,"schoolName":"浙江工商大学","schoolLon":"120.12","schoolLat":"30.28","schoolInfo":"全球某工商"},{"schoolId":2,"schoolName":"浙江大学","schoolLon":"120.08","schoolLat":"30.30","schoolInfo":"全国前几的课"}]
         * pageSize : 10
         * totalRecord : 2
         * pageNo : 0
         * totalPage : 0
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
             * schoolId : 1
             * schoolName : 浙江工商大学
             * schoolLon : 120.12
             * schoolLat : 30.28
             * schoolInfo : 全球某工商
             */

            private int schoolId;
            private String schoolName;
            private String schoolLon;
            private String schoolLat;
            private String schoolInfo;

            public int getSchoolId() {
                return schoolId;
            }

            public void setSchoolId(int schoolId) {
                this.schoolId = schoolId;
            }

            public String getSchoolName() {
                return schoolName;
            }

            public void setSchoolName(String schoolName) {
                this.schoolName = schoolName;
            }

            public String getSchoolLon() {
                return schoolLon;
            }

            public void setSchoolLon(String schoolLon) {
                this.schoolLon = schoolLon;
            }

            public String getSchoolLat() {
                return schoolLat;
            }

            public void setSchoolLat(String schoolLat) {
                this.schoolLat = schoolLat;
            }

            public String getSchoolInfo() {
                return schoolInfo;
            }

            public void setSchoolInfo(String schoolInfo) {
                this.schoolInfo = schoolInfo;
            }
        }
    }
}
