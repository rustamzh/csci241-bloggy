package models;

/**
 * Created by ruszh on 06-Nov-16.
 */
public class Comment {
    private int commentId;
    private String date;
    private String body;
    private int post_postId;
    private String user_nickname;

    public Comment(){}

    public Comment(String date, String body, int post_postId, String user_nickname){
        this.body=body;
        this.date=date;
        this.post_postId=post_postId;
        this.user_nickname=user_nickname;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getPost_postId() {
        return post_postId;
    }

    public void setPost_postId(int post_postId) {
        this.post_postId = post_postId;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }
}
