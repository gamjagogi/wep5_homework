package shop.mtcoding.mvcapp2.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Board {
    private int id;
    private String title;
    private String content;

    public Board(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
