package bindle_project.Controller;

import java.util.List;

import bindle_project.Dao.UserDao;

public class Listcontroller {
     UserDao dao = new UserDao();

    public List<Book> fetchBooks() {
        return dao.getAllBooks();
    
}
