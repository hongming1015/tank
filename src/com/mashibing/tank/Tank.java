package com.mashibing.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 坦克
 */
public class Tank {

    //坦克位置
    private int x = 400, y = 400;

    //坦克宽和高
    private int width = 50, height = 50;

    //坦克每次移动距离
    private int speed = 5;

    //坦克是否上下移动
    private boolean isLeft, isUp, isRight, isDown;

    //坦克按键处理的事件
    private KeyAdapter moveKeyAdapter = new TankListener();

    //坦克朝向
    private Dir dir = Dir.UP;

    //坦克射出去的子弹
    List<Bullet> bullets = new ArrayList<>();

    //是否玩家
    private boolean isGamePlayer = false;

    //用來判斷是否碰撞
    private Rectangle rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());

    //是否存活
    private boolean living = true;

    //随机移动随机数
    Random random = new Random();

    //满能量
    private int maxEnergy = 15;

    //自动需要充能
    private int energy = maxEnergy;


    public Tank() {
    }

    public Tank(boolean isGamePlayer) {
        this.isGamePlayer = isGamePlayer;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tank(int x, int y, boolean isGamePlayer) {
        this(x, y);
        this.isGamePlayer = isGamePlayer;
    }

    public Tank(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void initTank(Graphics graphics) {
        if (!isLiving()) {
            return;
        }
        draw(graphics);
        if (isGamePlayer) {
            movable();
        } else {
            autoFire();
            autoMovable();
        }
        initBullet(graphics);
    }


    /**
     * 绘制所有坦克子弹
     *
     * @param graphics
     */
    private void initBullet(Graphics graphics) {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.initBullet(graphics);
            if (!bullet.isLiving()) {
                iterator.remove();
            }
        }
    }

    public int getBulletNumber() {
        return bullets.size();
    }

    public void autoMovable() {
        if (this.energy++ >= getMaxEnergy()) {
            int num = random.nextInt(4);
            this.isUp = false;
            this.isLeft = false;
            this.isRight = false;
            this.isDown = false;
            switch (num) {
                case 0:
                    this.isUp = true;
                    break;
                case 1:
                    this.isDown = true;
                    break;
                case 2:
                    this.isLeft = true;
                    break;
                case 3:
                    this.isRight = true;
                    break;
            }
            energy = 0;
        }
        movable();
    }

    public void autoFire() {
        int num = random.nextInt(10);
        if (num > 8) {
            fire();
        }
    }

    /**
     * 坦克移动
     */
    private void movable() {
        if (!isLiving()) {
            return;
        }
        if (isUp) {
            setDir(Dir.UP);
            setY(getY() - getSpeed());
        } else if (isDown) {
            setDir(Dir.DOWN);
            setY(getY() + getSpeed());
        } else if (isLeft) {
            setDir(Dir.LEFT);
            setX(getX() - getSpeed());
        } else if (isRight) {
            setDir(Dir.RIGHT);
            setX(getX() + getSpeed());
        }
        getRectangle().setLocation(getX(), getY());
    }

    private void draw(Graphics graphics) {
        graphics.drawImage(getImage(), getX(), getY(), null);
    }

    public void fire() {
        if (!isLiving()) {
            return;
        }
        Bullet bullet = new Bullet(getBulletX(), getBulletY(), this.dir, this);
        bullets.add(bullet);
    }

    private int getBulletX() {
        return getX() + ResourceMgr.tankUp.getWidth() / 2 - ResourceMgr.bulletUp.getWidth() / 2;
    }

    private int getBulletY() {
        return getY() + ResourceMgr.tankDown.getWidth() / 2 - ResourceMgr.bulletDown.getHeight() / 2;
    }

    public BufferedImage getImage() {
        BufferedImage image = null;
        switch (dir) {
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

    public void die() {
        setLiving(false);
    }

    public boolean remove(Iterator iterator) {
        if (!isLiving()) {
            iterator.remove();
            return true;
        }
        return false;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x < 0 || x > TankFrame.WINDOW_WIDTH - getWidth() * 2) {
            return;
        }
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y < 0 + getHeight() || y > TankFrame.WINDOW_HEIGHT - getHeight() * 2) {
            return;
        }
        this.y = y;
    }

    public KeyAdapter getMoveKeyAdapter() {
        return moveKeyAdapter;
    }

    public void setMoveKeyAdapter(KeyAdapter moveKeyAdapter) {
        this.moveKeyAdapter = moveKeyAdapter;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        switch (dir) {
            case DOWN:
                setWidth(ResourceMgr.bulletDown.getWidth());
                setHeight(ResourceMgr.bulletDown.getHeight());
                break;
            case UP:
                setWidth(ResourceMgr.bulletUp.getWidth());
                setHeight(ResourceMgr.bulletUp.getHeight());
                break;
            case LEFT:
                setWidth(ResourceMgr.bulletLeft.getWidth());
                setHeight(ResourceMgr.bulletLeft.getHeight());
                break;
            case RIGHT:
                setWidth(ResourceMgr.bulletRight.getWidth());
                setHeight(ResourceMgr.bulletRight.getHeight());
                break;
        }
        rectangle.setSize(getWidth(), getHeight());
        this.dir = dir;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public class TankListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    isLeft = true;
                    break;
                case KeyEvent.VK_UP:
                    isUp = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    isRight = true;
                    break;
                case KeyEvent.VK_DOWN:
                    isDown = true;
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
                    isLeft = false;
                    break;
                case KeyEvent.VK_UP:
                    isUp = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    isRight = false;
                    break;
                case KeyEvent.VK_DOWN:
                    isDown = false;
                    break;
            }
        }

    }


}
