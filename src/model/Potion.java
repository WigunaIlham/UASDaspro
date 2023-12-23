package model;

public class Potion extends GameObject {
    private int healingPower;

    public Potion(String name, String description, int healingPower) {
        super(name, description, true); // Assume all potions are usable
        this.healingPower = healingPower;
    }

    public Potion(String name, int healingPower) {
        super(name, "", true); // Assume all potions are usable
        this.healingPower = 20;
    }

    public int getHealingPower() {
        return healingPower;
    }
}
