package netcat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * Klasse UDPSocket
 */
public class UDPSocket implements AutoCloseable {


    /** Datenfeld für den Socket */
    private DatagramSocket socket;

    /**
     * Erzeugt ein Objekt der Klasse UDPSocket
     *
     * @param host ~ Einlesen eines Strings (Darf nicht null sein)
     * @param port ~ Einlesen einer ganzzahligen Zahl (Darf nicht null sein)
     * @throws SocketException
     */
    public UDPSocket(String host, int port) throws SocketException {
        this.socket = new DatagramSocket();
        InetSocketAddress addr = new InetSocketAddress(host, port);
        socket.connect(addr);
    }

    /**
     * Erzeugt ein Objekt der Klasse UDPSocket
     *
     * param port ~ Einlesen einer ganzzahligen Zahl (Darf nicht null sein)
     * @throws SocketException
     */
    public UDPSocket(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    /**
     * Sendet eine Nachricht an einen Server weiter
     *
     * @param message ~ Einlesen eines Strings
     * @throws IOException
     */
    public void send(String message) throws IOException, InterruptedException {
        byte data[] = message.getBytes();
        DatagramPacket packetOut = new DatagramPacket(data, data.length);
        if(socket.isConnected()) socket.send(packetOut);
        else System.err.println("!!!Es BESTEHT KEINE VERBINDUNG ZU EINEM PORT!!!");
        //while(!socket.isConnected()) Thread.sleep(1000);
        //socket.send(packetOut);
    }

    /**
     * Empfängt von einem Server eine Nachricht
     *
     * @param maxByte ~ Einlesen einer ganzzahligen Zahl
     * @return Nachricht vom Server
     * @throws IOException
     */
    public String receive(int maxByte) throws IOException {
        DatagramPacket packetIn = new DatagramPacket(new byte[maxByte], maxByte);
        socket.receive(packetIn);
        if(!socket.isConnected()) socket.connect(packetIn.getSocketAddress());
        return new String(packetIn.getData(), 0, packetIn.getLength());
    }

    @Override
    public void close() {
        socket.close();
    }
}
