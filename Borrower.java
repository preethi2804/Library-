import java.util.ArrayList;
import java.util.List;

public class Borrower extends User {
    private double deposit = 1500;
    private List<Book> borrowedBooks = new ArrayList<>();

    public Borrower(String email, String password, String name) {
        super(email, password, name);
    }

    public double getDeposit() { return deposit; }
    public void deductFromDeposit(double amount) { deposit -= amount; }

    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    @Override
    public void showMenu() {
        System.out.println("\n--- Borrower Menu ---");
        System.out.println("1. View Available Books\n2. Borrow Book\n3. View Borrowed Books\n4. Return Book\n5. Report Card/Book Lost\n6. Logout");
    }
}