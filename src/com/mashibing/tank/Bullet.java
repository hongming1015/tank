package com.mashibing.tank;

import com.sun.media.sound.SoftTuning;

import javax.sound.midi.Soundbank;
import java.awt.*;

/**
 * 子弹
 */
public class Bullet {

    //子弹位置
    private int x, y;

    //子弹宽和高
    private int width = 20, height = 20;

    //子弹速度
    private int speed = 10;

    //子弹方向
    private Dir dir;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    /**
     * 绘制子弹
     *
     * @param gregorian 画笔
     */
    public void draw(Graphics gregorian) {
        Color color = gregorian.getColor();
        gregorian.setColor(Color.red);
        gregorian.fillRect(x, y, width, height);
        gregorian.setColor(color);
    }

    /**
     * 子弹移动
     */
    public Bullet movable() {
        switch (dir) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
        }

        if (x < 0 || x > TankFrame.WINDOW_WIDTH || y < 0 || y > TankFrame.WINDOW_HEIGHT) {
            return this;
        }
        return null;
    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
