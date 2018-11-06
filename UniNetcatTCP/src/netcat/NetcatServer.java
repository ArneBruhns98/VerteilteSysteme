package netcat;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Klasse NetcatServer
 */
public class NetcatServer implements Runnable {

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
    public NetcatServer(OutputStream out, int port){
        super();
        this.out = out;
        this.port = port;
    }

    @Override
    public void run() {
        System.err.println("Server empfängt Nachrichten vom Port: " + this.port);
        try(
                ServerSocket serverSocket = new ServerSocket(this.port);
                Socket socket = serverSocket.accept()
        ){
            Printer printer= new Printer(out);
            Receiver receiver = new Receiver(socket, printer);
            receiver.run();
        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
