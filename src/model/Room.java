package model;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String description;
    private List<GameObject> objects;
    private List<Monster> monsters;
    private int index;  

    public Room(String description, int index) {
        this.description = description;
        this.objects = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.index = index;  
    }

    public String getDescription() {
        return description;
    }

    public List<GameObject> getObjects() {
        return objects;
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void addObject(GameObject object) {
        objects.add(object);
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

     public int getIndex() {
        return index;
    }


    public void removeMonster(Monster monster) {
        monsters.remove(monster);
    }

     public void removeObject(Object selectedObject) {
        objects.remove(selectedObject);
    }

    public static int getTotalRooms() {
        return 0;
    }
}

