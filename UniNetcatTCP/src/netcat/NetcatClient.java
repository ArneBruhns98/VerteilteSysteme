package netcat;

import java.io.InputStream;
import java.net.Socket;

/**
 * Klasse NetcatClient
 */
public class NetcatClient implements Runnable {

    /** Datenfeld für den InputStream */
    private final InputStream in;
    /** Datenfeld für den Host*/
    private final String host;
    /** Fatenfeld für den Port*/
    private final int port;

    /**
     * Erzeugt ein Objekt der Klasse NetcatClient
     *
     * @param in ~ Einlesen eines InputStream (Darf nicht null sein)
     * @param port ~  Einlesen einer ganzahligen Zahl
     * @param host ~ Einlesen einer Zeichenkette
     */
    public NetcatClient(InputStream in, int port, String host){
        super();
        this.in = in;
        this.port = port;
        this.host = host;
    }

    @Override
    public void run() {
        System.err.println("Client sendet an: " + this.host + ", " + this.port);
        try (Socket socket = new Socket(this.host, this.port)){
            Transmitter transmitter = new Transmitter(socket);
            Reader reader = new Reader(transmitter, null, in);
            reader.run();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
