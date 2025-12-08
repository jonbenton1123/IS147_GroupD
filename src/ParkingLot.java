/**
 * Represents a parking lot on UMBC’s campus that contains EV charging stations.
 * Contains identifying information such as ID, name, and description.
 */


    public class ParkingLot {
    private int id;
    private String name;
    private String description;

    public ParkingLot(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return id + " - " + name + " (" + description + ")";
    }
}
