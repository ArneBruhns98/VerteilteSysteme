package netcat;

import java.io.PrintWriter;
import java.net.Socket;

/**
 * Klasse Transmitter
 */
public class Transmitter implements Actor {

    /** Datenfeld für den Socket */
    private final Socket socket;

    /**
     * Erzeugt ein Objekt der Klasse Transmitter
     *
     * @param socket ~ Einlesen eines Sockets (Darf nicht null sein)
     */
    public Transmitter(Socket socket){
        this.socket = socket;
    }

    @Override
    public void tell(String message, Actor sender) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() { }

}
