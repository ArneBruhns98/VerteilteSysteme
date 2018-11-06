package netcat;

import java.io.IOException;
import java.net.Socket;

public class TCPSocket implements AutoCloseable{

    private final Socket socket;

    public TCPSocket(Socket socket){
       this.socket = socket;
    }

    public TCPSocket(String host, int port) throws IOException {
        socket = new Socket(host, port);
    }


    @Override
    public void close() throws Exception {
        socket.close();
    }
}
