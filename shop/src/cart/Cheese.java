package cart;

public class Cheese extends ProductByKg{

    public Cheese(String name, double price, double quantity) throws InvalidProductException {
        super(name, price, quantity);
    }
}
