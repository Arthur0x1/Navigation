import java.util.HashMap;
import java.util.Map;
import java.util.*;

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
    H5_0,
    // H5
    H5_1,
    H5_2,
    // H6
    H6_1,
    H6_2;
    private final Map<String, Room> exits = new HashMap<>();

    static {
        FORUM.setExits(H6_1);
        H1_1.setExits(H1_2, H1_3, H2_1);
        H1_2.setExits(H1_1, H1_3);
        H1_3.setExits(H1_4, H1_2, H1_1);
        H1_4.setExits(H1_3);
        H2_1.setExits(H1_1, H2_2, H3_1, H6_2);
        H2_2.setExits(H2_1, H2_3);
        H2_3.setExits(H2_2, H4_1, H5_0);
        H3_1.setExits(H2_1, H3_2, H3_3);
        H3_2.setExits(H3_1, H3_3);
        H3_3.setExits(H3_2, H3_1);
        H4_1.setExits(H4_2, H4_3, H2_3);
        H4_2.setExits(H4_1, H4_3);
        H4_3.setExits(H4_2, H4_1);
        H5_0.setExits(H5_1, H2_3);
        H5_1.setExits(H5_2, H5_0);
        H5_2.setExits(H5_1);
        H6_1.setExits(FORUM, H6_2);
        H6_2.setExits(H2_1, H6_1);
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

    public Collection<Room> getAllExits(){
        return exits.values();
    }
    @Override
    public String toString() {
        return "You are in %s\n%s".formatted(getName(), getExitString());
    }
}

