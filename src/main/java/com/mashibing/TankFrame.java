package com.mashibing;

import com.mashibing.bullet.Bullets;
import com.mashibing.explode.Explodes;
import com.mashibing.tank.BaseTank;
import com.mashibing.tank.Tanks;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    //窗口宽度和高度
    public final static int WINDOW_WIDTH = 800, WINDOW_HEIGHT = 600;

    //敌人坦克列表
//    private static Tanks tanks = new Tanks();
    //緩存
    private Image offScreenImage = null;

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

        this.addKeyListener(Tanks.getMyTank().getMoveKeyAdapter());
    }

    @Override
    public void paint(Graphics g) {
        Tanks.initTanks(g);
        Explodes.booms(g);
        Bullets.initBullet(g);
    }

    public void drawBulletNumber(Graphics g) {
//        int totalNumber = 0;
//        for (BaseTank tank : Tanks.getTanks()) {
//            totalNumber += tank.getBullets().size();
//        }
//        g.drawString("總共子彈數量:"+totalNumber,10,30);
    }

    public void drawTankNumber(Graphics g){
        g.drawString("總共坦克:"+Tanks.getTanks().size(),10,80);
    }

    public void setTitle(Graphics g) {
        drawTankNumber(g);
        drawBulletNumber(g);
    }


    /**
     * 設置緩存方法
     * @param g
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(WINDOW_WIDTH, WINDOW_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        setAssemblyColor(gOffScreen,(graphics)->{
            graphics.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        },Color.black);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public static void setAssemblyColor(Graphics graphics, IColor<Graphics> iColor,Color assemblyColor) {
        Color color = graphics.getColor();
        graphics.setColor(assemblyColor);
        iColor.setColor(graphics);
        graphics.setColor(color);
    }

    protected interface IColor<T> {
        void setColor(T t);
    }

}
