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
        this.transmitter = transmitter;
        this.sender = sender;
        this.in = in;
        this.run();
    }

    @Override
    public void run() {
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while((this.line = br.readLine()) != null){
                this.transmitter.tell(this.line, this.sender);
            }
            this.transmitter.tell("\u0004", sender);
            //this.transmitter.shutdown();
            this.in.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}


