package models;


import java.util.Date;

public class Post {
    private int postId;
    private String title;
    private int number_of_likes;
    private Date date;
    private String body;
    private String category;
    private User user;

    public Post(){}

    public Post(int postId,int number_of_likes, Date date,String title, String body, String category, User user){
        this.postId=postId;
        this.title=title;
        this.number_of_likes=number_of_likes;
        this.date=date;
        this.body=body;
        this.category=category;
        this.user=user;
    }
    public Post(int number_of_likes, Date date,String title, String body, String category, User user){
        this.number_of_likes=number_of_likes;
        this.date=date;
        this.title=title;
        this.body=body;
        this.category=category;
        this.user=user;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getNumber_of_likes() {
        return number_of_likes;
    }

    public void setNumber_of_likes(int number_of_likes) {
        this.number_of_likes = number_of_likes;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}