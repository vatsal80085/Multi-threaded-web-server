
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public void run() throws UnknownHostException, IOException {
        int port = 8080;
        InetAddress address = InetAddress.getLocalHost();
        Socket socket = new Socket(address, port);

        // FIXED: Added 'true' so the message sends immediately
        PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // This now sends immediately because of the fix above
        toSocket.println("Hello From the Client");

        String line = fromSocket.readLine();
        System.out.println("Response from the socket is: " + line);

        toSocket.close();
        fromSocket.close();
        socket.close();
    }
    public static void main(String[] args) throws UnknownHostException, IOException {
        try {
            Client client = new Client();
            client.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}