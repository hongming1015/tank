package com.mashibing.tank;

import com.sun.org.apache.regexp.internal.REUtil;

import java.awt.*;
import java.nio.Buffer;
import java.util.Iterator;

public class Explode {

    //当前图片位置
    private int location = 0;

    //位置
    private int x, y;

    //存在
    private boolean living = false;

    //属于的子弹
    private Bullet bullet;

    //中弹的坦克
    private Tank tank;

    public Explode(Bullet bullet) {
        this.bullet = bullet;
    }

    public void draw(Graphics graphics) {
        if (!isLiving()) return;
        graphics.drawImage(ResourceMgr.explodes[location++], getX(), getY(), null);
    }

    public Explode boomStart(int x,int y,Tank tank){
//        new Audio(ResourceMgr.explodeAudioString).play();
        setX(x);
        setY(y);
        setTank(tank);
        setLiving(true);
        return this;
    }

    public void boom(Graphics g){
        if(isLiving()){
            draw(g);
            if (getLocation() >= ResourceMgr.explodes.length) {
                setLiving(false);
                location = 0;
            }
        }
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
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

    public boolean isLiving() {
        return living;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void setBullet(Bullet bullet) {
        this.bullet = bullet;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }


}
