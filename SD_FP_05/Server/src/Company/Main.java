package Company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static int sumOfRoundTripTimes = 0;
    public static List<Client> clientList;
    private static DatagramSocket multicastSocket = null;
    private static int roundTripTime = 0;

    public static void main (String[] args) throws IOException, InterruptedException {
        multicastSocket = new DatagramSocket(4445);
        AwaitResponseThread responseThread;
        roundTripTime = calculateRoundTrip();

        ClientThread clientThread = new ClientThread();
        clientThread.start();

        responseThread = new AwaitResponseThread();
        responseThread.start();

        sendDataTimeRequest();
        Thread.sleep(10000);
        updateDates();
        Thread.sleep(10000);

        responseThread.interrupt();
        clientThread.interrupt();

        System.exit(0);
    }

    public static int calculateRoundTrip() throws InterruptedException, IOException {
        Thread.sleep(10000);

        int countPacketsLosted = 0;

        for (int i = 0; i < 10; i++) {
            CalculateRoundTripThread tripThread = new CalculateRoundTripThread(multicastSocket);
            tripThread.start();
            Thread.sleep(10000);

            if (tripThread.isAlive()) {
                tripThread.interrupt();
                countPacketsLosted++;
            }
        }

        System.out.println("RTT: " + (sumOfRoundTripTimes / 10) + "ms!");
        System.out.println("Packages losted: " + countPacketsLosted);

        return (sumOfRoundTripTimes / 10);
    }

    public static void sendDataTimeRequest() throws InterruptedException {
        byte[] buf = new byte[256];
        String message = "Send me your data!";
        buf = message.getBytes();
        Thread.sleep(5000);
        InetAddress group = null;

        try {
            group = InetAddress.getByName("230.0.0.1");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        DatagramPacket packet;
        packet = new DatagramPacket(buf, buf.length, group, 4446);
        DatagramPacket packetServer = new DatagramPacket(buf, buf.length, group, 4449);

        try{
            multicastSocket.send(packet);
            multicastSocket.send(packetServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateDates() {
        System.out.println("Size: " + clientList.size());

        long sum = 0;

        for (int i = 0; i < clientList.size(); i++) {
            Client client = clientList.get(i);
            sum += client.getDate().getTime();

            System.out.println("Client " + i + ": " + client.getPort());
        }

        long avg = sum / clientList.size();
        long finalTime = avg + roundTripTime;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(finalTime);

        for (int i = 0; i < clientList.size(); i++) {
            sendNewDate(formatter.format(date), clientList.get(i).getAddress(), clientList.get(i).getPort());
        }
    }

    public static void sendNewDate(String date, String address, int port) {
        byte[] buf = new byte[256];
        buf = date.getBytes();

        try {
            InetAddress group = InetAddress.getByName(address);
            DatagramPacket packet;
            packet = new DatagramPacket(buf, buf.length, group, port);
            multicastSocket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
