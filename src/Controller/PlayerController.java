package Controller;

import Audio.AudioPlayer;
import Multicast.MulticastPublisher;
import Multicast.MulticastReceiver;
import Network.AddressBook;
import Network.Synchroniser;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class PlayerController {

    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException, InterruptedException {
        MulticastPublisher multicastPublisher = new MulticastPublisher();
        MulticastReceiver receiver = new MulticastReceiver();

        AddressBook addressBook = new AddressBook();
        Synchroniser synchroniser = new Synchroniser(addressBook);
        synchroniser.start();

        //own address
        String address = "";
        multicastPublisher.multicast(synchroniser.getTimestamp() + ";introduction;" + address);
        receiver.start();


        Scanner scanner = new Scanner(System.in);
        HashMap<String, File> files = new HashMap<String, File>();
        files.put("1", new File("C:/Users/Käyttäjä/Desktop/Distribuutti/sync-player/samplemusic/higher.wav").getAbsoluteFile());
        files.put("2", new File("C:/Users/Käyttäjä/Desktop/Distribuutti/sync-player/samplemusic/epic.wav").getAbsoluteFile());
        AudioPlayer audioPlayer = new AudioPlayer();

        long jumpToFrame;
        String trackId;
        String command;
        boolean isActive = true;
        while (isActive) {
            System.out.println("Enter command");
            command = scanner.nextLine();
            switch (command) {
                case "play":
                    audioPlayer.play();
                    break;
                case "stop":
                    audioPlayer.stop();
                    break;
                case "pause":
                    audioPlayer.pause();
                    break;
                case "jump":
                    System.out.println("Enter frame in ms");
                    jumpToFrame = Long.parseLong(scanner.nextLine());
                    audioPlayer.jump(jumpToFrame);
                    break;
                case "frame":
                    System.out.println(audioPlayer.getFrame());
                    break;
                case "load":
                    System.out.println("Enter track id number");
                    trackId = scanner.nextLine();
                    audioPlayer.loadTrack(files.get(trackId));
                    break;
                case "exit":
                    isActive = false;
                    synchroniser.closeSyncronisation();
                    receiver.closeConnection();
                    break;
                case "intro":
                    multicastPublisher.multicast("hello there");
                    break;
            }
        }
    }
}
