import java.util.Scanner;

public class AuthenticationService {
    public static User login(Scanner scanner) {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (Admin admin : Database.admins) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                System.out.println("\nWelcome Admin: " + admin.getName());
                return admin;
            }
        }

        for (Borrower borrower : Database.borrowers) {
            if (borrower.getEmail().equals(email) && borrower.getPassword().equals(password)) {
                System.out.println("\nWelcome Borrower: " + borrower.getName());
                return borrower;
            }
        }

        System.out.println("Invalid credentials.\n");
        return null;
    }

    // ✅ New method for Module A: Register a borrower
    public static void registerBorrower(Scanner scanner) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Borrower newBorrower = new Borrower(email, password, name);
        Database.borrowers.add(newBorrower);
        System.out.println("Borrower registered successfully.");
    }
}

