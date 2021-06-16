package projects.airlineticketapplication;

public class DestinationItem {
    private final String destination, destinationInfo, destinationId;

    DestinationItem(String destination, String destinationInfo, String destinationId) {
        this.destination = destination;
        this.destinationInfo = destinationInfo;
        this.destinationId = destinationId;
    }

    String getDestination() {
        return destination;
    }

    String getDestinationInfo() {
        return destinationInfo;
    }

    String getDestinationId() {
        return destinationId;
    }
}
