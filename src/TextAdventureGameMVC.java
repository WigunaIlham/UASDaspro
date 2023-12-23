import controller.GameController;
import model.Adventurer;
import model.BossMonster;
import model.Inventory;
import model.MiniMonster;
import model.Monster;
import model.Room;
import model.TextAdventureGameModel;
import model.Weapon;
import view.GameView;

import java.util.ArrayList;
import java.util.List;

public class TextAdventureGameMVC {
    public static void main(String[] args) {
        Adventurer player = new Adventurer();
        List<Room> rooms = createRooms();
        TextAdventureGameModel model = new TextAdventureGameModel(player, rooms);
        Inventory inventory = player.getInventory();
        GameView view = new GameView();
        GameController controller = new GameController(player, rooms.get(0), inventory, model, view);

        controller.startGame();
    }

    private static List<Room> createRooms() {
        List<Room> rooms = new ArrayList<>();

        // Room 1: Weapon Selection Room
        Room room1 = new Room("Weapon Selection Room", 0);
        room1.addObject(new Weapon("Sword", "A sharp sword", 20));
        room1.addObject(new Weapon("Bow", "A powerful bow", 15));
        rooms.add(room1);

        // Room 2: Skeleton Room
        Room room2 = new Room("Skeleton Room", 1);
        room2.addMonster(new Monster("Skeleton", 30, 10,10));
        rooms.add(room2);

        // Room 3: Goblin Room
        Room room3 = new Room("Goblin Room", 2);
        room3.addMonster(new Monster("Goblin", 40, 15,15));
        rooms.add(room3);

        // Room 4: Orc Room
        Room room4 = new Room("Orc Room", 3);
        room4.addMonster(new Monster("Orc", 50, 20,20));
        rooms.add(room4);

        // Room 5: Mini Monster Room
        Room room5 = new Room("Mini Monster Room", 4);
        room5.addMonster(new MiniMonster("Mini Monster", 20, 5,25));
        rooms.add(room5);

        // Room 6: Wyvern Room
        Room room6 = new Room("Wyvern Room", 5);
        room6.addMonster(new Monster("Wyvern", 60, 25,30));
        rooms.add(room6);

        // Room 7: Boss Monster Room
        Room room7 = new Room("Boss Monster Room", 6);
        room7.addMonster(new BossMonster("Dragon", 100, 30,50));
        rooms.add(room7);

        return rooms;
    }
}
