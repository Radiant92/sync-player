package Audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private AudioInputStream audioInputStream;
    private Clip clip;

    public AudioPlayer() {
    }

    public void loadTrack(File file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        if (this.clip != null) {
            this.clip.stop();
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
    }

    public void stop() {
        clip.stop();
        clip.close();
    }

    public void play() {
        if (clip != null) {
            clip.start();
        }
    }

    public void jump(long frame) throws LineUnavailableException, IOException {
        clip.stop();
        clip.setMicrosecondPosition(frame);
        play();
    }
}
