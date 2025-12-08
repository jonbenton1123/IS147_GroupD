/**
 * Repository interface defining how EV charging station data is accessed.
 * Enables the data layer to be swapped (e.g., in-memory or database-backed)
 * without modifying higher-level logic.
 */


import java.util.List;

public interface StationRepository {

    List<ParkingLot> getAllLots();

    List<ChargingStation> getAllStations();

    ChargingStation findByCode(String code);

    void randomizeStatuses();   // simulate live status updates
}
