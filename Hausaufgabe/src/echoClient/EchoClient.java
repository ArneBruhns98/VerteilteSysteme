package echoClient;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EchoClient {

    private static final int BUFSIZE = 508;
    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Usage: java DayTimeClient <host> <port>");
            return;
        }

        String host = args[0];
        int port = Integer.parseInt(args[1]);

        try (DatagramSocket socket = new DatagramSocket()){

            // Packet an Server senden
            InetAddress addr = InetAddress.getByName(host);
            String s = "Verteilte Systeme";
            byte data[] = s.getBytes();
            DatagramPacket packetOut = new DatagramPacket(data, data.length, addr, port);
            socket.send(packetOut);

            // Antwortpaket empfangen
            DatagramPacket packetIn = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
            socket.receive(packetIn);
            String received = new String(packetIn.getData(), 0, packetIn.getLength());
            System.out.println(received);

        } catch (Exception e) { System.err.println(e);}
    }
}
