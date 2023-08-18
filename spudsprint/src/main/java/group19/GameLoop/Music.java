package group19.GameLoop;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Able to play music and stop the music.
 */
public class Music {
    //Variables used
    private AudioInputStream audioInput;
    private Clip clip;
    private FloatControl volume;

    /*
     * Plays music by getting the music file in a stream and using the javax.sound.sampled.Clip library to play it.
     */
    public void playMusic() {
        try {
            InputStream audio = getClass().getResourceAsStream("/background_music.wav");
            InputStream bufferedIn = new BufferedInputStream(audio);
            audioInput = AudioSystem.getAudioInputStream(bufferedIn);

            clip = AudioSystem.getClip();
            clip.open(audioInput);

            volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-20);

            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Stops the music.
     */
    public void closeMusic() {
        clip.close();
    }
}