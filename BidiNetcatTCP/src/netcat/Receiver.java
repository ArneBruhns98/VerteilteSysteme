package netcat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Klasse Receiver
 */
public class Receiver implements Runnable{

    /** Datenfeld für den Printer */
    private final ReaderPrinter readerPrinter;
    /** Datenfeld für die Nachricht */
    String message;
    /** Datenfeld für den UDPSocket */
    private final Socket socket;

    /**
     * Erzeugt ein Objekt der Klasse Receiver
     *
     * @param printer ~ Einlesen eines Printers (Darf nicht null sein)
     */
    public Receiver(ReaderPrinter printer, Socket socket){
       this.readerPrinter = printer;
       this.socket = socket;
    }

    @Override
    public void run() {
        try{
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while(!(message = input.readLine()).equals("\u0004")) {
                readerPrinter.tell(message, null);
            }
            this.readerPrinter.shutdown();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
