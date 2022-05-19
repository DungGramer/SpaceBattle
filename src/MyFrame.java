import javax.swing.*;
import java.awt.*;
public class MyFrame extends JFrame {

    GraphicsDevice gd = GraphicsEnvironment                                                                             // set size default
            .getLocalGraphicsEnvironment().getDefaultScreenDevice();
    static int W_FRAME;
    static int H_FRAME;
    {
        W_FRAME = gd.getDisplayMode().getWidth();
        H_FRAME = gd.getDisplayMode().getHeight();
    }

    public MyFrame() {
        setTitle("Space Battle");
        setSize(W_FRAME, H_FRAME);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        MyPanel p = new MyPanel();
        add(p);
    }
    public static void main(String[] args) {
        MyFrame f = new MyFrame();
        f.setVisible(true);
    }
}

