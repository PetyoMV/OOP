package people;

import cart.Product;
import cart.ProductByCount;
import cart.ProductByKg;
import shop.Shop;

public class Buyer implements IBuyer {

    private String name;
    private Shop shop;
    private double money;
    private int maxProducts;
    private Product[] cart;
    private int freePlaces = 0;


    Buyer(String name, Shop shop, int neededProducts, double money){
        this.maxProducts = neededProducts;
        this.name = name;
        this.shop = shop;
        this.cart = new Product[maxProducts];
        this.money = money;
    }

    @Override
    public void addProductByKg(ProductByKg product, double amount) throws CloneNotSupportedException {
        addProduct(product,amount);
    }


    private void addProduct(Product product, double amount) throws CloneNotSupportedException {
        int idx = shop.existsProduct(product);
        if(idx == -1){
            return;
        }
        else {
            Product productInShop = shop.getProduct(idx);
            if(productInShop.getQuantity() >= amount){
                productInShop.removeQuantity(amount);
            }
            else {
                return;
            }
            int idxInCart = existsProduct(product);
            if(idxInCart == -1){ // vzel sum produkta, no go nqma v kolichkata
                Product productInCart = product.clone();
                productInCart.setQuantity(amount);
                if(freePlaces >= maxProducts){
                    return;
                }
                cart[freePlaces++] = productInCart;
            }
            else {//vzel sum producta i go ima v kolichkata i samo go uvelichavam
                Product productInCart = cart[idxInCart];
                productInCart.addQuantity(amount);
            }
        }
    }

    @Override
    public void removeProductByKg(ProductByKg product, double amount) {
        removeProduct(product,amount);
    }


    private void removeProduct(Product product, double amount) {
        int idx =existsProduct(product);
        if(idx == -1){
            return;
        }
        double quantityToRemove = amount;
        if(cart[idx].getQuantity() < amount){
            quantityToRemove = cart[idx].getQuantity();
            cart[idx] = null;
        }else {
            cart[idx].removeQuantity(quantityToRemove);
        }
        shop.getProduct(shop.existsProduct(product)).addQuantity(quantityToRemove);
    }

    @Override
    public void addProductByCount(ProductByCount product, int count) throws CloneNotSupportedException {
        addProduct(product,count);
    }

    @Override
    public void removeProductByCount(ProductByCount product, int count) {
        removeProduct(product,count);
    }

    @Override
    public void payProducts() {
        double price = 0;
        for (int i = 0; i < cart.length; i++) {
            if(cart[i] != null){
                price += cart[i].getTotalPrice();
            }
        }
        if(price >= money){
            return;
        }
        this.money -= price;
        shop.pay(price);
    }
    /**
     * searches for a product in the array
     * @param x - the product to be found
     * @return -1 if found, or the index of the product if found
     */
    private int existsProduct(Product x){
        for (int i = 0; i < cart.length; i++) {
            if(cart[i] != null && cart[i].getName().equals(x.getName())){
                return i;
            }
        }
        return -1;
    }

}
