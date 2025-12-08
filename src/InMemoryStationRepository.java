/**
 * In-memory implementation of StationRepository.
 * Stores 19 prebuilt chargers and randomizes their status.
 */


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InMemoryStationRepository implements StationRepository {

    private final List<ParkingLot> lots = new ArrayList<>();
    private final List<ChargingStation> stations = new ArrayList<>();
    private final Random random = new Random();

    public InMemoryStationRepository() {
        // Create parking lots
        ParkingLot walker = new ParkingLot(1, "Walker Avenue Garage", "10 charging stations");
        ParkingLot lot9 = new ParkingLot(2, "Lot 9", "7 charging stations");
        ParkingLot stadium = new ParkingLot(3, "Stadium Lot", "2 charging stations");

        lots.add(walker);
        lots.add(lot9);
        lots.add(stadium);

        int stationId = 1;

        // Walker Garage: 10 stations G1..G10
        for (int i = 1; i <= 10; i++) {
            String code = "G" + i;
            stations.add(new ChargingStation(
                    stationId++,
                    code,
                    walker,
                    "Level 2",
                    ChargingStatus.AVAILABLE
            ));
        }

        // Lot 9: 7 stations L1..L7
        for (int i = 1; i <= 7; i++) {
            String code = "L" + i;
            stations.add(new ChargingStation(
                    stationId++,
                    code,
                    lot9,
                    "Level 2",
                    ChargingStatus.AVAILABLE
            ));
        }

        // Stadium: 2 stations D1..D2
        for (int i = 1; i <= 2; i++) {
            String code = "D" + i;
            stations.add(new ChargingStation(
                    stationId++,
                    code,
                    stadium,
                    "Level 2",
                    ChargingStatus.AVAILABLE
            ));
        }

        // initial random status
        randomizeStatuses();
    }

    @Override
    public List<ParkingLot> getAllLots() {
        return lots;
    }

    @Override
    public List<ChargingStation> getAllStations() {
        return stations;
    }

    @Override
    public ChargingStation findByCode(String code) {
        for (ChargingStation s : stations) {
            if (s.getCode().equalsIgnoreCase(code)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public void randomizeStatuses() {
        for (ChargingStation s : stations) {
            int r = random.nextInt(100); // 0–99

            if (r < 60) {
                s.setStatus(ChargingStatus.AVAILABLE);
            } else if (r < 90) {
                s.setStatus(ChargingStatus.IN_USE);
            } else {
                s.setStatus(ChargingStatus.OUT_OF_SERVICE);
            }
        }

        // guarantee at least one available
        boolean anyAvailable = false;
        for (ChargingStation s : stations) {
            if (s.getStatus() == ChargingStatus.AVAILABLE) {
                anyAvailable = true;
                break;
            }
        }
        if (!anyAvailable && !stations.isEmpty()) {
            ChargingStation randomStation =
                    stations.get(random.nextInt(stations.size()));
            randomStation.setStatus(ChargingStatus.AVAILABLE);
        }
    }
}
