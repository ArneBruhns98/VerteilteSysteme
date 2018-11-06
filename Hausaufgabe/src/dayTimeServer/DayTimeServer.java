package dayTimeServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DayTimeServer {

    private static final int BUFSIZE = 508;
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: java DayTimeServer Port");
            return;
        }

        int port = Integer.parseInt(args[0]);

        // Socket an Port binden
        try (DatagramSocket socket = new DatagramSocket(port)) {

            // Paket zum Empfangen bzw. Senden
            DatagramPacket packet = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);

            while (true) {

                // Paket empfangen
                socket.receive(packet);
                System.out.println("Received: " + packet.getLength() + " bytes");

                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy kk:mm:ss");

                byte data[] = now.format(df).getBytes();
                DatagramPacket zeit = new DatagramPacket(data, data.length, packet.getAddress(), packet.getPort());

                // Paket an den Absender zurueckschicken
                socket.send(zeit);
            }
        } catch (IOException e) {System.err.println(e);}
    }

}
