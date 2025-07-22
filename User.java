public class User{
    protected String name;
    protected String email;
    protected String password;
    protected String role;
    public User(String name, String email,String password,String role){
        this.name=name;
        this.email=email;
        this.password=password;
        this.role=role;
    }
     public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }
}