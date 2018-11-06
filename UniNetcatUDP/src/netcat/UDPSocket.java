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

    /** Datenfeld für den Host */
    private final String host;
    /** Datenfeld für den Port */
    private final int port;
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
        this.host = host;
        this.port = port;
        this.socket = new DatagramSocket();
    }

    /**
     * Erzeugt ein Objekt der Klasse UDPSocket
     *
     * param port ~ Einlesen einer ganzzahligen Zahl (Darf nicht null sein)
     * @throws SocketException
     */
    public UDPSocket(int port) throws SocketException {
        this.host = null;
        this.port = port;
        this.socket = new DatagramSocket(this.port);
    }

    /**
     * Sendet eine Nachricht an einen Server weiter
     *
     * @param message ~ Einlesen eines Strings
     * @throws IOException
     */
    public void send(String message) throws IOException {
        InetSocketAddress addr = new InetSocketAddress(host, port);
        byte data[] = message.getBytes();
        DatagramPacket packetOut = new DatagramPacket(data, data.length, addr);
        socket.send(packetOut);
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
        return new String(packetIn.getData(), 0, packetIn.getLength());
    }

    @Override
    public void close() {
        socket.close();
    }
}
