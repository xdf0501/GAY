package com.Bean;

import java.util.List;

/**
 * Created by 狄飞 on 2017/4/27.
 */

public class CommenallBean {

    /**
     * commentList : {"totalRecord":1,
     * "datas":[{"commentUserId":17,"commentContent":"nihao","commentReplyNum":0,
     * "userimg":"http://182.254.243.200:8080/NightSleep/files/images/users/17.png",
     * "userNickName":"灰灰飞","commentId":26,"commentCreateTime":1493296797000,
     * "commentInvitationId":39,"commentPraiseNum":0}],"pageSize":10,"totalPage":1,"pageNo":1}
     * status : true
     */

    private CommentListBean commentList;
    private boolean status;

    public CommentListBean getCommentList() {
        return commentList;
    }

    public void setCommentList(CommentListBean commentList) {
        this.commentList = commentList;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public static class CommentListBean {
        /**
         * totalRecord : 1
         * datas : [{"commentUserId":17,"commentContent":"nihao","commentReplyNum":0,"userimg":"http://182.254.243.200:8080/NightSleep/files/images/users/17.png","userNickName":"灰灰飞","commentId":26,"commentCreateTime":1493296797000,"commentInvitationId":39,"commentPraiseNum":0}]
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
             * commentUserId : 17
             * commentContent : nihao
             * commentReplyNum : 0
             * userimg : http://182.254.243.200:8080/NightSleep/files/images/users/17.png
             * userNickName : 灰灰飞
             * commentId : 26
             * commentCreateTime : 1493296797000
             * commentInvitationId : 39
             * commentPraiseNum : 0
             */

            private int commentUserId;
            private String commentContent;
            private int commentReplyNum;
            private String userimg;
            private String userNickName;
            private int commentId;
            private long commentCreateTime;
            private int commentInvitationId;
            private int commentPraiseNum;

            public int getCommentUserId() {
                return commentUserId;
            }

            public void setCommentUserId(int commentUserId) {
                this.commentUserId = commentUserId;
            }

            public String getCommentContent() {
                return commentContent;
            }

            public void setCommentContent(String commentContent) {
                this.commentContent = commentContent;
            }

            public int getCommentReplyNum() {
                return commentReplyNum;
            }

            public void setCommentReplyNum(int commentReplyNum) {
                this.commentReplyNum = commentReplyNum;
            }

            public String getUserimg() {
                return userimg;
            }

            public void setUserimg(String userimg) {
                this.userimg = userimg;
            }

            public String getUserNickName() {
                return userNickName;
            }

            public void setUserNickName(String userNickName) {
                this.userNickName = userNickName;
            }

            public int getCommentId() {
                return commentId;
            }

            public void setCommentId(int commentId) {
                this.commentId = commentId;
            }

            public long getCommentCreateTime() {
                return commentCreateTime;
            }

            public void setCommentCreateTime(long commentCreateTime) {
                this.commentCreateTime = commentCreateTime;
            }

            public int getCommentInvitationId() {
                return commentInvitationId;
            }

            public void setCommentInvitationId(int commentInvitationId) {
                this.commentInvitationId = commentInvitationId;
            }

            public int getCommentPraiseNum() {
                return commentPraiseNum;
            }

            public void setCommentPraiseNum(int commentPraiseNum) {
                this.commentPraiseNum = commentPraiseNum;
            }
        }
    }
}
