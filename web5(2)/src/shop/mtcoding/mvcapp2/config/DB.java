package shop.mtcoding.mvcapp2.config;

import shop.mtcoding.mvcapp2.model.Board;
import shop.mtcoding.mvcapp2.model.User;

import java.util.ArrayList;
import java.util.List;

// Fake DataAccessObject (데이터베이스에 접근하는 클래스)
public class DB {

    private static List<User>userList = new ArrayList<>();

    private static List<Board>boardList = new ArrayList<>();

    static {
        userList.add(new User(1,"gudwns252","123567","kimhyung","man","gusdus@naver.ocm","010-2324-1234",true));

        boardList.add(new Board(1,"chainSawMan","denji"));
    }

    public static List<User>selectAll(){
        return userList;
    }
    public static List<Board>boardSelectAll(){
        return boardList;
    }

    public static void create(String userid, String password, String username, String gender, String email, String phoneNumber, boolean human){
        int id = userList.size()+1;
        userList.add(new User(id,userid,password,username,gender,email,phoneNumber,human));
    }

    public static void create(String title,String content){
        int id = boardList.size()+1;
        boardList.add(new Board(id,title,content));
    }
}
