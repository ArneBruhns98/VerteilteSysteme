package netcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Klasse NetcatServer
 */
public class NetcatServer implements Runnable {

    private final InputStream in;
    /** Datenfeld für den OutputStream */
    private final OutputStream out;
    /** Fatenfeld für den Port */
    private final int port;
    /** Datenfeld für den ServerSocket */
    private ServerSocket serverSocket;

    /**
     * Erzeugt ein Objekt der Klasse NetcatServer
     *
     * @param out ~ Einlesen eines OutputStream (Darf nicht null sein)
     * @param port ~ Einlesen einer ganzzahligen Zahl
     */
    public NetcatServer(InputStream in, OutputStream out, int port) throws IOException {
        super();
        this.in = in;
        this.out = out;
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() {
        System.err.println("Ich bin der Server");

        Socket socket = null;

        try{
            socket = this.serverSocket.accept();
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
            if(this.serverSocket != null) { this.serverSocket.close(); }
            assert socket != null;
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}