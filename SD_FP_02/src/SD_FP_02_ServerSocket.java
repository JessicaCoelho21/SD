import java.net.*;
import java.io.*;

/**
 * Exercício 3.2 de Sockets TCP Java 2 - Socket do lado do servidor
 */
public class SD_FP_02_ServerSocket {
    public static void main (String[] args) throws IOException{
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        PrintWriter out = null;
        SD_FP_02_KnockKnockProtocol knock = new SD_FP_02_KnockKnockProtocol();
        String knockResponse = "";
        BufferedReader in = null;

        try {
            serverSocket = new ServerSocket(7);
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen to port or listening for a connection");
            System.exit(-1);
        }

        try{
            //Iniciar processamento de pedidos
            clientSocket = serverSocket.accept();
            System.out.println("Chegou um cliente! IP Cliente: " + clientSocket.getInetAddress());
        } catch (IOException e){
            System.out.println("Accept failed.");
            System.exit(-1);
        }

        try{
            //Para se obter um objeto do tipo PrintWriter
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            //Para se obter um objeto do tipo BufferedReader
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Error");
        }

        //Socket input
        String serverOut = null;
        String clientIn = null;

        serverOut = knock.processInput(null);
        out.println(serverOut);

        //Respostas do cliente
        try{
            while((clientIn = in.readLine()) != null){
                System.out.println("Client: " + clientIn);
                serverOut = knock.processInput((clientIn));
                System.out.println("Server: " + serverOut);
                out.println(serverOut);

                if(serverOut.equals("Bye.")){
                    break;
                }
            }
        }catch(IOException e) {
                System.out.println("Error");
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}

