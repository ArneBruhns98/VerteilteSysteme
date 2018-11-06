package netcat;

/**
 * Klasse Receiver
 */
public class Receiver implements Runnable{

    /** Datenfeld für die maximale Größe einer Nachricht */
    private static final int MAXBYTES = 1024;
    /** Datenfeld für den Printer */
    private final ReaderPrinter readerPrinter;
    /** Datenfeld für die Nachricht */
    String message;
    /** Datenfeld für den UDPSocket */
    private UDPSocket socket;

    /**
     * Erzeugt ein Objekt der Klasse Receiver
     *
     * @param printer ~ Einlesen eines Printers (Darf nicht null sein)
     */
    public Receiver(ReaderPrinter printer, UDPSocket socket){
       this.readerPrinter = printer;
       this.socket = socket;
    }

    @Override
    public void run() {
        try{
            while(!(message = this.socket.receive(MAXBYTES)).equals("\u0004")) {
                this.readerPrinter.tell(message, null);
            }
            //System.out.println("!!!RECEIVER HAT EOT GESENDET!!!");
            this.readerPrinter.shutdown();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
