import java.net.*;
import java.io.*;
public class SD_FP01_ex3_ServerSocket {
    public static void main (String[] args) throws IOException{
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        PrintWriter out = null;

        try {
            serverSocket = new ServerSocket(7);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen to port or listening for a connection");
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            //mensagem enviada do cliente para o servidor
            out.println(inputLine);
        }

        out.close();
        in.close();
        serverSocket.close();
        clientSocket.close();
    }

}
