package cart;

public class Book extends ProductByCount {

    public Book(String name, double price, int quantity) throws InvalidProductException {
        super(name, price, quantity);
    }
}
