import javax.sound.sampled.LineEvent;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameManager {
    Image background = Loader.getImage("background.gif");
    ArrayList<Enemy> arrEnemy;
    ArrayList<Money> arrMoney;
    ArrayList<Bullet> arrBullet;
    Plane plane;
    int count;
    void initGame() {
        Loader.playAudio("background.wav");
        plane = new Plane();
        arrMoney = new ArrayList<>();
        arrEnemy = new ArrayList<>();
        arrBullet = new ArrayList<>();
        initEnemy();
    }

    void initEnemy() {
        for (int i = 0; i < 5; i++) {
            arrEnemy.add(new Enemy());
        }
    }

    void draw(Graphics2D g2d) {
        g2d.drawImage(background, 0, 0,
                MyFrame.W_FRAME, MyFrame.H_FRAME, null);

        for (Money m : arrMoney) {
            m.draw(g2d);
        }
        for (Enemy c : arrEnemy) {
            c.draw(g2d);
        }
        for (Bullet b : arrBullet) {
            b.draw(g2d);
        }
        plane.draw(g2d);
    }

    void AI() {
        count++;
        if (count >= 500) {
            initEnemy();
            count = 0;
        }

        for (int i = arrEnemy.size() - 1; i >= 0; i--) {
            arrEnemy.get(i).move();
            arrEnemy.get(i).createMoney(arrMoney);

            Rectangle r = arrEnemy.get(i).getRect().intersection(plane.getRect());
            if (r.isEmpty() == false) {
                boolean checkdie = plane.die();
                if (checkdie == true) {
                    int result = JOptionPane.showConfirmDialog(null,
                            "Do you want to replay?",
                            "Game over",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        initGame();
                    } else {
                        System.exit(0);
                    }
                }
            }
            for (int j = 0; j < arrBullet.size(); j++) {
                Rectangle rect = arrEnemy.get(i).getRect().intersection(arrBullet.get(j).getRect());
                if (rect.isEmpty() == false) {                                                                          //Giết enemy
                    arrEnemy.remove(i);
                    arrBullet.remove(j);
                    plane.score += 50;
                    Loader.playAudio("Explosion.wav");
                    break;
                }
            }
        }
        for (int i = arrMoney.size() - 1; i >= 0; i--) {
            boolean check = arrMoney.get(i).move();                                                                     //Xoá xu ngoài tầm
            if (check == true) {
                arrMoney.remove(i);
            } else {
                Rectangle rect = arrMoney.get(i).getRect().intersection(plane.getRect());
                if (rect.isEmpty() == false) {                                                                          //Nhặt xu
                    arrMoney.remove(i);
                    plane.score += 5;
                    Loader.playAudio("xu.wav");
                }
            }
        }
        for (int i = arrBullet.size() - 1; i >= 0; i--) {                                                               //Xoá đạn ngoài tầm
            boolean check = arrBullet.get(i).move();
            if (check == true) {
                arrBullet.remove(i);
            }
        }
    }
}
