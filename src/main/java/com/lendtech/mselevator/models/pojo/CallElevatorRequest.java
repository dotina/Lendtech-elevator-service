package com.lendtech.mselevator.models.pojo;

public class CallElevatorRequest {
    private Long elevatorID;
    private Long destinationFloor;

    public CallElevatorRequest() {
    }

    public CallElevatorRequest(Long elevatorID, Long destinationFloor) {
        this.elevatorID = elevatorID;
        this.destinationFloor = destinationFloor;
    }

    public Long getElevatorID() {
        return elevatorID;
    }

    public void setElevatorID(Long elevatorID) {
        this.elevatorID = elevatorID;
    }

    public Long getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(Long destinationFloor) {
        this.destinationFloor = destinationFloor;
    }
}
