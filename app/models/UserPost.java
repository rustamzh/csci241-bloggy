package models;

public class UserPost {
	
	private Post post;
	private String name;
	private String nickname;
	private String avatar;
	
	public UserPost(Post post, String name, String nickname, String avatar) {
		this.post = post;
		this.name = name;
		this.nickname = nickname;
		this.avatar = avatar;
	}
	
	public Post getPost() {
		return post;
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
}
