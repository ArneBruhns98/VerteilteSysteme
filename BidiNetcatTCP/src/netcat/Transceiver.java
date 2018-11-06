package netcat;

import java.io.IOException;
import java.net.Socket;

/**
 * Klasse Transceiver
 */
public class Transceiver implements Actor {

    /** Datenfeld für den Transmitter */
    private Transmitter transmitter;
    /** Datenfeld für den Socket*/
    private Socket socket;

    private Thread receiverThread;

    /**
     * Erzeugt ein Objekt der Klasse Transceiver
     *
     * @param socket ~ Einlesen eines Objektes der Klasse UDPSocket (Darf nicht null sein)
     */
    public Transceiver(Socket socket){
        this.socket = socket;
        this.transmitter = new Transmitter(socket);
    }

    /**
     * Gibt den Transmitter zurück
     *
     * @return Transmitter
     */
    public Transmitter getTransmitter(){
        return this.transmitter;
    }

    /**
     * Startet den Receiver
     *
     * @param readerPrinter ~ Einlesen eines Objektes der Klasse ReaderPrinter (Darf nicht null sein)
     */
    public void run(ReaderPrinter readerPrinter) {
        receiverThread = new Thread(new Receiver(readerPrinter, this.socket));
        receiverThread.start();
    }

    @Override
    public void tell(String message, Actor sender) {
        this.transmitter.tell(message, sender);
    }

    @Override
    public void shutdown() throws IOException {
        this.transmitter.shutdown();
    }

    /**
     * Wartet, bis der Thread sich beendet.
     *
     * @throws InterruptedException
     */
    public void join() throws InterruptedException {
        this.receiverThread.join();
    }
}
