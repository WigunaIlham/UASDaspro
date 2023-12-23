package model;

import java.util.List;

public class Adventurer {
    private Inventory inventory;
    private int position;
    private int health;
    private int score;

    public Adventurer() {
        this.inventory = new Inventory();
        this.position = 0;
        this.health = 100;
        this.score = 0;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void usePotion() {
        inventory.usePotion(this);
    }

    public List<Weapon> getAvailableWeapons() {
        Inventory inventory = getInventory();
        return inventory.getAvailableWeapons();
    }

    public void equipWeapon(Weapon weapon) {
        Inventory inventory = getInventory();
        inventory.equipWeapon(weapon);
    }

    public void attack(Monster monster) {
        int damage = calculateAttackDamage();
        monster.takeDamage(damage);
    }

    public void collectObject(Object selectedObject) {
        inventory.addObject(selectedObject);
        System.out.println("Anda mengambil: " + ((GameObject) selectedObject).getName());
    }

    private int calculateAttackDamage() {
        return 20;
    }

    public void increaseScore(int points) {
        score += points;
    }
}
