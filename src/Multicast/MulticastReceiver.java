package Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver extends Thread {
    protected MulticastSocket socket;
    protected byte[] buffer = new byte[256];

    private InetAddress group;

    @Override
    public void run() {
        try {
            socket = new MulticastSocket(4446);

            group = InetAddress.getByName("230.0.0.0");
            socket.joinGroup(group);

            while (true) {
                DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(datagramPacket);
                String received = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
                if (received.equals("end")) {
                    break;
                }
            }
            leaveGroupAndCloseConnection();
            System.out.println("Socket closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leaveGroupAndCloseConnection() throws IOException {
        if (group != null) {
            socket.leaveGroup(group);
        }
        socket.close();
    }
}
