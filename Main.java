import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user;

        while (true) {
            user = AuthenticationService.login(scanner);
            if (user == null) continue;

            if (user instanceof Admin) {
                Admin admin = (Admin) user;
                int choice;
                do {
                    admin.adminMenu();
                    System.out.print("Choose: ");
                    choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1 -> InventoryManager.addBook(scanner);
                        case 2 -> InventoryManager.modifyBook(scanner);
                        case 3 -> InventoryManager.deleteBook(scanner);
                        case 4 -> InventoryManager.viewBooksSorted(scanner);
                        case 5 -> InventoryManager.searchBook(scanner);
                        case 6 -> System.out.println("Add User: Coming Soon...");
                        case 7 -> System.out.println("Manage Fine: Coming Soon...");
                        case 8 -> System.out.println("Reports: Coming Soon...");
                        case 9 -> System.out.println("Logged out.");
                        default -> System.out.println("Invalid option.");
                    }
                } while (choice != 9);

            } else if (user instanceof Borrower) {
                Borrower borrower = (Borrower) user;
                int choice;
                do {
                    borrower.borrowerMenu();
                    System.out.print("Choose: ");
                    choice = Integer.parseInt(scanner.nextLine());
                    switch (choice) {
                        case 1 -> InventoryManager.viewBooksSorted(scanner);
                        case 2 -> System.out.println("Borrow Book: Coming Soon...");
                        case 3 -> System.out.println("Return Book: Coming Soon...");
                        case 4 -> System.out.println("Extend: Coming Soon...");
                        case 5 -> System.out.println("Report Lost: Coming Soon...");
                        case 6 -> System.out.println("History: Coming Soon...");
                        case 7 -> System.out.println("Logged out.");
                        default -> System.out.println("Invalid option.");
                    }
                } while (choice != 7);
            }
        }
    }
}
