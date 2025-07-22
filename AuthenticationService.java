import java.util.Scanner;
public class AuthenticationService{
    public static User login(Scanner scanner){
        System.out.println(" Welcome to Library!");
        System.out.print("Enter Email :");
        String email=scanner.nextLine();
        System.out.print("Enter Password :");
        String password=scanner.nextLine();
        for(User user:Database.users){
            if(user.getEmail().equalsIgnoreCase(email)&&user.getPassword().equals(password)){
                System.out.println("\nLogin successful!");
                return user;
            }
        }
        System.out.print("Invalid email or password");
        return null;
    }
}