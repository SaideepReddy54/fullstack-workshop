
import java.net.Socket;
import java.io.*;

public class CalculatorClient {

    private static final String SERVER = "localhost";
    private static final int PORT = 5001;

    public static void main(String[] args) {
        System.out.println("Connecting to Calculator Server...");

        try (Socket socket = new Socket(SERVER, PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            String serverMessage;
            for (int i = 0; i < 3; i++) {
                serverMessage = in.readLine();
                System.out.println("Server: " + serverMessage);
            }

            System.out.println("\nEnter calculations:");

            String userLine;
            while ((userLine = userInput.readLine()) != null) {
                out.println(userLine);

                String response = in.readLine();
                System.out.println("Server: " + response);

                if (userLine.equalsIgnoreCase("quit")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Connection error: " + e.getMessage());
        }

        System.out.println("Disconnected from server.");
    }
}