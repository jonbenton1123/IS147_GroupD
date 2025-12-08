/**
 * Defines behaviors for determining charger availability across lots.
 */


import java.util.List;

public interface AvailabilityService {

    int countAvailableStations();

    int countAvailableStationsByLot(ParkingLot lot);

    List<ChargingStation> getStationsByLot(ParkingLot lot);

    List<ChargingStation> getAvailableStations();
}
