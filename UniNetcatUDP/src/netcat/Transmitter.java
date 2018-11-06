package netcat;

/**
 * Klasse Transmitter
 */
public class Transmitter implements Actor {

    /** Datenfeld für den Host */
    private final String host;
    /** Datenfeld für den Port */
    private final int port;

    /**
     * Erzeugt ein Objekt der Klasse Transmitter
     *
     * @param host ~ Einlesen eines Strings (Darf nicht null sein)
     * @param port ~ Einlesen einer ganzzahligen Zahl (Darf nicht null sein)
     */
    public Transmitter(String host, int port){
       this.host = host;
       this.port = port;
    }

    @Override
    public void tell(String message, Actor sender) {
        try(UDPSocket socket = new UDPSocket(host, port)) {
            socket.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() { System.exit(0); }

}
