package cart;

public class Meat extends ProductByKg{

    public Meat(String name, double price, double quantity) throws InvalidProductException {
        super(name, price, quantity);
    }
}
