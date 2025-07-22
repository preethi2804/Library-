import java.util.ArrayList;
import java.util.List;

public class Database {
    public static List<User> users = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();

    static {
        
        users.add(new Admin("Admin One", "admin1@lib.com", "admin123"));
        users.add(new Borrower("Student One", "student1@lib.com", "stud123"));

        
        books.add(new Book("B001", "Java Programming", "James Gosling", 499.99, 5));
        books.add(new Book("B002", "Data Structures", "Mark Allen", 349.50, 3));
    }
}
