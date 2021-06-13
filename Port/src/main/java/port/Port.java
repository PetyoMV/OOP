package port;

import util.DBConnector;
import util.Randomizer;
import warehouse.Warehouse;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class Port {

    private String name;
    private List<Dock> docks;
    private Warehouse wh1;
    private Warehouse wh2;
    private Set<Shipment> shipments;

    public Port(String name) {
        this.name = name;
        this.shipments = new HashSet();
        wh1 = new Warehouse(1);
        wh2 = new Warehouse(2);
        this.docks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            docks.add(new Dock(i + 1));
        }
    }

    public synchronized void addShip(Ship ship) {
        docks.get(Randomizer.getRandomNumber(0, docks.size() - 1)).addShip(ship);
        System.out.println(ship.getName() +" added "+ship.getPackages().size()+" packages, all cranes notified");
        notifyAll();
    }

    private boolean isEmpty() {
        for (Dock d : docks) {
            if (!d.isEmpty() && !d.isNowUnloading()) {
                return false;
            }
        }
        return true;
    }


    private Dock getDockToUnload() throws Exception {
        for (Dock d : docks) {
            if (!d.isEmpty() && !d.isNowUnloading()) {
                return d;
            }
        }
        throw new Exception("Trying to get dock for unloading from empty port!");
    }

    public void unload(Crane crane) throws Exception {
        Ship s = null;
        Dock d = null;
        synchronized (this) {
            while (isEmpty()) {
                System.out.println("Cran " + crane.getNumber() + " wait because port is empty");
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("Something reawaken me!");
                }
            }
            d = getDockToUnload();
            d.setNowUnloading(true);
            s = d.getNextShip();
        }
        System.out.println("Crane " + crane.getNumber() + " starts unloading ship " + s.getName());
        for (Package p : s.getPackages()) {
            Thread.sleep(2000);
            getRandomWarehouse().addPackage(p);
            logShipment(new Shipment(p.getId(), d.getId(),crane.getNumber(),s.getName(), LocalDateTime.now()));
        }
        d.removeShip();
        d.setNowUnloading(false);
        System.out.println("Crane " + crane.getNumber() + " finishes unloading ship " + s.getName());
        //TODO add all to DB
    }

    private void logShipment(Shipment shipment) {
        this.shipments.add(shipment);
        addShipmentToDB(shipment);
    }

    private void addShipmentToDB(Shipment shipment) {

        String sql = "INSERT INTO port_shipment (boat_name, dock_id, crane_id, unloading_time, package_id)" +
                "VALUES (?,?,?,?,?)";
        Connection c = DBConnector.getInstance().getConnection();
        try(
             PreparedStatement ps = c.prepareStatement(sql)
        ) {
            ps.setString(1,shipment.getShipName());
            ps.setInt(2,shipment.getDockId());
            ps.setInt(3,shipment.getCraneId());
            ps.setTimestamp(4, Timestamp.valueOf(shipment.getShipmentTime()));
            ps.setInt(5,shipment.getPackageId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Warehouse getRandomWarehouse() {
        if(new Random().nextBoolean()){
            return wh1;
        }
        else {
            return wh2;
        }
    }
}

