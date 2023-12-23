package model;

import java.util.ArrayList;
import java.util.List;

public class TextAdventureGameModel {
    private Adventurer player;
    private List<Room> rooms;
    private Room currentRoom;

    public TextAdventureGameModel(Adventurer player, List<Room> rooms) {
        this.player = player;
        this.rooms = new ArrayList<>();
        this.currentRoom = rooms.get(0);
    }

    public Adventurer getPlayer() {
        return player;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public int getTotalRooms() {
        return rooms.size();
    }

    public Room getRoom(int index) {
        if (index >= 0 && index < rooms.size()) {
            return rooms.get(index);
        } else {
            return null;
        }
    }
    

}
