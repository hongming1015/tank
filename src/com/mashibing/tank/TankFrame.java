package com.mashibing.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    //窗口宽度和高度
    private int windowWidth = 800, WindowHeight = 600;

    //第一个坦克的位置
    private int gamePlayerTankX = 200, gamePlayerTankY = 200;

    //坦克的宽度和高度
    private int gamePlayerTankWidth = 50, getGamePlayerTankHeight = 50;

    private boolean isLeft, isUp, isRight, isDown;

    public boolean isLeft() {
        return isLeft;
    }

    public boolean isUp() {
        return isUp;
    }

    public boolean isRight() {
        return isRight;
    }

    public boolean isDown() {
        return isDown;
    }

    public int getGamePlayerTankX() {
        return gamePlayerTankX;
    }

    public void setGamePlayerTankX(int gamePlayerTankX) {
        this.gamePlayerTankX = gamePlayerTankX;
    }

    public int getGamePlayerTankY() {
        return gamePlayerTankY;
    }

    public void setGamePlayerTankY(int gamePlayerTankY) {
        this.gamePlayerTankY = gamePlayerTankY;
    }

    public TankFrame() {
        setSize(windowWidth, WindowHeight);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.addKeyListener(new MyKeyListener());
    }


    @Override
    public void paint(Graphics g) {
        g.fillRect(gamePlayerTankX, gamePlayerTankY, gamePlayerTankWidth, getGamePlayerTankHeight);
    }


    private class MyKeyListener extends KeyAdapter {
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
