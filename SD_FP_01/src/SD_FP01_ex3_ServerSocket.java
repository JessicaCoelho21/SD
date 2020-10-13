import java.net.*;
import java.io.*;
import java.util.Date;

public class SD_FP01_ex3_ServerSocket {
    public static void main(String[] args) {
        try {
            //Instancia o ServerSocket ouvindo a porta 12345
            ServerSocket server = new ServerSocket(12345);
            System.out.println("Server listening to port 12345");

            while(true) {
                //o método accept() bloqueia a execução até que o server receba um pedido de conexão
                Socket client = server.accept();
                System.out.println("Client connected to: " + client.getInetAddress().getHostAddress());
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

                out.flush();
                out.writeObject(new Date());
                out.close();
                client.close();
            }
        }
        catch(Exception e) {
            System.out.println("Erroe: " + e.getMessage());
        }
    }
}
