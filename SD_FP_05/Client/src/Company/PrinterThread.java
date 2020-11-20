package Company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PrinterThread extends Thread{
    private MulticastSocket socket;
    private DatagramPacket packet;
    private InetAddress group;
    private SystemDate systemDate;
    private SimpleDateFormat formatter;

    private static final String SEND_DATA = "Send me your data!";
    private static final String SEND_PING = "Ping!";

    public PrinterThread() throws IOException {
        super("PrinterThread");

        this.socket = new MulticastSocket(4446);
        systemDate = new SystemDate();
        formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    }

    @Override
    public void run() {
        super.run();

        try {
            group = InetAddress.getByName("230.0.0.1");
            socket.joinGroup(group);
            byte[] buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            while (packet != null) {
                String recieved = new String(packet.getData());

                if (recieved.contains(SEND_DATA)) {
                    System.out.println("Request received 1: " + recieved);
                    sendDateTime();
                } else if (recieved.contains(SEND_PING)) {
                    System.out.println("Ping received 1: " + recieved);
                    sendPing();
                } else {
                    System.out.println("Set SystemDate 1: " + recieved);
                    systemDate.setDate(formatter.parse(recieved));

                    System.out.println("Get SystemDate 1: " + systemDate.getDate());
                    System.exit(0);
                }

                buf = new byte[256];
                packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
            }

            socket.leaveGroup(group);
            socket.close();
        } catch (IOException | InterruptedException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendDateTime() throws InterruptedException {
        byte[] buf = new byte[256];
        buf = formatter.format(systemDate.getDate()).getBytes();
        DatagramPacket packet;
        packet = new DatagramPacket(buf, buf.length, group, 4447);

        try {
            Thread.sleep(5);
            socket.send(packet);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendPing() throws InterruptedException {
        byte[] buf = new byte[256];
        buf = formatter.format(systemDate.getDate()).getBytes();
        DatagramPacket packet;
        packet = new DatagramPacket(buf, buf.length, group, 4448);

        try {
            Thread.sleep(5);
            socket.send(packet);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
