/**
 * Enum representing the three possible states of a charging station:
 * AVAILABLE, IN_USE, OUT_OF_SERVICE.
 */


public enum ChargingStatus {
    AVAILABLE("Available", "Green"),
    IN_USE("In Use", "Orange"),
    OUT_OF_SERVICE("Out of Service", "Red");

    private final String description;
    private final String colorCode;

    ChargingStatus(String description, String colorCode) {
        this.description = description;
        this.colorCode = colorCode;
    }

    public String getDescription() {
        return description;
    }

    public String getColorCode() {
        return colorCode;
    }

    @Override
    public String toString() {
        return description + " (" + colorCode + ")";
    }
}
