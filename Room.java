import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    FORUM("Het forum"),
    H1("Hantal 1"),
    H2("Hantal 2"),
    H3("Hantal 3"),
    H4("Hantal 4"),
    H5("Hantal 5"),
    H6("Hantal 6");

    private final String description;
    private final Map<String, Room> exits;

    static {
        FORUM.setExits(H6);
        H6.setExits(H2);
        H2.setExits(H1, H3, H4, H5);
    }

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     *
     * @param description The room's description.
     */
    Room(String description) {
        this.description = description;
        exits = new HashMap<>();
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

    private String getName() {
        return name().toLowerCase();
    }

    /**
     * Return a description of the room in the form:
     * You are in the kitchen.
     * Exits: north west
     *
     * @return A long description of this room
     */
    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
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
}

