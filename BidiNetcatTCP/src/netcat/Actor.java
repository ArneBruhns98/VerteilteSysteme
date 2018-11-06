package netcat;

import java.io.IOException;
import java.net.SocketException;

/**
 * Interface Actor
 */
public interface Actor {

    /**
     * Leitet eine Nachricht weiter
     *
     * @param message ~ Einlesen eines Strings
     * @param sender ~ Einlesen eines Senders
     * @throws SocketException
     */
    void tell(String message, Actor sender) throws SocketException;

    /**
     * Schaltet jeweilige Klassen aus
     * @throws IOException
     */
    void shutdown() throws IOException;

}
