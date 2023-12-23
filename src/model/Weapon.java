package model;

public class Weapon extends GameObject {
    private int damage;

    public Weapon(String name, String description, int damage) {
        super(name, description, true); 
        this.damage = damage;
    }

    public Weapon(String name) {
        super(name, "", true); 
        this.damage = 20; 
    }

    public int getDamage() {
        return damage;
    }
}