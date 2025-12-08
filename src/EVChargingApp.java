/**
 * Entry point for the UMBC EV Charging Map simulation.
 * Handles user login and initializes the EVChargingSystem controller.
 */


import java.util.Scanner;

public class EVChargingApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Simple "login"
        User currentUser = User.login(scanner);

        // Hand off control to the system class
        EVChargingSystem system = new EVChargingSystem(scanner, currentUser);
        system.run();

        scanner.close();
    }
}
