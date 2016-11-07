package models;


public class Approval {
    private int approvalId;
    private int commentId;

    public Approval(){}

    public Approval(int approvalId, int commentId){
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
}
