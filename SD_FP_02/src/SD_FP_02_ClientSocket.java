import java.io.*;
import java.net.*;

/**
 * Exercício 3.2 de Sockets TCP Java 2 - socket do lado do cliente
 */
public class SD_FP_02_ClientSocket {
    public static void main (String[] args) throws IOException{
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            //Usado para estabelecer a ligação
            echoSocket = new Socket("localhost", 7);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput = null;

        //O que o cliente recebe

        String echo;

        while ((echo = in.readLine()) != null) {
            System.out.println("Server: " + echo);

            if(echo.equals("Bye.")){
                break;
            }

            userInput = stdIn.readLine();

            //System.out.println("echo: " + stdIn.readLine());
            //mensagem enviada do cliente para o servidor
            if(userInput != null)
                out.println(userInput);
        }

        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}
