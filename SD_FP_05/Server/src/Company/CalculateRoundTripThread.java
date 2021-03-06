package Company;

import java.io.IOException;
import java.net.*;

public class CalculateRoundTripThread extends Thread{
    private DatagramSocket multicastSocket;

    public CalculateRoundTripThread(DatagramSocket multicastSocket) {
        this.multicastSocket = multicastSocket;
    }

    @Override
    public void run() {
        super.run();

        try {
            byte[] buf = new byte[256];
            String message = "Ping!";
            buf = message.getBytes();
            InetAddress group = null;

            group = InetAddress.getByName("230.0.0.1");
            DatagramPacket packet;
            packet = new DatagramPacket(buf, buf.length, group, 4446);

            long startTime = System.currentTimeMillis();
            multicastSocket.send(packet);

            group = InetAddress.getByName("230.0.0.1");
            MulticastSocket socket = new MulticastSocket(4448);
            socket.joinGroup(group);

            buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            long endTime = System.currentTimeMillis();
            Main.sumOfRoundTripTimes += (endTime - startTime);

            socket.leaveGroup(group);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
