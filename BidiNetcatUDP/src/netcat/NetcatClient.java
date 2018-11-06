package netcat;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Klasse NetcatClient
 */
public class NetcatClient implements Runnable {

    /** Datenfeld für den InputStream */
    private final InputStream in;
    private final OutputStream out;
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
    public NetcatClient(InputStream in, OutputStream out, int port, String host){
        this.in = in;
        this.out = out;
        this.port = port;
        this.host = host;
    }

    @Override
    public void run() {
        System.err.println("Ich bin der Client");

        try {

            UDPSocket socket = new UDPSocket(this.host, this.port);

            Transceiver transceiver = new Transceiver(socket);
            ReaderPrinter readerPrinter = new ReaderPrinter(this.in, this.out);

            transceiver.run(readerPrinter);
            readerPrinter.run(transceiver);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}