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
     * @param message          acceptable messages: "stop" + playback frame, "load" + track identifier,
     *                        "ip" + ip address, "play" + track identifier, "introduction" + identifier.
     *                         messages are formatted as timestamp;command;additionalInfo
     * @throws IOException
     */
    public void multicast(String message) throws IOException {
        socket = new DatagramSocket();
        group = InetAddress.getByName("228.5.6.7");
        buffer = message.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 6789);
        socket.send(packet);
        socket.close();
    }
}
