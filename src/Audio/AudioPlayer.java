package Audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {
    private AudioInputStream audioInputStream;
    private Clip clip;
    private long currentFrame;

    public AudioPlayer() {
    }

    public void loadTrack(File file) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        this.audioInputStream = AudioSystem.getAudioInputStream(file);
        this.clip = AudioSystem.getClip();
        this.clip.open(this.audioInputStream);
    }

    public long getFrame() {
        return this.clip.getMicrosecondPosition();
    }

    public void pause() {
        clip.stop();
        currentFrame = clip.getMicrosecondPosition();
    }

    public void stop() {
        currentFrame = 0l;
        clip.stop();
        clip.close();
    }

    public void play() {
        if (clip != null){
            clip.start();
        }
    }

    public void jump(long frame) {
        clip.setMicrosecondPosition(frame);
    }
}
