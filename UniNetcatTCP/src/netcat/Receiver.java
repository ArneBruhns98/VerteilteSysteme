package netcat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Klasse Receiver
 */
public class Receiver implements Runnable{

    /** Datenfeld für den Socket */
    private final Socket socket;
    /** Datenfeld für den Printer */
    private final Printer printer;
    /** Datenfeld für die Nachricjht */
    String message;

    /**
     * Erzeugt ein Objekt der Klasse Receiver
     *
     * @param socket ~ Einlesen eine Sockets (Darf nicht null sein)
     * @param printer ~ Einlesen eines Printers (Darf nicht null sein)
     */
    public Receiver(Socket socket, Printer printer){
       this.socket = socket;
       this.printer = printer;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(!(message = input.readLine()).equals("\u0004")) {
                printer.tell(message, null);
            }
            this.printer.shutdown();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
