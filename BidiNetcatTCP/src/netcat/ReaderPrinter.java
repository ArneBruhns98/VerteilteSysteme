package netcat;

import java.io.InputStream;
import java.io.OutputStream;

public class ReaderPrinter implements Actor {

    /** Datenfeld ür den InputStream */
    private final InputStream in;
    /** Datenfeld für den Printer*/
    private Printer printer;
    /** Fatenfeld für einen Thread */
    private Thread readerThread;

    /**
     * Erzeugt ein neues Objekt der Klasse ReaderPrinter.
     *
     * @param in ~ Einlesen eines InputStreams
     * @param out ~ Einlesen eines OutputStreams
     */
    public ReaderPrinter(InputStream in, OutputStream out) {
        this.in = in;
        printer = new Printer(out);
    }

    /**
     *
     * @param transceiver
     */
    public void run(Transceiver transceiver) {
        this.readerThread = new Thread(new Reader(transceiver.getTransmitter(), this, this.in));
        this.readerThread.start();
    }

    @Override
    public void tell(String message, Actor sender) {
        this.printer.tell(message, sender);
    }

    @Override
    public void shutdown() {
        this.printer.shutdown();
    }

    /**
     * Wartet, bis der Thread sich beendet.
     *
     * @throws InterruptedException
     */
    public void join() throws InterruptedException {
        this.readerThread.join();
    }
}

