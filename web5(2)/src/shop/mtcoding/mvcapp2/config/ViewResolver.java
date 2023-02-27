package shop.mtcoding.mvcapp2.config;

public class ViewResolver {
    private static String prefix = "/WEB-INF/views";
    private static String suffix = ".jsp";

    public static String resolve(String viewName){// /board/list
        // /WEB-INF/views/board/list.jsp 가 된다.
        return prefix+viewName+suffix;
    }
}
