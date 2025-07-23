import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user;

        while (true) {
            user = AuthenticationService.login(scanner);
            if (user == null) continue;

            boolean stayLoggedIn = true;

            if (user instanceof Admin admin) {
                while (stayLoggedIn) {
                    System.out.println("\n--- Admin Menu ---");
                    System.out.println("1. Add Book");
                    System.out.println("2. View Inventory");
                    System.out.println("3. Remove Book");
                    System.out.println("4. Register Borrower");
                    System.out.println("5. Generate Reports");
                    System.out.println("6. Logout");
                    System.out.print("Choose: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1 -> InventoryManager.addBook(scanner);
                        case 2 -> InventoryManager.listBooks();
                        case 3 -> InventoryManager.deleteBook(scanner);
                        case 4 -> AuthenticationService.registerBorrower(scanner);
                        case 5 -> BorrowingService.generateAdminReports(scanner);
                        case 6 -> stayLoggedIn = false;
                        default -> System.out.println("Invalid choice");
                    }
                }
            } else if (user instanceof Borrower borrower) {
                while (stayLoggedIn) {
                    System.out.println("\n--- Borrower Menu ---");
                    System.out.println("1. View Available Books");
                    System.out.println("2. Borrow Book");
                    System.out.println("3. View Borrowed Books");
                    System.out.println("4. Return Book");
                    System.out.println("5. Extend Tenure");
                    System.out.println("6. Mark Book as Lost");
                    System.out.println("7. Report Card Lost");
                    System.out.println("8. View Fine History");
                    System.out.println("9. Logout");
                    System.out.print("Choose: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1 -> InventoryManager.listBooks();
                        case 2 -> BorrowingService.borrowBook(scanner, borrower);
                        case 3 -> BorrowingService.viewBorrowedBooks(borrower);
                        case 4 -> BorrowingService.returnBook(scanner, borrower);
                        case 5 -> BorrowingService.extendTenure(scanner, borrower);
                        case 6 -> BorrowingService.markBookLost(scanner, borrower);
                        case 7 -> BorrowingService.reportCardLost(borrower);
                        case 8 -> BorrowingService.viewFineHistory(borrower);
                        case 9 -> stayLoggedIn = false;
                        default -> System.out.println("Invalid choice");
                    }
                }
            }
        }
    }
}
