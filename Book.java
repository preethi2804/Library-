public class Book {
    private String isbn;
    private String name;
    private String author;
    private int quantity;
    private double price;

    public Book(String isbn, String name, String author, int quantity, double price) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
        this.price = price;
    }

    public String getIsbn() { return isbn; }
    public String getName() { return name; }
    public String getAuthor() { return author; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return name + " by " + author + " | ISBN: " + isbn + " | Quantity: " + quantity + " | Price: Rs." + price;
    }
}