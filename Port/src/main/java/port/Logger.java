package port;

import util.DBConnector;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class Logger extends Thread{

    private static int stats1Counter = 1;

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            listAllPackagesPerDock();
//            listDistinctShipsForDay();
//            listPackageCountsPerCrane();
//            getFattestShip();
        }
    }

    private void listAllPackagesPerDock() {
        TreeMap<Integer, TreeSet<Shipment>> stats = new TreeMap<>();

        String sql = "SELECT boat_name,dock_id,crane_id,unloading_time,package_id FROM port_shipment";
        Connection c = DBConnector.getInstance().getConnection();
        try (
             PreparedStatement ps = c.prepareStatement(sql)){
            ResultSet rows = ps.executeQuery();
            while (rows.next()){
                Shipment shipment = new Shipment(
                        rows.getInt("package_id"),
                        rows.getInt("dock_id"),
                        rows.getInt("crane_id"),
                        rows.getString("boat_name"),
                        rows.getTimestamp("unloading_time").toLocalDateTime());
                if(!stats.containsKey(shipment.getDockId())){
                    stats.put(shipment.getDockId(),new TreeSet<>());
                }
                stats.get(shipment.getDockId()).add(shipment);
            }
            for (Map.Entry<Integer,TreeSet<Shipment>> e : stats.entrySet()){
                System.out.println("Dock " + e.getKey() + ":");
                for(Shipment s : e.getValue()){
                    System.out.println(s);
                }
            }
            saveToFile(stats);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void saveToFile(TreeMap<Integer, TreeSet<Shipment>> stats) {
        try(PrintWriter pr = new PrintWriter("report-"+stats1Counter++ + "-"+ LocalDateTime.now().toString().replace(":","-")+".txt");) {
            for (Map.Entry<Integer,TreeSet<Shipment>> e : stats.entrySet()){
                pr.println("Dock " + e.getKey() + ":");
                for(Shipment s : e.getValue()){
                    pr.println(s);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
