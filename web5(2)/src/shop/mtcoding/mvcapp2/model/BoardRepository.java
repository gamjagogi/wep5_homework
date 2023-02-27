package shop.mtcoding.mvcapp2.model;

import shop.mtcoding.mvcapp2.config.DB;

import java.util.List;

//책임 : 데이터베이스 접근
public class BoardRepository {

    public List<Board> findAll(){
        // SELECT * FROM board 실제로는 레포지토리에 쿼리문이 작성된다.
        return DB.boardSelectAll(); //static으로 작성된거라 바로 가져다 쓸수있다.
    }

    // 클라이언트(formUrlEncoded:url파싱) -> 디스패처서블릿(reqest body) -> Controller(title,content) -> Repository(title, content)->db에서 가져옴
    // title, content가 잘 들어왔는지 확인하는것은 controller에서 해야된다. 디스패처서블릿에서 해도된다.레포지토리 전에만 체크
                                                        //이걸 유효성 검사라고 한다.
    public void save(String title, String content){
        // INSERT INTO board(title, content)Values('제목1','내용1') 쿼리로 이렇게 작성.
        DB.create(title,content);
    }
}
