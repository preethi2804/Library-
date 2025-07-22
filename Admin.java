public class Admin extends User {

    public Admin(String name, String email, String password) {
        super(name, email, password, "Admin");
    }

    public void adminMenu() {
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1. Add Book");
        System.out.println("2. Modify Book");
        System.out.println("3. Delete Book");
        System.out.println("4. View All Books (Sorted)");
        System.out.println("5. Search Book");
        System.out.println("6. Add User (Admin/Borrower)");
        System.out.println("7. Manage Borrower Fine Limit");
        System.out.println("8. View Reports");
        System.out.println("9. Logout");
    }
}
