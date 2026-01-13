import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class CalculatorServer {

    private static final int PORT = 5001;

    public static void main(String[] args) {
        System.out.println("Calculator Server starting...");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server listening on port " + PORT);

            while (true) {
                System.out.println("\nWaiting for client connection...");

                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                handleClient(clientSocket);
            }

        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            out.println("Welcome to Calculator Server!");
            out.println("Format: number1 operator number2 (e.g., 5 + 3)");
            out.println("Type 'quit' to disconnect");

            String input;
            while ((input = in.readLine()) != null) {
                System.out.println("Received: " + input);

                if (input.equalsIgnoreCase("quit")) {
                    out.println("Goodbye!");
                    break;
                }

                String result = calculate(input);
                out.println("Result: " + result);
            }

        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Client disconnected");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String calculate(String expression) {
        try {
            String[] parts = expression.trim().split("\\s+");
            if (parts.length != 3) {
                return "Invalid format. Use: number1 operator number2";
            }

            double num1 = Double.parseDouble(parts[0]);
            String operator = parts[1];
            double num2 = Double.parseDouble(parts[2]);
            double result;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0)
                        return "Error: Division by zero";
                    result = num1 / num2;
                    break;
                default:
                    return "Unknown operator: " + operator;
            }

            return String.valueOf(result);

        } catch (NumberFormatException e) {
            return "Error: Invalid number format";
        }
    }
}
