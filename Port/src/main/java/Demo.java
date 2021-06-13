import port.Crane;
import port.Logger;
import port.Port;
import port.ShipProducer;

public class Demo {

    public static void main(String[] args) throws InterruptedException {

        Port port = new Port("Varna");
        Crane newCrane = new Crane(1);
        Crane oldCrane = new Crane(2);
        Crane.port = port;
        ShipProducer.port = port;



        newCrane.start();
        oldCrane.start();
        Thread.sleep(2000);
        new ShipProducer().start();

        Logger logger = new Logger();
        logger.setDaemon(true);
        logger.start();

    }
}
