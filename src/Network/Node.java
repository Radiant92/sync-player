package Network;

import java.util.LinkedList;
import java.util.Queue;

public class Node {
    private String identifier;
    private int queueMaxLength;

    private Long avarageOffset;
    private Queue<Long> queue;
    private long totalTime;

    /**
     * Node reprecents another machine in the network that can be communicated with.
     *
     * @param identifier how the system identifies the remote machine.
     * @param queueLength how many offset calculations are used to generate the average offset between a message and its
     *                   response.
     */
    public Node(String identifier, int queueLength) {
        this.identifier = identifier;
        this.queueMaxLength = queueLength;

        this.avarageOffset = 0l;
        this.totalTime = 0l;
        this.queue = new LinkedList<>();
    }

    public String getIdentifier() {
        return identifier;
    }

    public Long getAvarageOffset() {
        return avarageOffset;
    }

    /**
     * uses times it takes to send a message and receive a response between the local machine and the remote node to
     * calculate the average offset by maintaining a list of offsets.
     * @param localTime time it takes to send and receive a response to the message.
     * @param remoteTime time it takes for the remote node to receive and send response to the message.
     */
    public void addTime(long localTime, long remoteTime) {
        long offset = (localTime - remoteTime) / 2;
        totalTime += offset;
        queue.add(offset);
        if (queue.size() < queueMaxLength) {
            totalTime -= queue.poll();
        }
        avarageOffset = totalTime / queue.size();
    }
}
