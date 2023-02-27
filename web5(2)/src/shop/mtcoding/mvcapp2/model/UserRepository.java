package shop.mtcoding.mvcapp2.model;

import shop.mtcoding.mvcapp2.config.DB;

import java.util.List;

public class UserRepository {



    public List<User>findAll(){
        return DB.selectAll();
    }
    public void save(String userid, String password, String username, String gender, String email, String phoneNumber, boolean human){
        DB.create(userid,password,username,gender,email,phoneNumber,human);
    }
}
