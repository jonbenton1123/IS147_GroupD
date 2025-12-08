/**
 * Controls all user interaction, menu navigation, and application flow.
 * Uses StationRepository for data and AvailabilityService for logic.
 */

import java.util.List;
import java.util.Scanner;

public class EVChargingSystem {

    private final Scanner scanner;
    private final User currentUser;
    private final StationRepository stationRepository;
    private final AvailabilityService availabilityService;

    public EVChargingSystem(Scanner scanner, User currentUser) {
        this.scanner = scanner;
        this.currentUser = currentUser;

        // choose implementation for data
        this.stationRepository = new InMemoryStationRepository();

        // availability service works over the repository's stations
        this.availabilityService =
                new SimpleAvailabilityService(stationRepository.getAllStations());
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    listParkingLots();
                    break;
                case "2":
                    listAllStations();
                    break;
                case "3":
                    listStationsByLot();
                    break;
                case "4":
                    // "I'm driving now" – live check, choose station, summary, exit
                    boolean shouldExit = driveToStationFlow();
                    if (shouldExit) {
                        running = false;
                    }
                    break;
                case "5":
                    updateStationStatus();      // debug / admin
                    break;
                case "6":
                    showAvailabilitySummary();
                    break;
                case "0":
                    running = false;
                    System.out.println("Goodbye, " + currentUser.getName() + "!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("===== UMBC EV Charging Map (Terminal Version) =====");
        System.out.println("Logged in as: " + currentUser);
        System.out.println("1. View parking lots (map overview)");
        System.out.println("2. View ALL charging stations (map details)");
        System.out.println("3. View stations by parking lot (map by area)");
        System.out.println("4. I’m driving now → show LIVE available stations and pick one");
        System.out.println("5. (Debug) Manually change a station's status");
        System.out.println("6. Show availability summary (per lot + total)");
        System.out.println("0. Exit");
    }

    // ---------- Menu actions ----------

    private void listParkingLots() {
        System.out.println("=== Parking Lots ===");
        for (ParkingLot lot : stationRepository.getAllLots()) {
            int available = availabilityService.countAvailableStationsByLot(lot);
            System.out.println(lot + " | Available now: " + available);
        }
    }

    private void listAllStations() {
        System.out.println("=== All Charging Stations ===");
        for (ChargingStation station : stationRepository.getAllStations()) {
            System.out.println(station);
        }
    }

    private void listStationsByLot() {
        List<ParkingLot> lots = stationRepository.getAllLots();

        System.out.println("Select a parking lot by ID:");
        for (ParkingLot lot : lots) {
            System.out.println(lot.getId() + " - " + lot.getName());
        }
        System.out.print("Enter lot ID: ");
        String input = scanner.nextLine();

        int lotId;
        try {
            lotId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid lot ID.");
            return;
        }

        ParkingLot selectedLot = null;
        for (ParkingLot lot : lots) {
            if (lot.getId() == lotId) {
                selectedLot = lot;
                break;
            }
        }

        if (selectedLot == null) {
            System.out.println("Lot not found.");
            return;
        }

        System.out.println("=== Stations in " + selectedLot.getName() + " ===");
        List<ChargingStation> lotStations =
                availabilityService.getStationsByLot(selectedLot);
        for (ChargingStation s : lotStations) {
            System.out.println(s);
        }
    }

    private void showAvailableStations() {
        System.out.println("=== AVAILABLE Stations (live view) ===");
        for (ChargingStation station : availabilityService.getAvailableStations()) {
            System.out.println("Code: " + station.getCode()
                    + " | Lot: " + station.getLot().getName()
                    + " | Type: " + station.getType());
        }
        System.out.println("Total available: " + availabilityService.countAvailableStations());
    }

    private void updateStationStatus() {
        System.out.println("Enter station CODE to update (e.g. G4, L2, D1):");
        String codeInput = scanner.nextLine().trim().toUpperCase();

        ChargingStation selected = stationRepository.findByCode(codeInput);

        if (selected == null) {
            System.out.println("Station not found.");
            return;
        }

        System.out.println("Selected: " + selected);
        System.out.println("Choose new status:");
        System.out.println("1 - Available (Green)");
        System.out.println("2 - In Use (Orange)");
        System.out.println("3 - Out of Service (Red)");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                selected.setStatus(ChargingStatus.AVAILABLE);
                break;
            case "2":
                selected.setStatus(ChargingStatus.IN_USE);
                break;
            case "3":
                selected.setStatus(ChargingStatus.OUT_OF_SERVICE);
                break;
            default:
                System.out.println("Invalid choice. Status not changed.");
                return;
        }

        System.out.println("Status updated: " + selected);
    }

