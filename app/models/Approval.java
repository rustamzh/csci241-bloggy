package models;


public class Approval {
    private int approvalId;
    private int commentId;
    private String title;
    private String user_nickname;
    private String comment_body;

    public Approval(){}

    public Approval(int approvalId, int commentId){
        this.approvalId=approvalId;
        this.commentId=commentId;
    }

    public Approval(int approvalId, int commentId, String title, String user_nickname, String comment_body){
        this.title=title;
        this.user_nickname=user_nickname;
        this.comment_body=comment_body;
        this.approvalId=approvalId;
        this.commentId=commentId;
    }
    public int getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(int approvalId) {
        this.approvalId = approvalId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public String getComment_body() {
        return comment_body;
    }

    public void setComment_body(String comment_body) {
        this.comment_body = comment_body;
    }
}
