package netcat;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Klasse Reader
 */
public class Reader implements Runnable {

    /** Datenfeld für den Eingabestring */
    private String line;
    /** Datenfeld für den Transmitter */
    private final Actor transmitter;
    /** Datenfeld für den Sender */
    private final Actor sender;
    /** Datenfeld für den InpuStream */
    private final InputStream in;

    /**
     * Erzeugt ein Objekt der Klasse Reader
     *
     * @param transmitter ~ Einlesen eines Transmitters (Darf nicht null sein)
     * @param sender ~ Einlesen eines Senders
     * @param in ~ Einlesen eines InpuStreams (Draf nicht null sein)
     */
    public Reader(Actor transmitter, Actor sender, InputStream in) {
        super();
        this.transmitter = transmitter;
        this.sender = sender;
        this.in = in;
    }

    @Override
    public void run() {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while((line = br.readLine()) != null){
                transmitter.tell(line, sender);
            }
            in.close();
            transmitter.tell("\u0004", sender);
            //System.err.println("!!!ES WURDE EOT EINGEGEBEN!!!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}


