package com.mashibing.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    //窗口宽度和高度
    private final static int WINDOW_WIDTH = 800,  WINDOW_HEIGHT = 600;

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

}
