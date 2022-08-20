/*
 * extend command:
 *  1. add a new Handler class
 *  2. add the new Handler into HashMap in the constructor
 *  3、add exixs of the new room in function createRoom
 */

package src.castle;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Room currentRoom;
    private HashMap<String, Handler> handlers = new HashMap<String, Handler>();
        
    public Game() {
        handlers.put("go", new HandlerGo(this));    // pass 'game' to Handler as parameter
        handlers.put("bye", new HandlerBye(this));
        handlers.put("help", new HandlerHelp(this, handlers.keySet()));
        createRooms();
    }

    private void createRooms() {
        Room outside, lobby, pub, study, bedroom;
        
        //	create rooms
        outside = new Room("城堡外");
        lobby = new Room("大堂");
        pub = new Room("小酒吧");
        study = new Room("书房");
        bedroom = new Room("卧室");
        
        //	initialize exixs of rooms
        outside.setExit("east", lobby);
        outside.setExit("south", study);
        outside.setExit("west", pub);
        lobby.setExit("west", outside);
        pub.setExit("east", outside);
        study.setExit("north", outside);
        study.setExit("east", bedroom);
        bedroom.setExit("west", study);
        lobby.setExit("up", pub);
        pub.setExit("down", lobby);

        currentRoom = outside;  // set birthplace
    }

    private void printWelcome() {
        System.out.println();
        System.out.println("欢迎来到城堡！");
        System.out.println("这是一个超级无聊的游戏。");
        System.out.println("如果需要帮助，请输入 'help' 。");
        System.out.println();
        showPrompt();
    }

    // commands
    public void goRoom(String direction) 
    {
        Room nextRoom = null;
        if (direction.equals("random")) {
            Random rd = new Random();
            Object[] allExit = currentRoom.getAllExit().toArray();
            nextRoom = currentRoom.getExit(allExit[rd.nextInt(allExit.length)].toString());
        }
        else
            nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("那里没有门！\n");
        } else {
            currentRoom = nextRoom;
            showPrompt();
        }
    }
    
    public void showPrompt() {
        System.out.println("现在你在" + currentRoom);
        System.out.print("出口有：");
        System.out.println(currentRoom.getExitDesc()); // reduece coupling
        System.out.println();
    }
    
    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("请输入指令：");
            String line = in.nextLine();
            String[] words = line.split(" ");
            Handler handler = handlers.get(words[0]);
            String value = null;
            if (words.length > 1)
                value = words[1];   // command has more than one word, save addtional command
            if (handler != null) {
                handler.doCmd(value);   // handle addtional command
                if (handler.isBye())
                    break;   
            }
        }
        in.close();
    }
	
	public static void main(String[] args) {
		Game game = new Game();
        game.printWelcome();
        game.run();
        System.out.println("感谢您的光临。再见！");
	}
}
