import java.util.Scanner;

public class InventoryManager {
    public static void addBook(Scanner scanner) {
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter Book Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine();
        System.out.print("Enter Quantity: ");
        int qty = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Database.books.add(new Book(isbn, name, author, qty, price));
        System.out.println("Book added successfully!");
    }

    public static void modifyBook(Scanner scanner) {
        System.out.print("Enter ISBN of Book to Modify: ");
        String isbn = scanner.nextLine();
        for (Book book : Database.books) {
            if (book.getIsbn().equals(isbn)) {
                System.out.print("Enter New Quantity: ");
                int newQty = Integer.parseInt(scanner.nextLine());
                book.setQuantity(newQty);
                System.out.println("Book quantity updated.");
                return;
            }
        }
        System.out.println("Book not found.");
    }
    

    public static void deleteBook(Scanner scanner) {
        System.out.print("Enter ISBN of Book to Delete: ");
        String isbn = scanner.nextLine();
        Book toRemove = null;
        for (Book book : Database.books) {
            if (book.getIsbn().equals(isbn)) {
                toRemove = book;
                break;
            }
        }
        if (toRemove != null) {
            Database.books.remove(toRemove);
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    public static void listBooks() {
        if (Database.books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\n--- Book Inventory ---");
        for (Book book : Database.books) {
            System.out.println(book);
        }
    }
}