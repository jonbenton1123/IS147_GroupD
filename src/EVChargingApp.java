/**
 * Entry point of the UMBC EV Charging Map project.
 * This class handles launching the application, prompting user login,
 * and starting the EVChargingSystem controller.
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
