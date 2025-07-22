import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class InventoryManager {

    public static void addBook(Scanner sc) {
        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        System.out.print("Enter Cost: ");
        double cost = Double.parseDouble(sc.nextLine());
        System.out.print("Enter Quantity: ");
        int qty = Integer.parseInt(sc.nextLine());

        Book book = new Book(isbn, title, author, cost, qty);
        Database.books.add(book);
        System.out.println("Book added successfully!");
    }

    public static void modifyBook(Scanner sc) {
        System.out.print("Enter ISBN to Modify: ");
        String isbn = sc.nextLine();
        for (Book b : Database.books) {
            if (b.getIsbn().equalsIgnoreCase(isbn)) {
                System.out.print("Enter new Quantity: ");
                int newQty = Integer.parseInt(sc.nextLine());
                b.setQuantity(newQty);
                System.out.println("Book quantity updated.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public static void deleteBook(Scanner sc) {
        System.out.print("Enter ISBN to Delete: ");
        String isbn = sc.nextLine();
        Database.books.removeIf(book -> book.getIsbn().equalsIgnoreCase(isbn));
        System.out.println("Book deleted if it existed.");
    }

    public static void viewBooksSorted(Scanner sc) {
        System.out.println("Sort by: 1. Title  2. Quantity");
        int choice = Integer.parseInt(sc.nextLine());

        if (choice == 1) {
            Database.books.sort(Comparator.comparing(Book::getTitle));
        } else if (choice == 2) {
            Database.books.sort(Comparator.comparing(Book::getQuantity));
        }

        for (Book b : Database.books) {
            b.display();
        }
    }

    public static void searchBook(Scanner sc) {
        System.out.print("Enter book title or ISBN to search: ");
        String keyword = sc.nextLine().toLowerCase();
        for (Book b : Database.books) {
            if (b.getTitle().toLowerCase().contains(keyword) || b.getIsbn().toLowerCase().equals(keyword)) {
                b.display();
                return;
            }
        }
        System.out.println("Book not found.");
    }
}
