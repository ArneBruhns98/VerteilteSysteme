package test;

import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(Theories.class)
public abstract class BidiNetcatTest {

    @DataPoint public static String input1 = "Hallo\n";
    @DataPoint public static String input2 = "Hallo Hallo\n";
    @DataPoint public static String input3 = "Hallo Hallo Hallo\n";

    private Thread clientThread;
    private Thread serverThread;

    protected abstract Thread createClient(InputStream input, OutputStream output);
    protected abstract Thread createServer(InputStream input, OutputStream output);

    @Theory
    public void test(String input) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(byteOutput);

        serverThread = createServer(inputStream, outputStream);
        clientThread = createClient(inputStream, outputStream);

        serverThread.start();
        clientThread.start();
        clientThread.join();
        serverThread.join();

        assertEquals(input, byteOutput.toString());
    }
}