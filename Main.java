import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true) {
                Socket client = serverSocket.accept();
                //System.out.println(client.getInputStream().read());
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                out.writeBytes("hello");
            }
        }
         */

        for (Room start : Room.values()) {
            for (Room end : Room.values()) {
                System.out.println(ShortestPathFinder.findShortestPath(start, end));
            }
        }

        //Application application = new Application();
        //application.run();
    }
}
