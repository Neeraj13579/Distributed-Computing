package batch_1;


import java.net.*;
import java.io.*;

public class Chat_Client {
    public static void main(String[] args) {
        DatagramSocket clientSocket = null;
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        try {
            // Create a DatagramSocket
            clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 9876;

            while (true) {
                // Read message from user
                System.out.print("Client: ");
                String message = userInput.readLine();
                if (message.equalsIgnoreCase("STOP")) {
                    System.out.println("Client shutting down...");
                    break; // Exit the loop to stop the client
                }

                // Send the message to the server
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                clientSocket.send(sendPacket);

                // Receive response from the server
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server: " + serverResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close(); // Close the socket when done
            }
        }
    }
}
