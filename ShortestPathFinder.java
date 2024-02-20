import java.util.*;

public class ShortestPathFinder {
    // Methode om de kortste weg te vinden tussen twee kamers
    public static List<Room> findShortestPath(Room start, Room end) {
        Queue<Room> queue = new LinkedList<>(); // Maak een wachtrij om kamers te verkennen
        Map<Room, Room> path = new HashMap<>(); // Kaart om het pad van kamers bij te houden
        Set<Room> visited = new HashSet<>(); // Set om bezochte kamers bij te houden

        queue.add(start); // Voeg startkamer toe aan de wachtrij
        visited.add(start); // Markeer startkamer als bezocht

        while (!queue.isEmpty()) { // Zolang er kamers zijn om te verkennen
            Room currentRoom = queue.poll(); // Haal de volgende kamer uit de wachtrij

            if (currentRoom.equals(end)) { // Als de huidige kamer de eindkamer is
                // Reconstructie van het pad van start naar eind en retourneer het
                return reconstructPath(path, start, end);
            }

            // Verken alle aangrenzende kamers van de huidige kamer
            for (Room neighbor : currentRoom.getAllExits()) {
                if (!visited.contains(neighbor)) { // Als de buurkamer nog niet is bezocht
                    queue.add(neighbor); // Voeg buurkamer toe aan de wachtrij om te verkennen
                    visited.add(neighbor); // Markeer buurkamer als bezocht
                    path.put(neighbor, currentRoom); // Houd bij welke kamer de buurkamer heeft ontdekt
                }
            }
        }

        return null; // Geen pad gevonden van start naar eind
    }

    // Methode om het pad van de eindkamer naar de startkamer opnieuw op te bouwen vanuit de kaart van kamers
    private static List<Room> reconstructPath(Map<Room, Room> path, Room start, Room end) {
        List<Room> shortestPath = new ArrayList<>(); // Lijst om het kortste pad bij te houden
        Room currentRoom = end; // Begin bij de eindkamer

        // Loop door de kaart van kamers om het pad terug te volgen naar de startkamer
        while (!currentRoom.equals(start)) {
            shortestPath.add(currentRoom); // Voeg huidige kamer toe aan het pad
            currentRoom = path.get(currentRoom); // Ga terug naar de vorige kamer in het pad
        }

        shortestPath.add(start); // Voeg startkamer toe aan het pad
        Collections.reverse(shortestPath); // Keer de volgorde van kamers om
        return shortestPath; // Retourneer het kortste pad van start naar eind
    }
}

