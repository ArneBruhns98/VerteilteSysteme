package netcat;

import java.io.InputStream;
import java.io.OutputStream;

public class ReaderPrinter implements Actor {

    private final InputStream in;
    private Reader reader;
    private Printer printer;

    public ReaderPrinter(InputStream in, OutputStream out) {
        this.in = in;
        printer = new Printer(out);
    }

    public void run(Transceiver transceiver) {
        new Thread(reader = new Reader(transceiver.getTransmitter(), this, this.in)).start();
    }

    @Override
    public void tell(String message, Actor sender) {
        this.printer.tell(message, sender);
    }

    @Override
    public void shutdown() { this.printer.shutdown();}
}

