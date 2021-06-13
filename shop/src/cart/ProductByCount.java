package cart;

public abstract class ProductByCount extends Product {

    private int quantity;

    public ProductByCount(String name, double price, int quantity) throws InvalidProductException {
        super(name, price);
        if (quantity < 0){
            throw new InvalidProductException(getClass().getName(),name,price);
        }
        this.quantity = quantity;
    }

    @Override
    public double getQuantity() {
        return quantity;
    }

    @Override
    public void addQuantity(double quantity) {
        if(quantity > 0) {
            this.quantity += quantity;
        }
    }

    @Override
    public void setQuantity(double quantity) {
        if(quantity > 0){
            this.quantity = (int)(quantity);
        }
    }

    @Override
    public void removeQuantity(double quantity) {
        if(quantity > 0 && quantity <= this.quantity){
            this.quantity -= quantity;
        }
    }
}
