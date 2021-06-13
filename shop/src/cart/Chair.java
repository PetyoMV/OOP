package cart;

public class Chair extends ProductByCount{

    public Chair(String name, double price, int quantity) throws InvalidProductException {
        super(name, price, quantity);
    }
}
