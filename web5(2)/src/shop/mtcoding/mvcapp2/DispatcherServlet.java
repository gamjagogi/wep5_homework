package shop.mtcoding.mvcapp2;


import shop.mtcoding.mvcapp2.controller.BoardController;
import shop.mtcoding.mvcapp2.model.BoardRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * GET -> ex)http://localhost:10000/comment/mainPage.do
 * GET -> http://localhost:10000/board/mainPage.do
 * GET -> http://localhost:10000/board/admin.do
 * POST -> http://localhost:10000/board/save.do
 */

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 버퍼로 들어오는값을 전부 UTP8로 파싱
        req.setCharacterEncoding("utf-8");
        // 2. path를 파싱
        String path = getPath(req);
        System.out.println("path: "+path);

        // 3. action 파싱
        String action = getAction(req);
        System.out.println("action:" +action); //board/list.do do를 없애버림

        // 4. 컨트롤러 객체 생성(싱글톤)
        BoardController boardCon = new BoardController(new BoardRepository());

        // 5. 라우팅 하기
        if(path.equals("board")){
            switch (action){
                case "login":
                    try {
                        String userId = req.getParameter("userId");
                        String userPw = req.getParameter("userPw");
                        System.out.println(userId+","+userPw);
                        String loginView = boardCon.login(userId, userPw);
                        req.getRequestDispatcher(loginView).forward(req,resp);
                    }catch(Exception e) {
                        req.setAttribute("errorMessage",e.getMessage());
                        String mainPageView = boardCon.mainPage(req);
                        req.getRequestDispatcher(mainPageView).forward(req,resp);
                        //resp.sendRedirect("/board/mainPage.do");
                    }
                    break;
                case "admin": //회원가입 form줘!
                    String adminView = boardCon.admin();
                    req.getRequestDispatcher(adminView).forward(req,resp);
                    break;
                case "save": //POST 요청 mainPage칸 채우기 (content, title)
                    String method = req.getMethod();
                    if(!method.equals("POST")){
                        resp.setContentType("text/html; charset=utf-8");
                        resp.getWriter().println("POST로 요청해야 합니다.");
                        break;
                    }
                    String title = req.getParameter("title");
                    String content = req.getParameter("content");
                    String saveRedirect = boardCon.save(title,content); //디스패처로 받아서 컨트롤러에 넘긴다.
                    resp.sendRedirect(saveRedirect);
                    break;
                case "mainPage":
                    // MVC에 위임하는 코드 정확히는 Controller(서블릿)에 위임
                    String mainPageView = boardCon.mainPage(req);
                    req.getRequestDispatcher(mainPageView).forward(req,resp);
                    //뷰 파일에 직접 가져와서 클라이언트에 넘긴다.
                    break;
                default:
                    resp.sendRedirect("/board/mainPage.do");
                    //case mainPage로 가라!! 라고 클라이언트에 전달. mainPage가 디폴트임.
            }
        }
    }

    private String getAction(HttpServletRequest req){
        String action = getUri(req).split("/")[1];
        action = action.replace(".do","");
        return action;
    }
    private String getPath(HttpServletRequest req){
        // board/list.doS
        String path = getUri(req).split("/")[0]; //board만 가져옴
        return path;
    }

    //http://localhost:10000/board/list.do
    private String getUri(HttpServletRequest req){
        String uri = req.getRequestURI();// /board/list.do
        uri = uri.substring(1); // board/list.do앞에 슬레쉬사라짐
        return uri;
    }


}
