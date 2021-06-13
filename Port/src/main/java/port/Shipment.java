package port;

import java.time.LocalDateTime;
import java.util.Objects;

public class Shipment implements Comparable<Shipment> {
    private int packageId;
    private int dockId;
    private int craneId;
    private String shipName;
    private LocalDateTime shipmentTime;

    public Shipment(int packageId, int dockId, int craneId, String shipName, LocalDateTime shipmentTime) {
        this.packageId = packageId;
        this.dockId = dockId;
        this.craneId = craneId;
        this.shipName = shipName;
        this.shipmentTime = shipmentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipment shipment = (Shipment) o;
        return packageId == shipment.packageId && dockId == shipment.dockId && craneId == shipment.craneId && Objects.equals(shipName, shipment.shipName) && Objects.equals(shipmentTime, shipment.shipmentTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(packageId, dockId, craneId, shipName, shipmentTime);
    }

    public int getPackageId() {
        return packageId;
    }

    public int getDockId() {
        return dockId;
    }

    public int getCraneId() {
        return craneId;
    }

    public String getShipName() {
        return shipName;
    }

    public LocalDateTime getShipmentTime() {
        return shipmentTime;
    }

    @Override
    public int compareTo(Shipment o) {
        return this.shipmentTime.compareTo(o.shipmentTime);
    }

    @Override
    public String toString() {
        return "package "+packageId+" ship "+shipName + " crane "+craneId+" date "+shipmentTime;
    }
}
