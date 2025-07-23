import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class BorrowingService {
    static class BorrowedInfo {
        Book book;
        LocalDate borrowDate;
        int extensions;

        BorrowedInfo(Book book, LocalDate borrowDate) {
            this.book = book;
            this.borrowDate = borrowDate;
            this.extensions = 0;
        }
    }

    static Map<Borrower, List<BorrowedInfo>> borrowedMap = new HashMap<>();
    static Map<Borrower, List<String>> fineHistory = new HashMap<>();

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void borrowBook(Scanner scanner, Borrower borrower) {
        borrowedMap.putIfAbsent(borrower, new ArrayList<>());
        List<BorrowedInfo> userBorrowings = borrowedMap.get(borrower);

        if (userBorrowings.size() >= 3) {
            System.out.println("Max borrow limit (3 books) reached.");
            return;
        }

        System.out.print("Enter Book ISBN or Name: ");
        String input = scanner.nextLine();

        for (Book book : Database.books) {
            if ((book.getIsbn().equalsIgnoreCase(input) || book.getName().equalsIgnoreCase(input)) && book.getQuantity() > 0) {
                for (BorrowedInfo info : userBorrowings) {
                    if (info.book.getIsbn().equals(book.getIsbn())) {
                        System.out.println("You already borrowed this book.");
                        return;
                    }
                }
                if (borrower.getDeposit() < 500) {
                    System.out.println("Insufficient deposit. Minimum Rs.500 required.");
                    return;
                }
                userBorrowings.add(new BorrowedInfo(book, LocalDate.now()));
                book.setQuantity(book.getQuantity() - 1);
                System.out.println("Book borrowed successfully.");
                return;
            }
        }
        System.out.println("Book not found or unavailable.");
    }

    public static void viewBorrowedBooks(Borrower borrower) {
        List<BorrowedInfo> infos = borrowedMap.getOrDefault(borrower, new ArrayList<>());
        if (infos.isEmpty()) {
            System.out.println("No borrowed books.");
            return;
        }
        System.out.println("Your Borrowed Books:");
        for (BorrowedInfo info : infos) {
            System.out.println(info.book + " | Borrowed On: " + info.borrowDate.format(formatter));
        }
    }

    public static void returnBook(Scanner scanner, Borrower borrower) {
        List<BorrowedInfo> infos = borrowedMap.getOrDefault(borrower, new ArrayList<>());
        System.out.print("Enter ISBN to return: ");
        String isbn = scanner.nextLine();

        BorrowedInfo returnInfo = null;
        for (BorrowedInfo info : infos) {
            if (info.book.getIsbn().equals(isbn)) {
                returnInfo = info;
                break;
            }
        }

        if (returnInfo != null) {
            System.out.print("Enter return date (dd/MM/yyyy): ");
            String dateStr = scanner.nextLine();
            LocalDate returnDate = LocalDate.parse(dateStr, formatter);
            long days = ChronoUnit.DAYS.between(returnInfo.borrowDate, returnDate);

            double fine = 0;
            if (days > 15) {
                long overDays = days - 15;
                fine = Math.min(overDays * 2 * Math.pow(1.1, overDays / 10), 0.8 * returnInfo.book.getPrice());
                fine = Math.round(fine);
                borrower.deductFromDeposit(fine);
                fineHistory.computeIfAbsent(borrower, x -> new ArrayList<>())
                    .add("Late Return Fine Rs." + fine + " for book: " + returnInfo.book.getName());
            }
            infos.remove(returnInfo);
            returnInfo.book.setQuantity(returnInfo.book.getQuantity() + 1);
            System.out.println("Book returned successfully.");
            if (fine > 0) System.out.println("Fine collected: Rs." + fine);
        } else {
            System.out.println("Book not in borrowed list.");
        }
    }

    public static void extendTenure(Scanner scanner, Borrower borrower) {
        List<BorrowedInfo> infos = borrowedMap.getOrDefault(borrower, new ArrayList<>());
        System.out.print("Enter ISBN to extend: ");
        String isbn = scanner.nextLine();

        for (BorrowedInfo info : infos) {
            if (info.book.getIsbn().equals(isbn)) {
                if (info.extensions >= 2) {
                    System.out.println("Cannot extend more than 2 times.");
                    return;
                }
                info.borrowDate = LocalDate.now();
                info.extensions++;
                System.out.println("Extension granted. New borrow date: " + info.borrowDate.format(formatter));
                return;
            }
        }
        System.out.println("Book not found in your borrowed list.");
    }

    public static void markBookLost(Scanner scanner, Borrower borrower) {
        List<BorrowedInfo> infos = borrowedMap.getOrDefault(borrower, new ArrayList<>());
        System.out.print("Enter ISBN to mark as lost: ");
        String isbn = scanner.nextLine();

        Iterator<BorrowedInfo> iterator = infos.iterator();
        while (iterator.hasNext()) {
            BorrowedInfo info = iterator.next();
            if (info.book.getIsbn().equals(isbn)) {
                double fine = 0.5 * info.book.getPrice();
                borrower.deductFromDeposit(fine);
                fineHistory.computeIfAbsent(borrower, x -> new ArrayList<>())
                    .add("Lost Book Fine Rs." + fine + " for book: " + info.book.getName());
                iterator.remove();
                System.out.println("Book marked as lost. Fine Rs." + fine + " deducted.");
                return;
            }
        }
        System.out.println("Book not found in your borrowed list.");
    }

    public static void reportCardLost(Borrower borrower) {
        borrower.deductFromDeposit(10);
        fineHistory.computeIfAbsent(borrower, x -> new ArrayList<>())
            .add("Card Lost Fine: Rs.10");
        System.out.println("Card lost reported. Rs.10 deducted.");
    }

    public static void viewFineHistory(Borrower borrower) {
        List<String> fines = fineHistory.getOrDefault(borrower, new ArrayList<>());
        if (fines.isEmpty()) {
            System.out.println("No fines yet.");
        } else {
            System.out.println("--- Fine History ---");
            fines.forEach(System.out::println);
        }
    }

    public static void generateAdminReports(Scanner scanner) {
        System.out.println("\n--- Admin Reports ---");
        System.out.println("1. Low-stock Books (<3)");
        System.out.println("2. Books never borrowed");
        System.out.println("3. Heavily borrowed Books");
        System.out.println("4. Students with pending returns");
        System.out.println("5. Book status by ISBN");
        System.out.print("Select report: ");
        int opt = Integer.parseInt(scanner.nextLine());

        switch (opt) {
            case 1 -> Database.books.stream().filter(b -> b.getQuantity() < 3).forEach(System.out::println);
            case 2 -> Database.books.stream()
                .filter(book -> borrowedMap.values().stream().flatMap(List::stream)
                        .noneMatch(info -> info.book.equals(book)))
                .forEach(System.out::println);
            case 3 -> {
                Map<String, Long> countMap = new HashMap<>();
                borrowedMap.values().stream()
                    .flatMap(List::stream)
                    .forEach(info -> countMap.put(info.book.getIsbn(), countMap.getOrDefault(info.book.getIsbn(), 0L) + 1));
                countMap.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                        .limit(5)
                        .forEach(e -> System.out.println(e.getKey() + " borrowed " + e.getValue() + " times"));
            }
            case 4 -> borrowedMap.entrySet().stream()
                .filter(entry -> !entry.getValue().isEmpty())
                .forEach(entry -> System.out.println(entry.getKey().getName() + ": " + entry.getValue().size() + " book(s)"));
            case 5 -> {
                System.out.print("Enter ISBN: ");
                String isbn = scanner.nextLine();
                for (Map.Entry<Borrower, List<BorrowedInfo>> entry : borrowedMap.entrySet()) {
                    for (BorrowedInfo info : entry.getValue()) {
                        if (info.book.getIsbn().equals(isbn)) {
                            System.out.println("Borrowed by: " + entry.getKey().getName() + " | Expected return: " + info.borrowDate.plusDays(15));
                        }
                    }
                }
            }
            default -> System.out.println("Invalid option.");
        }
    }
}
