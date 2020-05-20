package com.mashibing.bullet;

import com.mashibing.Dir;
import com.mashibing.explode.BaseExplode;
import com.mashibing.explode.Explode;
import com.mashibing.explode.Explodes;
import com.mashibing.TankFrame;
import com.mashibing.resource.ResourceMgr;
import com.mashibing.tank.BaseTank;
import com.mashibing.tank.Tanks;
import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;

@Data
public abstract class BaseBullet {
    //子弹位置
    private int x, y;

    //子弹宽和高
    private int width, height;

    //子弹速度
    private int speed = 10;

    //子弹方向
    private Dir dir;

    //发射的坦克
    private BaseTank tank;

    //子弹是否存活
    private boolean living = true;

    //爆照效果
    private BaseExplode explode = new Explode(this);

    Rectangle rectangle  = new Rectangle(getX(),getY(),getWidth(),getHeight());

    public BaseBullet(int x, int y, Dir dir, BaseTank tank) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tank = tank;
        setUpSize();
    }

    private void setUpSize(){
        switch (dir){
            case LEFT:
                setWidth(ResourceMgr.bulletLeft.getWidth());
                setHeight(ResourceMgr.bulletLeft.getHeight());
            case RIGHT:
                setWidth(ResourceMgr.bulletRight.getWidth());
                setHeight(ResourceMgr.bulletRight.getHeight());
            case UP:
                setWidth(ResourceMgr.bulletUp.getWidth());
                setHeight(ResourceMgr.bulletUp.getHeight());
            case DOWN:
                setWidth(ResourceMgr.bulletDown.getWidth());
                setHeight(ResourceMgr.bulletDown.getHeight());
        }
        rectangle.setSize(getWidth(),getHeight());
    }

    public void initBullet(Graphics graphics) {
        movable();
        if(!hitTank()){
            draw(graphics);
        }
    }

    /**
     * 绘制子弹
     *
     * @param graphics 画笔
     */
    public void draw(Graphics graphics) {
        graphics.drawImage(getImage(), getX(), getY(),null);
    }

    public void die(){
        setLiving(false);
    }

    /**
     * 子弹移动
     */
    public void movable() {
        if (!isLiving()){
            return;
        }
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
        rectangle.setLocation(getX(),getY());
        crossJudgment();
    }

    /**
     * 计算子弹是否越界
     */
    private void crossJudgment(){
        if(x < 0 || x > TankFrame.WINDOW_WIDTH || y < 0 || y > TankFrame.WINDOW_HEIGHT){
            setLiving(false);
        }
    }

    /**
     * 计算是否击中坦克
     * @return
     */
    public boolean hitTank(){
        for (BaseTank tank : Tanks.getTanks()) {
            if (rectangle.intersects(tank.getRectangle())) {
                if (getTank().equals(tank)) {
                    continue;
                }
                this.die();
                tank.die();
                BaseExplode baseExplode = explode.boomStart(tank.getX(), tank.getY(), tank);
                Explodes.add(baseExplode);
                return true;
            }
        }
        return false;
    }

    public BufferedImage getImage(){
        BufferedImage image = null;
        switch (dir) {
            case UP:
                image = ResourceMgr.bulletUp;
                break;
            case LEFT:
                image = ResourceMgr.bulletLeft;
                break;
            case RIGHT:
                image = ResourceMgr.bulletRight;
                break;
            case DOWN:
                image = ResourceMgr.bulletDown;
                break;
        }
        setWidth(image.getWidth());
        setHeight(image.getHeight());
        return image;
    }
}
