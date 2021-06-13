package shop;

import people.IBuyer;
import cart.*;

public class Demo {

    public static void main(String[] args) {

        Shop shop = new Shop("Kaufland","Sofia",20000,6);



        try {
            ProductByKg cheddar = new Cheese("Cheddar",12,88.65);
            ProductByKg svinsko = new Meat("Svinsko",22,64.3);
            ProductByKg cipura = new Fish("Cipura",19,14);

            ProductByCount zagorka = new Beer("Zagorka",2.2,200);
            ProductByCount voinaIMir = new Book("Voina i mir",55,10);
            ProductByCount stol = new Chair("Practic chair",90,17);

            shop.addProduct(cheddar);
            shop.addProduct(svinsko);
            shop.addProduct(cipura);
            shop.addProduct(zagorka);
            shop.addProduct(voinaIMir);
            shop.addProduct(stol);

            IBuyer ivan = IBuyer.createBuyer("Ivan",shop,3,2000);
            IBuyer mara = IBuyer.createBuyer("Maria",shop,5,5000);
            IBuyer gogo = IBuyer.createBuyer("Georgie",shop,2,15000);

            shop.showProducts();

            mara.addProductByKg(svinsko,3);
            mara.addProductByKg(cipura,3);
            mara.addProductByKg(cheddar,2);
            gogo.addProductByCount(zagorka,7);
            ivan.addProductByCount(stol,11);
            ivan.removeProductByCount(stol,2);
            mara.removeProductByKg(cheddar,1);

            mara.payProducts();
            ivan.payProducts();
            gogo.payProducts();

            shop.showProducts();
        }
        catch (InvalidProductException e) {
            System.out.println(e.getMessage());
        } catch (ShopFullExeption e) {
            System.out.println(e.getMessage());
        } catch (CloneNotSupportedException e) {
            System.out.println("oops clone failed");
        }


    }
}
