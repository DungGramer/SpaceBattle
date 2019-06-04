import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Enemy {
    int x;
    int y;
    Image[] images = { Loader.getImage("enemy.png") };
    int index = 0;
    int speedX = 1;
    int speedY;
    int count;
    int countCreate;

    public Enemy() {
        y = -images[index].getHeight(null);
        Random rd = new Random();
        x = MyFrame.W_FRAME - images[index].getWidth(null);
        x = rd.nextInt(x);
        speedY = rd.nextInt(5) + 1;
    }
    void draw(Graphics2D g2d) {
        g2d.drawImage(images[index], x, y, null);
    }

    void move() {
        count++;
        if (count >= 5) {
            index++;
            if (index >= images.length) {
                index = 0;
            }
            count = 0;
        }
        y += speedY;
        x += speedX * speedY;
        if (y > MyFrame.H_FRAME) {
            y = -images[index].getHeight(null);
        }
        if (x <= 0 ||
                x >= MyFrame.W_FRAME - images[index].getWidth(null)) {
            speedX = speedX * -1;
        }
    }
    void createMoney(ArrayList<Money> arrMoney) {
        countCreate++;
        if (countCreate < 100){
            return;
        }
        countCreate = 0;
        int xT = x + images[index].getWidth(null) / 2;
        int yT = y + images[index].getHeight(null);
        arrMoney.add(new Money(xT, yT));
    }
    Rectangle getRect(){
        Rectangle rect = new Rectangle(x, y,
                images[index].getWidth(null),
                images[index].getHeight(null));
        return rect;
    }
}
