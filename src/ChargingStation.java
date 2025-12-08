/**
 * Models an EV charging station, including ID, code, lot, type, and status.
 */


public class ChargingStation {
    private int id;                 // internal numeric id
    private String code;            // user-facing code: G1, L3, D2, etc.
    private ParkingLot lot;
    private String type;            // e.g. "Level 2"
    private ChargingStatus status;

    public ChargingStation(int id, String code,
                           ParkingLot lot, String type,
                           ChargingStatus status) {
        this.id = id;
        this.code = code;
        this.lot = lot;
        this.type = type;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public ParkingLot getLot() {
        return lot;
    }

    public String getType() {
        return type;
    }

    public ChargingStatus getStatus() {
        return status;
    }

    public void setStatus(ChargingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Station " + code +
                " | Lot: " + lot.getName() +
                " | Type: " + type +
                " | Status: " + status.getDescription();
    }
}
