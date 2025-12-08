/**
 * Represents an UMBC system user.
 * When logging in, the system simulates a permit lookup based on
 * the user's campus ID by randomly assigning a parking permit type.
 */


import java.util.Random;
import java.util.Scanner;

public class User {
    private String name;
    private String campusId;
    private String permitType; // Daily / Annual / Hourly

    public User(String name, String campusId, String permitType) {
        this.name = name;
        this.campusId = campusId;
        this.permitType = permitType;
    }

    public String getName() {
        return name;
    }

    public String getCampusId() {
        return campusId;
    }

    public String getPermitType() {
        return permitType;
    }

    /**
     * Prompts the user for name and campus ID.
     * Randomly assigns a parking permit to simulate database lookup.
     *
     * @param scanner the Scanner used for user input
     * @return a new User with randomly generated permit type
     */

        public static User login(Scanner scanner) {
        System.out.println("===== EV Charging System Login =====");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your campus ID (ex: A12345678): ");
        String id = scanner.nextLine();

        // Fake “lookup”: randomly assign a permit type
        String[] permits = {"Daily", "Annual", "Hourly"};
        Random random = new Random();
        String permit = permits[random.nextInt(permits.length)];

        System.out.println("Looking up your account for campus ID " + id + "...");
        System.out.println("Permit found: " + permit + " permit.");
        System.out.println("Login successful. Welcome, " + name + "!");
        System.out.println();

        return new User(name, id, permit);
    }

    @Override
    public String toString() {
        return name + " (" + campusId + "), Permit: " + permitType;
    }
}
