package netcat;

import java.io.InputStream;

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
        this.in = in;
        this.port = port;
        this.host = host;
    }

    @Override
    public void run() {
        System.err.println("Client sendet an: " + this.host + ", " + this.port);
        Transmitter transmitter = new Transmitter(this.host, this.port);
        Reader reader = new Reader(transmitter, null, in);
        reader.run();
    }
}
