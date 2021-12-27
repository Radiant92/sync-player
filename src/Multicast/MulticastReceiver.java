package Multicast;

import java.io.IOException;
import java.net.*;

public class MulticastReceiver extends Thread {
    protected MulticastSocket socket;
    protected byte[] buffer = new byte[256];

    private InetAddress mcastaddr;
    private NetworkInterface netif;
    private InetSocketAddress group;

    @Override
    public void run() {
        System.out.println("multicast receiver running");
        try {
            socket = new MulticastSocket(4445);
            mcastaddr = InetAddress.getByName("228.5.6.7");

            socket.joinGroup(mcastaddr);

            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(datagramPacket);
                String received = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                System.out.println("received: " + received);
                if (received.equals("end")) {
                    break;
                }
            }
            closeConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() throws IOException {
        socket.leaveGroup(mcastaddr);
        socket.close();
        System.out.println("Socket closed");
    }

}
