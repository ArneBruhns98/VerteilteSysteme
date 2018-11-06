import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Theories.class)
public abstract class BidiNetcatTest {

    @DataPoint public static String input1 = "Hallo\n";
    @DataPoint public static String input2 = "Hallo Hallo\n";
    @DataPoint public static String input3 = "Hallo Hallo Hallo\n";

    private Thread clientThread;
    private Thread serverThread;

    protected abstract Thread createClient(InputStream input, OutputStream output);
    protected abstract Thread createServer(InputStream input, OutputStream output) throws IOException;

    @Theory
    public void test(String input) throws Exception {
        ByteArrayInputStream inputStream1 = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream byteOutput1 = new ByteArrayOutputStream();
        PrintStream outputStream1 = new PrintStream(byteOutput1);

        ByteArrayInputStream inputStream2 = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream byteOutput2 = new ByteArrayOutputStream();
        PrintStream outputStream2 = new PrintStream(byteOutput2);

        serverThread = createServer(inputStream1, outputStream1);
        clientThread = createClient(inputStream2, outputStream2);

        serverThread.start();
        clientThread.start();
        clientThread.join();
        serverThread.join();

        assertEquals(input, byteOutput1.toString());
        assertEquals(input, byteOutput2.toString());
    }

}