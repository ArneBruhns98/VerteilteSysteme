package netcat;

/**
 * Klasse Transceiver
 */
public class Transceiver implements Actor {

    /** Datenfeld für den Transmitter */
    private Transmitter transmitter;
    /** Datenfeld für den UDPSocket*/
    private UDPSocket socket;

    /**
     * Erzeugt ein Objekt der Klasse Transceiver
     *
     * @param socket ~ Einlesen eines Objektes der Klasse UDPSocket (Darf nicht null sein)
     */
    public Transceiver(UDPSocket socket){
        this.socket = socket;
        this.transmitter = new Transmitter(this.socket);
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
        new Thread(new Receiver(readerPrinter, this.socket)).start();
    }

    @Override
    public void tell(String message, Actor sender) {
        this.transmitter.tell(message, sender);
    }

    @Override
    public void shutdown() {
        this.transmitter.shutdown();
    }
}
