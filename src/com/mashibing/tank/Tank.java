package com.mashibing.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 坦克
 */
public class Tank {

    //坦克位置
    private int x = 200, y = 200;

    //坦克宽和高
    private int width = 50, height = 50;

    //坦克每次移动距离
    private int SPEED = 10;

    //坦克是否上下移动
    private boolean isLeft, isUp, isRight, isDown;

    //坦克按键处理的事件
    private KeyAdapter moveKeyAdapter = new TankListener();

    //坦克朝向
    private Dir dir = Dir.UP;

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
        draw(graphics);
        movable();
    }

    public void movable() {
        if (isUp) {
            setY(getY() - getSPEED());
        } else if (isDown) {
            setY(getY() + getSPEED());
        } else if (isLeft) {
            setX(getX() - getSPEED());
        } else if (isRight) {
            setX(getX() + getSPEED());
        }
    }

    public void draw(Graphics graphics) {
        graphics.fillRect(getX(), getY(), getWidth(), getHeight());
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

    public int getSPEED() {
        return SPEED;
    }

    public void setSPEED(int SPEED) {
        this.SPEED = SPEED;
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


}
