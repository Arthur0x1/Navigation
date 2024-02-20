import java.util.HashMap;
import java.util.Map;

/**
 * Class Room - a room in an adventure game.
 * <p>
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 * <p>
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  For each existing exit, the room
 * stores a reference to the neighboring room.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public enum Room {
    FORUM,
    // H1
    H1_1,
    H1_2,
    H1_3,
    H1_4,
    // H2
    H2_1,
    H2_2,
    H2_3,
    // H3
    H3_1,
    H3_2,
    H3_3,
    // H4
    H4_1,
    H4_2,
    H4_3,
    H5_1,
    H5_2,
    H6_1,
    H6_2;

    private final int x, y;
    private final Map<String, Room> exits = new HashMap<>();

    Room(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static {
        FORUM.setExits(H6);
        H6.setExits(H2);
        H2.setExits(H1, H3, H4, H5);
    }

    static Room fromString(String str) {
        try {
            return valueOf(str.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    /*
    Defines exits for this room.
    This also sets this room as an exit to the passed rooms.
     */
    private void setExits(Room... rooms) {
        for (Room room : rooms) {
            exits.put(room.getName(), room);
            room.exits.put(getName(), this);
        }
    }

    public String getName() {
        return name().toLowerCase();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     *
     * @return Details of the room's exits.
     */
    private String getExitString() {
        StringBuilder returnString = new StringBuilder("Exits:");
        for (String exit : exits.keySet()) {
            returnString.append(" ").append(exit);
        }
        return returnString.toString();
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     *
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }


    @Override
    public String toString() {
        return "You are in %s\n%s".formatted(getName(), getExitString());
    }
}

