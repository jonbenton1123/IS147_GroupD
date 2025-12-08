/**
 * Interface defining methods for evaluating EV charger availability.
 * Provides filtering and counting logic independent of data storage.
 */

import java.util.List;

public interface AvailabilityService {

    int countAvailableStations();

    int countAvailableStationsByLot(ParkingLot lot);

    List<ChargingStation> getStationsByLot(ParkingLot lot);

    List<ChargingStation> getAvailableStations();
}
