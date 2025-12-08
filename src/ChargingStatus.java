/**
 * Enum defining the three possible states of an EV charging station:
 * - AVAILABLE: Free for use
 * - IN_USE: Currently occupied
 * - OUT_OF_SERVICE: Not functional
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
