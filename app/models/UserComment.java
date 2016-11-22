package models;

public class UserComment {
    	
	private String name;
	private String nickname;
	private String avatar;
	private Comment comment;
	
	public UserComment(String name, String nickname, String avatar, Comment comment) {
		this.name = name;
		this.nickname = nickname;
		this.avatar = avatar;
		this.comment = comment;
	}
	
	public String getName() {
		return name;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public Comment getComment() {
		return comment;
	}
}
