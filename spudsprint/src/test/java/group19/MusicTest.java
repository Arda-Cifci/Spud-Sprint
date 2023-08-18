package group19;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.BufferedInputStream;
import java.io.InputStream;

public class MusicTest {
    private AudioInputStream audioInput;
    private Clip clip;

    @Test
    public void testPlayMusic() {
        try {
            boolean testIfExists = false;

            InputStream audio = getClass().getResourceAsStream("/background_music.wav");
            InputStream bufferedIn = new BufferedInputStream(audio);
            audioInput = AudioSystem.getAudioInputStream(bufferedIn);

            clip = AudioSystem.getClip();
            clip.open(audioInput);

            if(clip.isOpen()){
                testIfExists = true;
            }

            assertTrue(testIfExists);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
