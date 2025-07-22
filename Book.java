public class Book {
    private String isbn;
    private String title;
    private String author;
    private double cost;
    private int quantity;

    public Book(String isbn, String title, String author, double cost, int quantity) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.cost = cost;
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public double getCost() {
        return cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int qty) {
        this.quantity = qty;
    }

    public void display() {
        System.out.println("ISBN: " + isbn + ", Title: " + title + ", Author: " + author + ", Cost: ₹" + cost + ", Available: " + quantity);
    }
}
