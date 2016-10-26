package models;


public class User {
    private String Nickname;
    private String Name;
    private String Password;
    private String Type;
    private String Email;
    private String Avatar;

    public User(){}
    public User(String nickname, String password, String type, String email){
        this.Nickname=nickname;
        this.Password=password;
        this.Type=type;
        this.Email=email;

    }
    public User(String nickname, String name, String password, String type, String email, String Avatar){
        this.Nickname=nickname;
        this.Name=name;
        this.Password=password;
        this.Type=type;
        this.Email=email;
        this.Avatar=Avatar;

    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }
}
