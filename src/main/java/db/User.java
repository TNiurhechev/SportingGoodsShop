package db;

public class User {
    private String nickname;
    private String password;
    private int isAdmin;

    public User(String nickname, String password, int isAdmin){
        this.nickname = nickname;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public int getIsAdmin(){
        return  isAdmin;
    }
}
