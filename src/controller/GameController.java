package controller;

import model.Adventurer;
import model.BossMonster;
import model.GameObject;
import model.Inventory;
import model.MiniMonster;
import model.Monster;
import model.Room;
import model.TextAdventureGameModel;
import model.Weapon;

import view.GameView;

import java.util.InputMismatchException;
import java.util.List;

public class GameController {
    private Adventurer player;
    private Room currentRoom;
    private Inventory inventory;
    private GameView view;
    private TextAdventureGameModel model;

    public GameController(Adventurer player, Room initialRoom, Inventory inventory, TextAdventureGameModel model, GameView view) {
        this.player = player;
        this.currentRoom = initialRoom;
        this.inventory = inventory;
        this.model = model;
        this.view = view;
    }

    public void startGame() {
        initializeGame();

        // Main game loop
        while (true) {
            displayGameInfo();
            int choice = view.getUserInput();
            handlePlayerInput(choice);
        }
    }

    private void initializeGame() {
        // Room 1: Pilih Senjata
        Room room1 = new Room("Weapon Selection Room", 0);
        room1.addObject(new Weapon("Sword", "A sharp sword", 20));
        room1.addObject(new Weapon("Bow", "A powerful bow", 15));
        model.addRoom(room1);

        // Room 2: Skeleton
        Room room2 = new Room("Skeleton Room", 1);
        room2.addMonster(new Monster("Skeleton", 30, 10, 10));
        model.addRoom(room2);

        // Room 3: Goblin
        Room room3 = new Room("Goblin Room", 2);
        room3.addMonster(new Monster("Goblin", 40, 15, 15));
        model.addRoom(room3);

        // Room 4: Orc
        Room room4 = new Room("Orc Room", 3);
        room4.addMonster(new Monster("Orc", 50, 20, 20));
        model.addRoom(room4);

        // Room 5: Mini Monster
        Room room5 = new Room("Mini Monster Room", 4);
        room5.addMonster(new MiniMonster("Mini Monster", 20, 5, 25));
        model.addRoom(room5);

        // Room 6: Wyvern
        Room room6 = new Room("Wyvern Room", 5);
        room6.addMonster(new Monster("Wyvern", 60, 25, 30));
        model.addRoom(room6);

        // Room 7: Boss Monster
        Room room7 = new Room("Boss Monster Room", 6);
        room7.addMonster(new BossMonster("Dragon", 100, 30,50));
        model.addRoom(room7);

        // Set current room to the first room
        currentRoom = room1;
    }


    private void displayGameInfo() {
        view.displayRoomInfo(currentRoom);
        view.displayOptions();
    }

    private void handlePlayerInput(int choice) {
        switch (choice) {
            case 1:
                movePlayer();
                break;
            case 2:
                attackMonster();
                break;
            case 3:
                collectObjects();
                break;
            case 4:
                openInventory();
                break;
            case 5:
                view.displayPlayerStatus(player);
                break;
            case 6:
                view.displayExitMessage();
                System.exit(0);
            default:
                view.displayInvalidChoiceMessage();
        }
    }

    private void movePlayer() {
     view.displayMoveOptions();
    int directionChoice = view.getUserInputForDirection();

    if (directionChoice == 5) {
        return; // Exit from the move option menu
    }

    int nextRoomIndex = calculateNextRoomIndex(directionChoice);

    if (isValidRoomIndex(nextRoomIndex)) {
        movePlayerToRoom(nextRoomIndex);
    } else {
        view.displayCannotMoveMessage();
    }
}

private int calculateNextRoomIndex(int directionChoice) {
    switch (directionChoice) {
        case 1:
            return currentRoom.getIndex() - 1; // Move north
        case 2:
            return currentRoom.getIndex() + 1; // Move south
        case 3:
            return currentRoom.getIndex() - 2; // Move west
        case 4:
            return currentRoom.getIndex() + 2; // Move east
        default:
            return -1; // Invalid choice
    }
}

private boolean isValidRoomIndex(int index) {
    return index >= 0 && index < model.getTotalRooms();
}

private void movePlayerToRoom(int nextRoomIndex) {
    currentRoom = model.getRoom(nextRoomIndex);
    player.setPosition(nextRoomIndex);
    view.displayMoveSuccessMessage(currentRoom.getDescription());
}


private void attackMonster() {
    List<Monster> monsters = currentRoom.getMonsters();

    if (monsters.isEmpty()) {
        view.displayNoMonstersMessage();
        return;
    }

    view.displayAvailableMonsters(monsters);

    int monsterChoice = view.getUserInputForMonsters(monsters.size());

    if (monsterChoice <= 0 || monsterChoice > monsters.size()) {
        view.displayInvalidMonsterChoiceMessage();
        return;
    }

    Monster selectedMonster = monsters.get(monsterChoice - 1);
    player.attack(selectedMonster);

    if (selectedMonster.isDefeated()) {
        player.increaseScore(selectedMonster.getScoreValue());  // Increase score
        view.displayDefeatedMonsterMessage(selectedMonster.getName(), player.getScore());
        currentRoom.removeMonster(selectedMonster);
    } else {
        view.displaySuccessfulAttackMessage(selectedMonster.getName(), selectedMonster.getHealth());
    }
}

    private void collectObjects() {
    List<GameObject> objects = currentRoom.getObjects();

    if (objects.isEmpty()) {
        view.displayNoObjectsMessage();
        return;
    }

    view.displayAvailableObjects(objects);

    int objectChoice = view.getUserInputForObject(objects.size());

    if (objectChoice <= 0 || objectChoice > objects.size()) {
        view.displayInvalidObjectChoiceMessage();
        return;
    }

    GameObject selectedObject = objects.get(objectChoice - 1);

    player.collectObject(selectedObject);
    view.displayCollectedObjectMessage(selectedObject.getName());
    currentRoom.removeObject(selectedObject);
}

// Modify pasangSenjata() in GameController
private void equipWeapon() {
    List<Weapon> availableWeapons = inventory.getAvailableWeapons();
    if (!availableWeapons.isEmpty()) {
        view.displayAvailableWeapons(availableWeapons);

        try {
            int weaponChoice = view.getUserInputForWeapons(availableWeapons.size());
            if (weaponChoice > 0 && weaponChoice <= availableWeapons.size()) {
                Weapon selectedWeapon = availableWeapons.get(weaponChoice - 1);
                inventory.equipWeapon(selectedWeapon);
                view.displayEquippedWeaponMessage(selectedWeapon.getName());
            } else {
                view.displayInvalidWeaponChoiceMessage();
            }
        } catch (InputMismatchException e) {
            view.displayInvalidInputMessage();
        }
    } else {
        view.displayNoWeaponsMessage();
    }
}



    private void openInventory() {
        view.displayInventoryMenu();
        int inventoryChoice = view.getUserInput();

        switch (inventoryChoice) {
            case 1:
                usePotion();
                break;
            case 2:
                equipWeapon();
                break;
            case 3:
                System.out.println("Exiting Inventory Menu.");
                break;
            default:
                view.displayInvalidChoiceMessage();
        }
    }

    private void usePotion() {
        inventory.usePotion(player);
         view.displayUsedPotionMessage("Potion Name", 10); 
    }

}
