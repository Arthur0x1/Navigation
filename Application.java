import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {
    private Room currentRoom = Room.H5_2;

    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println(currentRoom);

        while (true) {
            String line = in.nextLine();
            String[] elems = line.split(" ");
            if (elems.length != 1) {
                System.err.println("Invalid input, please only enter your destination");
            }
            Room nextRoom = Room.fromString(elems[0]);

            if (nextRoom == null) {
                //System.out.println("There is no door!");
                System.out.println("Room does not exist");
            } else {
                List<Room> path = ShortestPathFinder.findShortestPath(currentRoom, nextRoom);
                System.out.println(path.stream().map(Room::getName).collect(Collectors.joining(" ")));
                currentRoom = nextRoom;
            }
        }
    }
}
