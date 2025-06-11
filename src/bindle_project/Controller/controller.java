/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;
import bindle_project.Dao.UserDao;
import java.awt.print.Book;

import java.util.List;
/**
 *
 * @author ushadhakal
 */
public class controller {
    


public class BookController {
    private UserDao userdao;

    public BookController() {
        userdao = new UserDao();
    }

    public List<Book> search(String keyword) {
        return userdao.search(keyword);
    }
}

}

