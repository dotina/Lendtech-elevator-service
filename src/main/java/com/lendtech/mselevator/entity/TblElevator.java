package com.lendtech.mselevator.entity;


import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Table("tbl_elevator")
public class TblElevator extends BaseModel implements Serializable {
    private int currentFloor;
    private int destinationFloor;
    private ElevatorDirection direction;
    private boolean isMoving;
    private ElevatorDoorState elevatorDoorState;

    private long maxFloor;

    public TblElevator() {
    }

    public TblElevator(int currentFloor, int destinationFloor, ElevatorDirection direction, boolean isMoving, ElevatorDoorState elevatorDoorState, long maxFloor) {
        this.currentFloor = currentFloor;
        this.destinationFloor = destinationFloor;
        this.direction = direction;
        this.isMoving = isMoving;
        this.elevatorDoorState = elevatorDoorState;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public ElevatorDirection getDirection() {
        return direction;
    }

    public void setDirection(ElevatorDirection direction) {
        this.direction = direction;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public ElevatorDoorState getElevatorDoorState() {
        return elevatorDoorState;
    }

    public void setElevatorDoorState(ElevatorDoorState elevatorDoorState) {
        this.elevatorDoorState = elevatorDoorState;
    }

    public long getMaxFloor() {
        return maxFloor;
    }

    public void setMaxFloor(long maxFloor) {
        this.maxFloor = maxFloor;
    }
}
