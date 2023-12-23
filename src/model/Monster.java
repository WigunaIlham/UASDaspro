package model;

public class Monster {
    private String name;
    private int health;
    private int attackPower;
    private int scoreValue;

    public Monster(String name, int health, int attackPower, int scoreValue) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
         this.scoreValue = scoreValue;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttackDamage() {
        return attackPower;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    } 

    public boolean isDefeated() {
        return health <= 0;
    }

   public int getScoreValue() {
        return scoreValue;
    }
}
