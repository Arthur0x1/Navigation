import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Application {
    private final Parser parser = new Parser();
    private Room currentRoom = Room.FORUM;

    public void run() {
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println(currentRoom);

        while (true) {
            Command command = parser.getCommand();
            boolean requestsQuit = processCommand(command);
            if (requestsQuit) break;
        }
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        CommandWord commandWord = command.commandWord();
        boolean wantToQuit = false;

        switch (commandWord) {
            case UNKNOWN -> System.out.println("Unknown command");
            case HELP -> printHelp();
            case GO -> goRoom(command);
            case QUIT -> wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    /*
    /*
    Returns a list of rooms to traverse to get from start to destination.
    The destination is included in the returned list.
    Returns {@code Collections.emptyList()} when start and destination are the same.
     * /
    private static List<Room> findPath(Room start, Room destination) {
        // only start location H2 and FORUM require intermediary 'rooms' to be entered
        // e.g. H2 -> H6 -> FORUM
        // read the exits from Room.exits

        // if the destination is the same as the start, return a list with the start
        return switch (start) {
            case FORUM -> switch (destination) {
                case FORUM -> Collections.emptyList();
                case H1 -> List.of(Room.H6, Room.H2, Room.H1);
                case H2 -> List.of(Room.H6, Room.H2);
                case H3 -> List.of(Room.H6, Room.H2, Room.H3);
                case H4 -> List.of(Room.H6, Room.H2, Room.H4);
                case H5 -> List.of(Room.H6, Room.H2, Room.H5);
                case H6 -> List.of(Room.H6);
            };
            case H1 -> switch (destination) {
                case FORUM -> List.of(Room.H2, Room.H6, Room.FORUM);
                case H1 -> Collections.emptyList();
                case H2 -> List.of(Room.H2);
                case H3 -> List.of(Room.H2, Room.H3);
                case H4 -> List.of(Room.H2, Room.H4);
                case H5 -> List.of(Room.H2, Room.H5);
                case H6 -> List.of(Room.H2, Room.H6);
            };
            case H2 -> switch (destination) {
                case FORUM -> List.of(Room.H6, Room.FORUM);
                case H1 -> List.of(Room.H1);
                case H2 -> Collections.emptyList();
                case H3 -> List.of(Room.H3);
                case H4 -> List.of(Room.H4);
                case H5 -> List.of(Room.H5);
                case H6 -> List.of(Room.H6);
            };
            case H3 -> switch (destination) {
                case FORUM -> List.of(Room.H2, Room.H6, Room.FORUM);
                case H1 -> List.of(Room.H2, Room.H1);
                case H2 -> List.of(Room.H2, Room.H3);
                case H3 -> Collections.emptyList();
                case H4 -> List.of(Room.H2, Room.H4);
                case H5 -> List.of(Room.H2, Room.H5);
                case H6 -> List.of(Room.H2, Room.H6);
            };
            case H4 -> switch (destination) {
                case FORUM -> List.of(Room.H2, Room.H6, Room.FORUM);
                case H1 -> List.of(Room.H2, Room.H1);
                case H2 -> List.of(Room.H2);
                case H3 -> List.of(Room.H2, Room.H3);
                case H4 -> Collections.emptyList();
                case H5 -> List.of(Room.H2, Room.H5);
                case H6 -> List.of(Room.H2, Room.H6);
            };
            case H5 -> switch (destination) {
                case FORUM -> List.of(Room.H2, Room.H6, Room.FORUM);
                case H1 -> List.of(Room.H2, Room.H1);
                case H2 -> List.of(Room.H2);
                case H3 -> List.of(Room.H2, Room.H3);
                case H4 -> List.of(Room.H2, Room.H4);
                case H5 -> Collections.emptyList();
                case H6 -> List.of(Room.H2, Room.H6);
            };
            case H6 -> switch (destination) {
                case FORUM -> List.of(Room.FORUM);
                case H1 -> List.of(Room.H2, Room.H1);
                case H2 -> List.of(Room.H2);
                case H3 -> List.of(Room.H2, Room.H3);
                case H4 -> List.of(Room.H2, Room.H4);
                case H5 -> List.of(Room.H2, Room.H5);
                case H6 -> Collections.emptyList();
            };
        };
    }
    */

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.secondWord();
        //Room nextRoom = currentRoom.getExit(direction);
        Room nextRoom = Room.fromString(direction);

        if (nextRoom == null) {
            //System.out.println("There is no door!");
            System.out.println("Room does not exist");
        } else {
            //List<Room> path = findPath(currentRoom, nextRoom);
            System.out.printf("path: [%s]\n", path.stream().map(Room::getName)
                    .collect(Collectors.joining(" ")));
            currentRoom = nextRoom;
            System.out.println(currentRoom);
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        return true;  // signal that we want to quit
    }
}
