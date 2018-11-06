package netcat;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Klasse NetcatServer
 */
public class NetcatServer implements Runnable {

    private final InputStream in;
    /** Datenfeld für den OutputStream */
    private final OutputStream out;
    /** Fatenfeld für den Port*/
    private final int port;

    /**
     * Erzeugt ein Objekt der Klasse NetcatServer
     *
     * @param out ~ Einlesen eines OutputStream (Darf nicht null sein)
     * @param port ~ Einlesen einer ganzzahligen Zahl
     */
    public NetcatServer(InputStream in, OutputStream out, int port){
        super();
        this.in = in;
        this.out = out;
        this.port = port;
    }

    @Override
    public void run() {
        System.err.println("Ich bin der Server");

        try {

            UDPSocket socket = new UDPSocket(this.port);

            Transceiver transceiver = new Transceiver(socket);
            ReaderPrinter readerPrinter = new ReaderPrinter(this.in, this.out);

            transceiver.run(readerPrinter);
            readerPrinter.run(transceiver);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
