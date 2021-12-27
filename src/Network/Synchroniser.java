package Network;

public class Synchroniser extends Thread{
    private AddressBook addressBook;
    private long timestamp;
    private boolean isRunning;

    public Synchroniser(AddressBook addressBook) {
        this.addressBook = addressBook;
        this.timestamp = -1l;
    }

    @Override
    public void run(){
        System.out.println("synchroniser running");
        while (isRunning) {
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (Node node : addressBook.getAllNodes()) {
                //todo: add time calculation:
                long time = 0l;
                node.addTime(time);
            }
        }
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long time) {
        timestamp = time;
    }
    public void closeSyncronisation(){
        isRunning = false;
    }

}
