package netcat;

/**
 * Klasse Receiver
 */
public class Receiver implements Runnable{

    /** Datenfeld für die maximale Größe einer Nachricht */
    private static final int MAXBYTES = 1024;
    /** Datenfeld für den Port */
    private final int port;
    /** Datenfeld für den Printer */
    private final Printer printer;
    /** Datenfeld für die Nachricjht */
    String message;

    /**
     * Erzeugt ein Objekt der Klasse Receiver
     *
     * @param port ~ Einlesen einer ganzzahligen Zahl (Darf nicht null sein)
     * @param printer ~ Einlesen eines Printers (Darf nicht null sein)
     */
    public Receiver(int port, Printer printer){
       this.port = port;
       this.printer = printer;
    }

    @Override
    public void run() {
        try(UDPSocket socket = new UDPSocket(port)){
            while(!(message = socket.receive(MAXBYTES)).equals("\u0004")) {
                printer.tell(message, null);
            }
            printer.shutdown();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
