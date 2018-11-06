package netcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Klasse NetcatClient
 */
public class NetcatClient implements Runnable {

    /** Datenfeld für den InputStream */
    private final InputStream in;
    /** Datenfel dfür den OutputStream */
    private final OutputStream out;
    /** Datenfeld für den Host */
    private final String host;
    /** Fatenfeld für den Port */
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

        Socket socket = null;

        try{
            socket = new Socket(this.host, this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Transceiver transceiver = new Transceiver(socket);
        ReaderPrinter readerPrinter = new ReaderPrinter(this.in, this.out);

        readerPrinter.run(transceiver);
        transceiver.run(readerPrinter);

        try {
            readerPrinter.join();
            transceiver.join();
            assert socket != null;
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
