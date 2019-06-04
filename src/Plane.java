import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Plane {
    int x;
    int y;
    Image image = Loader.getImage("spaceship.png");
    int score;
    int hp;

    public Plane() {
        x = MyFrame.W_FRAME - image.getWidth(null);
        x /= 2;
        y = MyFrame.H_FRAME - image.getHeight(null) - 100;
        hp = 1;
    }
    boolean die() {
        hp--;
        x = MyFrame.W_FRAME - image.getWidth(null);
        x /= 2;
        y = MyFrame.H_FRAME - image.getHeight(null) - 100;
        return hp == 0;
    }
    void moveMouse() {
        Point p = new Point(x, y);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        // Search the devices for the one that draws the specified point.
        for (GraphicsDevice device : gs) {
            GraphicsConfiguration[] configurations = device.getConfigurations();
            for (GraphicsConfiguration config : configurations) {
                Rectangle bounds = config.getBounds();
                if (bounds.contains(p)) {
                    // Set point to screen coordinates.
                    Point b = bounds.getLocation();
                    Point s = new Point(p.x - b.x, p.y - b.y);
                    try {
                        Robot r = new Robot(device);
                        r.mouseMove(s.x, s.y);
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
        }
    }
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.drawImage(image, x, y, null);
        g2d.setColor(new Color(255, 255, 255));
        g2d.setFont(new Font("Calibri", Font.BOLD, 30));
        g2d.drawString(score + "", 20, 50);
    }
    void fire(ArrayList<Bullet> arrBullet) {
        int xB = x + image.getWidth(null) / 2;
        Bullet b = new Bullet(xB, y);
        arrBullet.add(b);
        Loader.playAudio("bullet.wav");
    }
    void move(int newX, int newY) {
        int xR = x;
        int yR = y;
        x = newX;
        y = newY;
        if (x <= 0 ||
                x >= MyFrame.W_FRAME - image.getWidth(null)) {
            x = xR;
        }
        if (y <= 0 ||
                y >= MyFrame.H_FRAME - image.getHeight(null)) {
            y = yR;
        }
    }
    Rectangle getRect() {
        Rectangle rect = new Rectangle(x, y,
                image.getWidth(null),
                image.getHeight(null));
        return rect;
    }
}
