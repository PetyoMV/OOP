package cart;

public class Beer extends ProductByCount {

    public Beer(String name, double price, int quantity) throws InvalidProductException {
        super(name, price, quantity);
    }
}
