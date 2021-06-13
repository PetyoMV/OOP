package cart;

public abstract class Product implements Cloneable{

    private final String name;
    private double price;

    public Product(String name, double price) throws InvalidProductException {
        if(name == null || name.isEmpty() || price <= 0){
            throw new InvalidProductException(getClass().getName(),name,price);
        }
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public abstract double getQuantity();

    public double getTotalPrice(){
        return this.price*getQuantity();
    }

    public abstract void setQuantity(double quantity);

    public abstract void addQuantity(double quantity);

    public abstract void removeQuantity(double quantity);

    @Override
    public Product clone() throws CloneNotSupportedException {
        return (Product) super.clone();
    }
}
