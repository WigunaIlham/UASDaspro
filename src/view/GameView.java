package view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import model.Adventurer;
import model.GameObject;
import model.Monster;
import model.Potion;
import model.Room;
import model.Weapon;

public class GameView {
    private Scanner scanner;

    public GameView() {
        this.scanner = new Scanner(System.in);
    }

    public void displayOptions() {
        System.out.println("================================================================");
        System.out.println("Options:");
        System.out.println("1. Move to the next room");
        System.out.println("2. Attack the monster");
        System.out.println("3. Collect Objects");
        System.out.println("4. Open Inventory");
        System.out.println("5. Status Player");
        System.out.println("6. Exit the game");
        System.out.println("================================================================");
    }

    public int getUserInput() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
        
    }

    public void displayMoveOptions() {
        System.out.println("================================================================");
        System.out.println("Choose direction:");
        System.out.println("1. Move north");
        System.out.println("2. Move south");
        System.out.println("3. Move west");
        System.out.println("4. Move east");
        System.out.println("================================================================");
    }

    public void displayPlayerStatus(Adventurer player) {
        System.out.println("================================================================");
        System.out.println("Player Status:");
        System.out.println("Health: " + player.getHealth());
        System.out.println("Score: " + player.getScore());
        System.out.println();
        System.out.println("================================================================");
    }

    public void displayInventoryMenu() {
        System.out.println("================================================================");
        System.out.println("Inventory Menu:");
        System.out.println("1. Use Potion");
        System.out.println("2. Equip Weapon");
        System.out.println("3. Exit Inventory Menu");
        System.out.println("================================================================");
    }

    public void displayAvailableWeapons(List<Weapon> weapons) {
        System.out.println("Available Weapons:");
        for (int i = 0; i < weapons.size(); i++) {
            System.out.println((i + 1) + ". " + weapons.get(i).getName());
        }
    }

    public void displayEquipWeaponMessage(String weaponName) {
        System.out.println("Equipped " + weaponName + "!");
        System.out.println("================================================================");
    }

    public void displayNoWeaponsMessage() {
        System.out.println("No weapons available.");
        System.out.println("================================================================");
    }

    public void displayMoveSuccessMessage(String roomDescription) {
        System.out.println("You have successfully moved to the next room.");
        System.out.println("Current Room: " + roomDescription);
        System.out.println("================================================================");
    }

    public void displayCannotMoveMessage() {
        System.out.println("You cannot move in that direction.");
        System.out.println("================================================================");
    }

    public int getUserInputForDirection() {
        int choice = 0;
        try {
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next(); // clear the buffer
        }
        return choice;
    }


    public void displayRoomInfo(Room room) {
        System.out.println("================================================================");
        System.out.println("Current Room: " + room.getDescription());
        System.out.println("================================================================");
        System.out.println("Monsters in the Room:");
        for (Monster monster : room.getMonsters()) {
            System.out.println(" - " + monster.getName());
        }
        System.out.println("================================================================");
        System.out.println("Objects in the Room:");
        for (GameObject object : room.getObjects()) {
            System.out.println(" - " + object.getName() + ": " + object.getDescription());
        }
    }

    public void displayAvailableMonsters(List<Monster> monsters) {
        System.out.println("Available Monsters:");
        System.out.println("================================================================");
        for (int i = 0; i < monsters.size(); i++) {
            System.out.println((i + 1) + ". " + monsters.get(i).getName());
        }
    }

    public int getUserInputForMonsters(int maxOptions) {
        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                if (choice >= 1 && choice <= maxOptions) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();  // Consume the invalid input
            }
        }

        return choice;
    }


    public void displayDefeatedMonsterMessage(String monsterName, int scoreValue) {
        System.out.println("You defeated " + monsterName + " and earned " + scoreValue + " points!");
    }

    public void displaySuccessfulAttackMessage(String monsterName, int remainingHealth) {
        System.out.println("You successfully attacked " + monsterName + ". Remaining health: " + remainingHealth);
    }

    public void displayNoMonstersMessage() {
        System.out.println("There are no monsters in the room to attack.");
        System.out.println("================================================================");
    }

    public void displayInvalidMonsterChoiceMessage() {
        System.out.println("Invalid monster choice. Please enter a valid number.");
        System.out.println("================================================================");
    }

    public void displayInvalidInputMessage() {
        System.out.println("Invalid input. Please enter a number.");
        System.out.println("================================================================");
    }

    public void displayInvalidChoiceMessage() {
        System.out.println("Invalid choice. Please choose a valid option.");
        System.out.println("================================================================");
    }

    public void displayNoPotionsMessage() {
        System.out.println("No potions available.");
        System.out.println("================================================================");
    }

    public void displayAvailablePotions(List<Potion> potions) {
        System.out.println("Available Potions:");
        System.out.println("================================================================");
        for (int i = 0; i < potions.size(); i++) {
            System.out.println((i + 1) + ". " + potions.get(i).getName());
        }
    }

    public int getUserInputForPotion(int maxChoices) {
        int choice = -1;

        try {
            System.out.print("Enter the number of the potion to use: ");
            System.out.println("================================================================");
            choice = scanner.nextInt();

            if (choice < 1 || choice > maxChoices) {
                displayInvalidPotionChoiceMessage();
                choice = -1;
            }
        } catch (InputMismatchException e) {
            displayInvalidInputMessage();
            scanner.nextLine(); 
        }

        return choice;
    }

    public void displayUsedPotionMessage(String potionName, int healingPower) {
        System.out.println("Used " + potionName + ". Health Restored: " + healingPower);
        System.out.println("================================================================");
    }

    public void displayInvalidPotionChoiceMessage() {
        System.out.println("Invalid potion choice. Please enter a valid number.");
        System.out.println("================================================================");
    }

    public void displayInvalidWeaponChoiceMessage() {
        System.out.println("Invalid weapon choice. Please enter a valid number.");
        System.out.println("================================================================");
    }

    public int getUserInputForWeapon(int maxChoices) {
        return 0; 
    }

    public void displayEquippedWeaponMessage(String weaponName) {
        System.out.println("Weapon equipped: " + weaponName);
        System.out.println("================================================================");
    }

    public void displayExitMessage() {
        System.out.println("Exiting the game. Goodbye!");
        System.out.println("================================================================");
    }


    public int getUserInputForMonster(int maxChoices) {
        int choice = -1;
        try {
            System.out.print("Enter the number of the monster to attack: ");
            System.out.println("================================================================");
            choice = scanner.nextInt();

            // Validate user input
            if (choice < 1 || choice > maxChoices) {
                displayInvalidMonsterChoiceMessage();
                choice = -1;
            }
        } catch (java.util.InputMismatchException e) {
            displayInvalidInputMessage();
        } finally {
            scanner.nextLine(); // Consume the newline character
        }
        return choice;
    }

    public void displayAvailableObjects(List<GameObject> objects) {
        if (!objects.isEmpty()) {
            System.out.println("Objek yang tersedia:");
            System.out.println("================================================================");
            for (int i = 0; i < objects.size(); i++) {
                GameObject object = objects.get(i);
                System.out.println((i + 1) + ". " + object.getName());
            }
        } else {
            displayNoObjectsMessage();
        }
    }

    public int getUserInputForObject(int maxChoice) {
        int userInput = -1;

        if (maxChoice > 0) {
            System.out.print("Pilih objek (1-" + maxChoice + "): ");
            userInput = scanner.nextInt();
        }

        return userInput;
    }

    public void displayNoObjectsMessage() {
        System.out.println("Tidak ada objek yang tersedia di ruangan ini.");
        System.out.println("================================================================");
    }

    public void displayInvalidObjectChoiceMessage() {
        System.out.println("Pilihan objek tidak valid. Silakan pilih kembali.");
        System.out.println("================================================================");
    }

    public void displayObjectCollectedMessage(String objectName) {
        System.out.println("Anda mengambil: " + objectName);
        System.out.println("================================================================");
    }

    public void displayCollectedObjectMessage(String name) {
        System.out.println("Anda mengambil: " + name);
        System.out.println("================================================================");
    }   

    public int getUserInputForWeapons(int maxChoice) {
        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter your choice for weapons: ");
                choice = scanner.nextInt();

                if (choice >= 1 && choice <= maxChoice) {
                    validInput = true;
                } else {
                    System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
            }
        }

        return choice;
    }

}
