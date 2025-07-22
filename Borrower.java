import java.util.ArrayList;
import java.util.List;

public class Borrower extends User {
    private double depositBalance;
    private List<String> borrowedBooks;
    private List<String> fineHistory;

    public Borrower(String name, String email, String password) {
        super(name, email, password, "Borrower");
        this.depositBalance = 1500.0;
        this.borrowedBooks = new ArrayList<>();
        this.fineHistory = new ArrayList<>();
    }

    public double getDepositBalance() {
        return depositBalance;
    }

    public void deductDeposit(double amount) {
        this.depositBalance -= amount;
    }

    public void addFine(String reason) {
        fineHistory.add(reason);
    }

    public List<String> getFineHistory() {
        return fineHistory;
    }

    public void borrowerMenu() {
        System.out.println("\n--- Borrower Menu ---");
        System.out.println("1. View Available Books");
        System.out.println("2. Borrow Book");
        System.out.println("3. Return Book");
        System.out.println("4. Extend Book Tenure");
        System.out.println("5. Report Lost Book");
        System.out.println("6. View Borrow History & Fines");
        System.out.println("7. Logout");
    }
}
