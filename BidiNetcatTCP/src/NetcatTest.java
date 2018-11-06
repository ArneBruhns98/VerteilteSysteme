import netcat.NetcatClient;
import netcat.NetcatServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class NetcatTest extends BidiNetcatTest{
    @Override
    protected Thread createClient(InputStream input, OutputStream output) {
        return new Thread(new NetcatClient(input, output,5555, "localhost"));
    }

    @Override
    protected Thread createServer(InputStream input, OutputStream output) throws IOException {
        return new Thread(new NetcatServer(input, output, 5555));
    }
}