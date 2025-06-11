package bindle_project.Controller;



import bindle_project.Dao.UserDao;
import java.awt.print.Book;



import java.util.List;

public class Bookcontroller {
    private UserDao userDao;

    public Bookcontroller() {
        userDao = new UserDao();
    }

    public List< Book> search(String keyword) {
        return userDao.searchBooks(keyword);
    }
}
