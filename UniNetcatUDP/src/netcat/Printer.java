package netcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Klasse Printer
 */
public class Printer implements Actor {

    /** Datenfeld für den OutputStream */
    private final OutputStream out;

    /**
     * Erzeugt ein Objekt der Klasse Printer
     * @param out
     */
    public Printer(OutputStream out){
        this.out = out;
    }

    @Override
    public void tell(String message, Actor sender) {
        try {
            out.write(message.concat("\n").getBytes());
            out.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void shutdown() throws IOException {
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
