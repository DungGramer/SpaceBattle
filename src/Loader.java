import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Loader {
    public static Image getImage(String name) {                                                                         //Load ảnh
        Image img = new ImageIcon(
                new Loader().getClass()
                        .getResource("/Data/" + name)
        ).getImage();
        return img;
    }
    public static void playAudio(String name) {                                                                         //Load âm thanh
        try {
            File f = new File("src/Data/" + name);
            AudioInputStream stream = AudioSystem.getAudioInputStream(f);
            Clip c = AudioSystem.getClip();
            c.open(stream);
            c.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
