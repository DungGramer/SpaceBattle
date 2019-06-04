import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class MyPanel extends JPanel {
    GameManager manager = new GameManager();
    public MyPanel() {
            manager.initGame();
            Thread t = new Thread(run);
            t.start();
            addMouseMotionListener(motion);
            addMouseListener(click);
            // Ẩn chuột
            BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                    cursorImg, new Point(0, 0), "blank cursor");
            setCursor(blankCursor);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        manager.draw(g2d);
        }
    MouseListener click = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) { }
        @Override
        public void mousePressed(MouseEvent e) {
            manager.plane.fire(manager.arrBullet);
        }
        @Override
        public void mouseReleased(MouseEvent e) { }
        @Override
        public void mouseEntered(MouseEvent e) { }
        @Override
        public void mouseExited(MouseEvent e) { }
    };
    MouseMotionListener motion = new MouseMotionListener() {
        @Override
        public void mouseDragged(MouseEvent e) { }
        @Override
        public void mouseMoved(MouseEvent e) {
            manager.plane.move(e.getX(), e.getY());
        }
    };
    Runnable run = new Runnable() {
        @Override
        public void run() {
            while (true) {
                manager.AI();
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
}
