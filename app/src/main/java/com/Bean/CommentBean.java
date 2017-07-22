package com.Bean;

import java.util.List;

/**
 * Created by 狄飞 on 2017/4/21.
 */

public class CommentBean {

    /**
     * commentList : {"datas":[{"commentId":2,"commentInvitationId":2,"commentUserId":1,
     * "topic":"浙江大学主题帖1","content":"gggg","userNickName":"用户1",
     * "userimg":"http://182.254.243.200:8080/NightSleep/files/images/users/1.png",
     * "commentContent":"好烦呐","commentPraiseNum":10,"commentReplyNum":0,"commentCreateTime":1490283962000}],"pageSize":10,"totalRecord":1,"pageNo":1,"totalPage":1}
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
         * datas : [{"commentId":2,"commentInvitationId":2,"commentUserId":1,"topic":"浙江大学主题帖1","content":"gggg","userNickName":"用户1","userimg":"http://182.254.243.200:8080/NightSleep/files/images/users/1.png","commentContent":"好烦呐","commentPraiseNum":10,"commentReplyNum":0,"commentCreateTime":1490283962000}]
         * pageSize : 10
         * totalRecord : 1
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
             * commentId : 2
             * commentInvitationId : 2
             * commentUserId : 1
             * topic : 浙江大学主题帖1
             * content : gggg
             * userNickName : 用户1
             * userimg : http://182.254.243.200:8080/NightSleep/files/images/users/1.png
             * commentContent : 好烦呐
             * commentPraiseNum : 10
             * commentReplyNum : 0
             * commentCreateTime : 1490283962000
             */

            private int commentId;
            private int commentInvitationId;
            private int commentUserId;
            private String topic;
            private String content;
            private String userNickName;
            private String userimg;
            private String commentContent;
            private int commentPraiseNum;
            private int commentReplyNum;
            private long commentCreateTime;

            public int getCommentId() {
                return commentId;
            }

            public void setCommentId(int commentId) {
                this.commentId = commentId;
            }

            public int getCommentInvitationId() {
                return commentInvitationId;
            }

            public void setCommentInvitationId(int commentInvitationId) {
                this.commentInvitationId = commentInvitationId;
            }

            public int getCommentUserId() {
                return commentUserId;
            }

            public void setCommentUserId(int commentUserId) {
                this.commentUserId = commentUserId;
            }

            public String getTopic() {
                return topic;
            }

            public void setTopic(String topic) {
                this.topic = topic;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUserNickName() {
                return userNickName;
            }

            public void setUserNickName(String userNickName) {
                this.userNickName = userNickName;
            }

            public String getUserimg() {
                return userimg;
            }

            public void setUserimg(String userimg) {
                this.userimg = userimg;
            }

            public String getCommentContent() {
                return commentContent;
            }

            public void setCommentContent(String commentContent) {
                this.commentContent = commentContent;
            }

            public int getCommentPraiseNum() {
                return commentPraiseNum;
            }

            public void setCommentPraiseNum(int commentPraiseNum) {
                this.commentPraiseNum = commentPraiseNum;
            }

            public int getCommentReplyNum() {
                return commentReplyNum;
            }

            public void setCommentReplyNum(int commentReplyNum) {
                this.commentReplyNum = commentReplyNum;
            }

            public long getCommentCreateTime() {
                return commentCreateTime;
            }

            public void setCommentCreateTime(long commentCreateTime) {
                this.commentCreateTime = commentCreateTime;
            }
        }
    }
}
