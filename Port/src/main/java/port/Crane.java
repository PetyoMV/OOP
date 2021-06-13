package port;

public class Crane extends Thread {

    public static Port port;
    private int num;

    public Crane (int num){
        this.num = num;
    }

    @Override
    public void run() {
        while (true){
            try {
                port.unload(this);
            } catch (Exception e) {
                System.out.println("Oops! - " + e.getMessage());
                return;
            }
        }
    }

    public int getNumber() {
        return num;
    }
}
