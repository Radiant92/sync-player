import Audio.AudioPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("moto");
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.loadTrack(new File("C:/Users/Käyttäjä/Desktop/Distribuutti/sync-player/samplemusic/higher.wav").getAbsoluteFile());
        audioPlayer.play();
        String command;
        boolean isActive = true;
        while (isActive) {
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
                case "exit":
                    isActive = false;
                    break;

            }
        }
    }
}
