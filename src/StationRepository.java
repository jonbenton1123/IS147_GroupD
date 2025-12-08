/**
 * Repository interface defining how charging station and lot data is accessed.
 * Enables future replacement with a database implementation.
 */


import java.util.List;

public interface StationRepository {

    List<ParkingLot> getAllLots();

    List<ChargingStation> getAllStations();

    ChargingStation findByCode(String code);

    void randomizeStatuses();   // simulate live status updates
}
