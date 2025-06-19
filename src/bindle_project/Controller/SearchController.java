/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bindle_project.Controller;

import bindle_project.Dao.SearchDao;
import bindle_project.Model.Search;
import bindle_project.View.HomeScreen;
import java.awt.print.Book;
import java.util.List;

/**
 *
 * @author acer
 */
public class SearchController {
    private HomeScreen view;
    private SearchDao searchDao;

    public SearchController(HomeScreen view) {
        this.view = view;
        this.searchDao = new SearchDao();
    }

    public void performSearch(String keyword) {
        Search search = new Search(keyword);
        List<Book> results = searchDao.searchBooks(search.getKeyword());
        view.displaySearchResults(results); // Assume HomeScreen has this method
    }
}
