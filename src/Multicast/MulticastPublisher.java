package Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastPublisher {
    private DatagramSocket socket;
    private InetAddress group;
    private byte[] buffer;

    /**
     * used to introduce this node to others in the network and for stop & load commands which do not require exact
     * synchronization.
     * @param message          acceptable messages: "stop" + playback frame, "load" + track identifier, "ip" + ip address
     * @param lamportTimestamp to ensure events take place in correct order, since multiple nodes can simultaneously
     *                         give out commands.
     * @throws IOException
     */
    public void multicast(String message, long lamportTimestamp) throws IOException {
        socket = new DatagramSocket();
        group = InetAddress.getByName("230.0.0.0");
        buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 4446);
        socket.send(packet);
        socket.close();
    }
}
