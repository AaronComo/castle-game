package src.castle;

import java.util.HashMap;
import java.util.Set;

public class Room {
    private String description;
    private HashMap<String, Room> exits = new HashMap<String, Room>();  // use container to increase flexibility

    public Room(String description) {    // recieve map of room
        this.description = description;
    }

    public void setExit(String dir, Room room) {
        exits.put(dir, room);
    }

    @Override
    public String toString() {
        return description;
    }

    public String getExitDesc() {               //  let room return exits
        StringBuffer sb = new StringBuffer();
        for (String dir : exits.keySet()) { // list keys(exits) of the room
            sb.append(dir).append(" ");
        }
        sb.append("random ");       // a random direction
        return sb.toString();
    }
    
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public Set<String> getAllExit() {
        return exits.keySet();
    }
}
