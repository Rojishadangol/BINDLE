package bindle_project.Controller;



import bindle_project.Dao.UserDao;
import bindle_project.Model.Search;



import java.util.List;

public class Bookcontroller {
    private UserDao userDao;

    public Bookcontroller() {
        userDao = new UserDao();
    }

    public List<Search> search(String keyword) {
        return userDao.searchbook(keyword);
    }
}
