package cart;

public class InvalidProductException extends java.lang.Exception {

        private String productName;
        private String productClassName;
        private double productPrice;

        public InvalidProductException(String productClassName, String productName,double productPrice){
            this.productName = productName;
            this.productClassName = productClassName;
            this.productPrice = productPrice;
        }

        public String getMessage(){
            return "Invalid parameter when trying to create a product of type: " + productClassName + " with name: " + productName+" and price: "+productPrice;
        }

    }

