package models;

/**
 * Created by ruszh on 06-Nov-16.
 */
public class Like {
    private String user_nickname;
    private int post_postId;

    public Like(){}

    public Like( int post_postId, String user_nickname){
        this.user_nickname=user_nickname;
        this.post_postId=post_postId;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
    }

    public int getPost_postId() {
        return post_postId;
    }

    public void setPost_postId(int post_postId) {
        this.post_postId = post_postId;
    }
}
