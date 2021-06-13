package shop;

public class ShopFullExeption extends Exception {

    @Override
    public String getMessage() {
        return "Shop is full !";
    }
}
