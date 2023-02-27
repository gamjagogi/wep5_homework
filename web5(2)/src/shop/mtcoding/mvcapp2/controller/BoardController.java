package shop.mtcoding.mvcapp2.controller;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import shop.mtcoding.mvcapp2.config.ViewResolver;
import shop.mtcoding.mvcapp2.model.Board;
import shop.mtcoding.mvcapp2.model.BoardRepository;
import shop.mtcoding.mvcapp2.model.User;
import shop.mtcoding.mvcapp2.model.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


// 컨트롤러는 제일 앞단 프론트컨트롤러(라우팅)의 부하직원같은 개념이다.
// 책임 : 클라이언트 요청 잘(유효성검사)받고, 응답(View, Data)

public class BoardController {
    //model에 위임한거다

    private HttpServletRequest request;
    private static BoardRepository boardRepository;

    private UserRepository userRepository;


    //생성과 동시에, 주입된걸 받는 코드 즉, "초기화"
    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public String mainPage(HttpServletRequest request) {
        // C - M - V
        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList", boardList);
        return ViewResolver.resolve("/board/mainPage");
    }

    public String admin() {
        // C - V
        return ViewResolver.resolve("/board/admin");
    }

    //스프링은 컨트롤러에 매개변수를 적기만 하면 formUrlEncoded 데이터를 디스패처 서블릿으로부터 전달받음.
    //나중에는 알아서 url을 알아서 파싱해서, title, content값을 가져와준다.

    public String save(String title, String content) {
        // 검증코드 : http method 4가지 중에!!
        // post와 put은 resource를 클라이언트로 부터 전달 받으니까!!!
        // get과 delete는 왜 유효성검사를 하지않는가. body가 없기때문이다
        if (title == null || title.equals("")) {
            //return "/serverError.do"; 특정 에러페이지로 유도할수있다.
            throw new NullPointerException("title이 없습니다.");
        }
        if (content == null || content.equals("")) {
            throw new NullPointerException("content이 없습니다.");
        }
        boardRepository.save(title, content);
        return "/board/mainPage.do";
    }

    public String login(String userId, String userPw) throws Exception {
//        if (userRepository.findAll().stream().filter(s -> s.getUserid().equals(userId)&&s.getPassword().equals(userPw)) != null) {
//            return ViewResolver.resolve("/board/list");
//        }else {
//            throw new Exception("아이디,패스워드를 찾을 수 없습니다.");
//        }
        List<User>list = userRepository.findAll();
        for(User u:list){
            if(u.getUserid().equals(userId) && u.getPassword().equals(userPw)){
                return ViewResolver.resolve("/board/list");
            }else {
                throw new NullPointerException("아이디,패스워드 없어요.");
            }
        }
        return "/board/mainPage.do";
    }
}