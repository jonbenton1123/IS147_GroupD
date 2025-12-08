/**
 * Simple implementation of AvailabilityService using in-memory lists.
 */


import java.util.ArrayList;
import java.util.List;

public class SimpleAvailabilityService implements AvailabilityService {

    private final List<ChargingStation> stations;

    public SimpleAvailabilityService(List<ChargingStation> stations) {
        this.stations = stations;
    }

    @Override
    public int countAvailableStations() {
        int count = 0;
        for (ChargingStation s : stations) {
            if (s.getStatus() == ChargingStatus.AVAILABLE) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countAvailableStationsByLot(ParkingLot lot) {
        int count = 0;
        for (ChargingStation s : stations) {
            if (s.getLot().getId() == lot.getId() &&
                    s.getStatus() == ChargingStatus.AVAILABLE) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<ChargingStation> getStationsByLot(ParkingLot lot) {
        List<ChargingStation> result = new ArrayList<>();
        for (ChargingStation s : stations) {
            if (s.getLot().getId() == lot.getId()) {
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public List<ChargingStation> getAvailableStations() {
        List<ChargingStation> result = new ArrayList<>();
        for (ChargingStation s : stations) {
            if (s.getStatus() == ChargingStatus.AVAILABLE) {
                result.add(s);
            }
        }
        return result;
    }
}
