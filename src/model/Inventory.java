package model;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Potion> potions;
    private List<Weapon> weapons;
    private List<GameObject> objects;

    public Inventory() {
        this.potions = new ArrayList<>();
        this.weapons = new ArrayList<>();
        this.objects = new ArrayList<>();
    }

    public void equipWeapon(Weapon weapon) {
        weapons.add(weapon);
        System.out.println("Equipping weapon: " + weapon.getName());
    }

    public void usePotion(Adventurer adventurer) {
        if (!potions.isEmpty()) {
            Potion usedPotion = potions.remove(0);
            int restoredHealth = adventurer.getHealth() + usedPotion.getHealingPower();
            adventurer.setHealth(Math.min(restoredHealth, 100)); // Ensure health doesn't exceed 100
            System.out.println("Used " + usedPotion.getName() + ". Health Restored: " + usedPotion.getHealingPower());
            System.out.println("Adventurer's Health: " + adventurer.getHealth());
        } else {
            System.out.println("No potions available.");
        }
    }

    public List<Weapon> getAvailableWeapons() {
        return new ArrayList<>(weapons);
    }

      public void displayAvailablePotions() {
        for (Potion potion : potions) {
            System.out.println("Potion: " + potion.getName());
        }
    }

    public void addObject(Object selectedObject) {
        objects.add((GameObject) selectedObject);
    }
}
