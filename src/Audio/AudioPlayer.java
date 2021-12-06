package Audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private AudioInputStream audioInputStream;
    private Clip clip;
    private String state = "stopped";

    public AudioPlayer() {
    }

    public void loadTrack(File file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (this.clip != null) {
            this.clip.stop();
            state = "stopped";
        }
        this.audioInputStream = AudioSystem.getAudioInputStream(file);
        this.clip = AudioSystem.getClip();
        this.clip.open(this.audioInputStream);
    }

    public long getFrame() {
        return this.clip.getMicrosecondPosition();
    }

    public void pause() {
        clip.stop();
        state = "stopped";
    }

    public void stop() {
        clip.stop();
        clip.close();
        state = "stopped";
    }

    public void play() {
        if (clip != null) {
            clip.start();
            state = "playing";
        }
    }

    public void jump(long frame) throws LineUnavailableException, IOException {
        clip.stop();
        clip.setMicrosecondPosition(frame);
        play();
        state = "playing";
    }

    public String getState() {
        return state;
    }
}
