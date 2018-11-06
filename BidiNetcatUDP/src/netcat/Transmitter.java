package netcat;

/**
 * Klasse Transmitter
 */
public class Transmitter implements Actor {

    /** Datenfeld für den UDPSocket */
    UDPSocket socket;

    /**
     * Erzeugt ein Objekt der Klasse Transmitter
     *
     */
    public Transmitter(UDPSocket socket){
       this.socket = socket;
    }

    @Override
    public void tell(String message, Actor sender) {
        try {
            this.socket.send(message);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() { }

}
