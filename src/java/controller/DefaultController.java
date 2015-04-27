package controller;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.Book;
import model.Calc;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @RequestMapping("/")
    public String printIndex(ModelMap model) {
        return "index";
    }

    @RequestMapping("/index")
    public String printIndexPage(ModelMap model) {
        return "index";
    }


    @RequestMapping(value = "/viewAll", method = RequestMethod.GET)
    public String viewAllBooks(ModelMap model) {
        //Map<String, Object> model = new HashMap<String, Object>();
         ArrayList<Book> booksList = new ArrayList();
        ResultSet resultSet=null;

        try {
           
            Class.forName("com.mysql.jdbc.Driver");
            

            try (Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/spring?zeroDateTimeBehavior=convertToNull", "root", "")) {
                
                int id;
                String bookTitle, bookAuthor, bookIsbn;
                Book temp;
                 String query = "select * from books ";
                PreparedStatement prepStatement = con.prepareStatement(query);
                resultSet = prepStatement.executeQuery();
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                    bookTitle = resultSet.getString("title");
                    bookAuthor = resultSet.getString("author");
                    bookIsbn = resultSet.getString("isbn");
                   
                    temp = new Book(id, bookTitle, bookAuthor, bookIsbn);
                    booksList.add(temp);
                }
                

            } catch (SQLException ex) {
                Logger.getLogger(DefaultController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DefaultController.class.getName()).log(Level.SEVERE, null, ex);
        }
        StringBuilder builder= new StringBuilder();
        builder.append("<table class='table'>");
        builder.append("<th>Title</th> <th>Author</th><th>ISBN</th>");
        for(Book b: booksList){
            
            builder.append("<tr><td>"+b.getTitle() +"</td><td>"+b.getAuthor() +"</td><td>"+ b.getIsbn()+"</td></tr> ");    
        }
        
        builder.append("</table>");
        
      
         model.addAttribute("table", builder);
		return "viewAll";
        
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ModelAndView aboutPage() {
        System.out.println("loading calc form");
        return new ModelAndView("about", "command", new Calc());

    }

    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
    public ModelAndView addBook() {
        
        return new ModelAndView("addBook", "command", new Book());

    }

    @RequestMapping(value = "/saveBook", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute("SpringWeb") Book book,
            ModelMap model) throws SQLException {
        model.addAttribute("book_title", book.getTitle());
        model.addAttribute("book_author", book.getAuthor());
        model.addAttribute("book_isbn", book.getIsbn());
        ArrayList<Book> booksList = new ArrayList();
        ResultSet resultSet=null;

        try {
            String query = "INSERT INTO books (title, author, isbn ) VALUES(?,?,?)";
            Class.forName("com.mysql.jdbc.Driver");
            

            try (Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/spring?zeroDateTimeBehavior=convertToNull", "root", "")) {
                PreparedStatement prepStatement = con.prepareStatement(query);
                prepStatement.setString(1, book.getTitle());
                prepStatement.setString(2, book.getAuthor());
                prepStatement.setString(3, book.getIsbn());
                prepStatement.executeUpdate();

                int id;
                String bookTitle, bookAuthor, bookIsbn;
                Book temp;
                 query = "select * from books ";
                prepStatement = con.prepareStatement(query);
                resultSet = prepStatement.executeQuery();
                while (resultSet.next()) {
                    id = resultSet.getInt("id");
                    bookTitle = resultSet.getString("title");
                    bookAuthor = resultSet.getString("author");
                    bookIsbn = resultSet.getString("isbn");
                   
                    temp = new Book(id, bookTitle, bookAuthor, bookIsbn);
                    booksList.add(temp);
                }
                

            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DefaultController.class.getName()).log(Level.SEVERE, null, ex);
        }

      
        model.addAttribute("allBooks", booksList);
        return "redirect:/viewAll";
    }

}
