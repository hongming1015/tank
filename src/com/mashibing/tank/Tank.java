package com.mashibing.tank;

import com.sun.imageio.stream.CloseableDisposerRecord;
import sun.plugin2.util.ColorUtil;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 * 坦克
 */
public class Tank {

    //坦克位置
    private int x = 200, y = 200;

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

    public Tank() {
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tank(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void initTank(Graphics graphics){
        //绘制坦克
        draw(graphics);
        //坦克移动
        movable();
        //绘制坦克子弹
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()){
            Bullet bullet = iterator.next();
            bullet.draw(graphics);
            Bullet removeBullet = bullet.movable();
            if (removeBullet != null) {
                iterator.remove();
            }
        }
    }

    public void movable() {
        if (isUp) {
            setY(getY() - getSpeed());
        } else if (isDown) {
            setY(getY() + getSpeed());
        } else if (isLeft) {
            setX(getX() - getSpeed());
        } else if (isRight) {
            setX(getX() + getSpeed());
        }
    }

    public void draw(Graphics graphics) {
         Color c = graphics.getColor();
         graphics.setColor(Color.yellow);
         graphics.fillRect(getX(), getY(), getWidth(), getHeight());
         graphics.setColor(c);
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
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
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
        this.dir = dir;
    }

    public class TankListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    setDir(Dir.LEFT);
                    isLeft =true;
                    break;
                case KeyEvent.VK_UP:
                    setDir(Dir.UP);
                    isUp = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    setDir(Dir.RIGHT);
                    isRight = true;
                    break;
                case KeyEvent.VK_DOWN:
                    setDir(Dir.DOWN);
                    isDown=true;
                    break;
                case KeyEvent.VK_SPACE:
                    shooting();
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    isLeft =false;
                    break;
                case KeyEvent.VK_UP:
                    isUp = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    isRight = false;
                    break;
                case KeyEvent.VK_DOWN:
                    isDown=false;
                    break;
            }
        }

    }


    public void shooting(){
        Bullet bullet = new Bullet(this.x+15, this.y+15,this.dir);
        bullets.add(bullet);
    }


}
