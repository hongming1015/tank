package com.mashibing.tank;

import com.mashibing.bullet.BaseBullet;
import com.mashibing.bullet.Bullet;
import com.mashibing.resource.ResourceMgr;
import lombok.Data;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Random;

/**
 * 坦克
 */
@Data
public class GoodTank extends BaseTank {

    //坦克按键处理的事件
    private KeyAdapter moveKeyAdapter;

    //随机移动随机数
    Random random = new Random();

    //满能量
    private int maxEnergy = 15;

    //自动需要充能
    private int energy = maxEnergy;

    {
        moveKeyAdapter = new TankListener();
    }

    public GoodTank(boolean isGamePlayer) {
        super(isGamePlayer);
    }

    public GoodTank(int x, int y) {
        super(x,y);
    }

    @Override
    public void initTank(Graphics graphics) {
        if (!isLiving()) {
            return;
        }
        draw(graphics);
        movable();
    }

    public void autoFire() {
        int num = random.nextInt(10);
        if (num > 8) {
            fire();
        }
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(getImage(), getX(), getY(), null);
    }

    @Override
    public void fire() {
        if (!isLiving()) {
            return;
        }
        getFire().Fire(this);
    }

    public BufferedImage getImage() {
        BufferedImage image = null;
        switch (getDir()) {
            case UP:
                image = ResourceMgr.tankUp;
                break;
            case DOWN:
                image = ResourceMgr.tankDown;
                break;
            case LEFT:
                image = ResourceMgr.tankLeft;
                break;
            case RIGHT:
                image = ResourceMgr.tankRight;
                break;
        }
        setWidth(image.getWidth());
        setHeight(image.getHeight());
        return image;
    }

    @Override
    public void die() {
        setLiving(false);
    }

    public class TankListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    setLeft(true);
                    break;
                case KeyEvent.VK_UP:
                    setUp(true);
                    break;
                case KeyEvent.VK_RIGHT:
                    setRight(true);
                    break;
                case KeyEvent.VK_DOWN:
                    setDown(true);
                    break;
                case KeyEvent.VK_SPACE:
                    fire();
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    setLeft(false);
                    break;
                case KeyEvent.VK_UP:
                    setUp(false);
                    break;
                case KeyEvent.VK_RIGHT:
                    setRight(false);
                    break;
                case KeyEvent.VK_DOWN:
                    setDown(false);
                    break;
            }
        }

    }


}
