import netcat.*;

import java.io.IOException;

/**
 * Klasse Netcat
 */
public class Netcat {

    /**
     * Methode zur Steuerung des Netcat
     *
     * @param args ~ Einlesen von Argumenten
     */
    public static void main(String[] args) throws IOException {
        if(args.length != 2){
            System.err.println("Für den Client-Modus: Netcat <host> <port>");
            System.err.println("Für den Server-Modus: Netcat -l <port>");
            return;
        }

        if(args[0].equals("-l")) {
            NetcatServer nc = new NetcatServer(System.in, System.out, Integer.parseInt(args[1]));
            nc.run();
        } else {
            NetcatClient nc = new NetcatClient(System.in, System.out, Integer.parseInt(args[1]), args[0]);
            nc.run();
        }

        return;
    }
}
