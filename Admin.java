public class Admin extends User {
    public Admin(String email, String password, String name) {
        super(email, password, name);
    }

    @Override
    public void showMenu() {
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1. Add Book\n2. Modify Book\n3. Delete Book\n4. View All Books\n5. View Reports\n6. Logout");
    }
}