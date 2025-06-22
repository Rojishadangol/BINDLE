/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;
import bindle.project.dao.userdao;
import model.Book;

import java.util.List;
/**
 *
 * @author ushadhakal
 */
public class controller {
    


public class BookController {
    private userdao userdao;

    public BookController() {
        userdao = new userdao();
    }

    public List<Book> search(String keyword) {
        return userdao.searchBooks(keyword);
    }
}

}

