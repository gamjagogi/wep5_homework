package shop.mtcoding.mvcapp2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private int id;
    private String userid;
    private String password;
    private String username;
    private String birth;
    private String gender;
    private String email;
    private String phoneNumber;
    private boolean human;

    public User(int id,String userid, String password, String username, String gender, String email, String phoneNumber, boolean human) {
        this.setId(id);
        this.userid = userid;
        this.password = password;
        this.username = username;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.human = human;
    }

    public User(String year,String month, String day) {
        this.birth = year+month+day;
    }
}
