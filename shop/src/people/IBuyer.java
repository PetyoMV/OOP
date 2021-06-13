package people;

import cart.ProductByCount;
import cart.ProductByKg;
import shop.Shop;

public interface IBuyer {

    public static IBuyer createBuyer(String name, Shop shop, int neededProducts, double money){
        return new Buyer(name,shop,neededProducts,money);
    }

    public void addProductByKg(ProductByKg product, double count) throws CloneNotSupportedException;

    public void removeProductByKg(ProductByKg product,double count);

    public void addProductByCount(ProductByCount product, int count) throws CloneNotSupportedException;

    public void removeProductByCount(ProductByCount product, int count);

    public void payProducts();
}
