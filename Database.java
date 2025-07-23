import java.util.*;
//import java.util.ArrayList;
//import java.util.List;

public class Database {
    public static List<Admin> admins = new ArrayList<>();
    public static List<Borrower> borrowers = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();

     static {
        admins.add(new Admin("admin@lib.com", "admin123", "Librarian"));
        borrowers.add(new Borrower("user@lib.com", "user123", "John Doe"));
        books.add(new Book("ISBN001", "Java Basics", "James", 5, 300));
        books.add(new Book("ISBN002", "C Programming", "Dennis", 3, 250));
        books.add(new Book("ISBN003", "Python Guide", "Guido", 7, 400));
    }
}
