package test;

import netcat.NetcatClient;
import netcat.NetcatServer;

import java.io.InputStream;
import java.io.OutputStream;

public class NetcatTest extends UniNetcatTest {
    @Override
    protected Thread createClient(InputStream input) {
        return new Thread(new NetcatClient(input, 5555, "localhost"));
    }

    @Override
    protected Thread createServer(OutputStream output) {
        return new Thread(new NetcatServer(output, 5555));
    }
}