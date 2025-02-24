package batch_1;

import java.net.*;
import java.io.*;

public class Chat_Server {
    public static void main(String[] args) {
        DatagramSocket serverSocket = null;
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        try {
            // Create a DatagramSocket to listen on port 9876
            serverSocket = new DatagramSocket(9876);
            System.out.println("Server is running and waiting for messages...");
            
            byte[] receiveData = new byte[1024];
            InetAddress clientAddress = null;
            int clientPort = 0;

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket); // Receive message from client
                
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                clientAddress = receivePacket.getAddress();
                clientPort = receivePacket.getPort();

                System.out.println("Client: " + message);
                
                // Read message from server input and send to client
                System.out.print("Server: ");
                String response = userInput.readLine();
                if (response.equalsIgnoreCase("STOP")) {
                    System.out.println("Server shutting down...");
                    break; // Exit the loop to stop the server
                }

                // Send response to the client
                byte[] sendData = response.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close(); // Close the socket when done
            }
        }
    }
}

