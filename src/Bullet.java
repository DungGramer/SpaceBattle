import java.awt.*;

public class Bullet {
    int x;
    int y;
    Image image = Loader.getImage("bullet.png");

    public Bullet(int x, int y) {
        this.x = x - image.getWidth(null) / 2;
        this.y = y;
    }
    void draw(Graphics2D g2d) {
        g2d.drawImage(image, x, y, null);
    }
    boolean move() {
        y -= 10;
        return y < -image.getHeight(null);
    }
    Rectangle getRect(){
        Rectangle rect = new Rectangle(x, y,
                image.getWidth(null),
                image.getHeight(null));
        return rect;
    }
}
