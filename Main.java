public class Main {
    public static void main(String[] args) {
        Application application = new Application();
        //application.run();

        System.out.println("-------------");

        System.out.println(ShortestPathFinder.findShortestPath(Room.FORUM,Room.H4).stream().map(Room::getName).toList());
    }
}
