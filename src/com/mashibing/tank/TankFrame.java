package com.mashibing.tank;

import sun.util.calendar.Gregorian;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Clock;

public class TankFrame extends Frame {

    //窗口宽度和高度
    public final static int WINDOW_WIDTH = 800,  WINDOW_HEIGHT = 600;

    Tank tank = new Tank();


    public TankFrame() {
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.addKeyListener(tank.getMoveKeyAdapter());
    }

    @Override
    public void paint(Graphics g) {
       tank.initTank(g);
    }


    private Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(WINDOW_WIDTH, WINDOW_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0,0,WINDOW_WIDTH,WINDOW_HEIGHT);
        gOffScreen.setColor(color);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }
}