    private void showAvailabilitySummary() {
        System.out.println("=== Availability Summary ===");
        for (ParkingLot lot : stationRepository.getAllLots()) {
            int available = availabilityService.countAvailableStationsByLot(lot);
            System.out.println(lot.getName() + " -> Available: " + available);
        }
        System.out.println("Total available on campus: "
                + availabilityService.countAvailableStations());
    }

    // ---------- NEW helper: print status counts (incl. out-of-service) ----------

    private void printStatusCounts() {
        int available = 0;
        int inUse = 0;
        int outOfService = 0;

        StringBuilder outCodes = new StringBuilder();

        for (ChargingStation s : stationRepository.getAllStations()) {
            if (s.getStatus() == ChargingStatus.AVAILABLE) {
                available++;
            } else if (s.getStatus() == ChargingStatus.IN_USE) {
                inUse++;
            } else if (s.getStatus() == ChargingStatus.OUT_OF_SERVICE) {
                outOfService++;
                if (outCodes.length() > 0) {
                    outCodes.append(", ");
                }
                outCodes.append(s.getCode());
            }
        }

        System.out.println("Current status after refresh:");
        System.out.println("  Available:      " + available);
        System.out.println("  In use:         " + inUse);
        System.out.println("  Out of service: " + outOfService);

        if (outOfService > 0) {
            System.out.println("  Out-of-service station codes: " + outCodes);
        }
    }

    // ---------- "I'm driving now" flow (option 4) with call to printStatusCounts ----------

    /**
     * Simulates the user getting ready to drive to a station.
     * 1. Randomize statuses (live update)
     * 2. Show counts (including out-of-service)
     * 3. Show only available chargers
     * 4. User chooses a station code (G1, L3, D1…)
     * 5. Mark that station as IN_USE
     * 6. Show summary
     * 7. Return true → caller will exit program
     */
    private boolean driveToStationFlow() {
        System.out.println("Refreshing live availability before you drive...");
        stationRepository.randomizeStatuses();

        // show counts including out-of-service
        printStatusCounts();

        if (availabilityService.countAvailableStations() == 0) {
            System.out.println("Right now there are no available stations. Sorry!");
            showAvailabilitySummary();
            return true; // exit
        }

        showAvailableStations();

        System.out.println();
        System.out.print("Enter the station CODE you want to drive to (e.g. G4, L2, D1): ");
        String codeInput = scanner.nextLine().trim().toUpperCase();

        ChargingStation chosen = stationRepository.findByCode(codeInput);

        if (chosen == null) {
            System.out.println("That station code does not exist.");
            showAvailabilitySummary();
            return true; // exit anyway (you could change this to retry if you want)
        }

        if (chosen.getStatus() != ChargingStatus.AVAILABLE) {
            System.out.println("Oops! While you were deciding, that station changed to: "
                    + chosen.getStatus().getDescription());
            showAvailabilitySummary();
            return true;
        }

        // Mark as in use to simulate plugging in
        chosen.setStatus(ChargingStatus.IN_USE);
        System.out.println("You have selected station " + chosen.getCode()
                + " in " + chosen.getLot().getName() + ". It is now marked as IN USE.");
        System.out.println();

        // Show final summary, then exit
        showAvailabilitySummary();
        System.out.println("Have a good charge! Exiting the system.");
        return true;
    }
}
