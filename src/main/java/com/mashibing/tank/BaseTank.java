package com.mashibing.tank;

import com.mashibing.Dir;
import com.mashibing.TankFrame;
import com.mashibing.bullet.BaseBullet;
import com.mashibing.fire.DefaultFire;
import com.mashibing.fire.IFire;
import com.mashibing.resource.ResourceMgr;
import lombok.Data;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.List;

@Data
public abstract class BaseTank {
    //坦克位置
    private int x = 400, y = 400;

    //坦克宽和高
    private int width = ResourceMgr.bulletUp.getWidth(), height = ResourceMgr.bulletUp.getWidth();

    //坦克每次移动距离
    private int speed = 5;

    //坦克是否上下移动
    private boolean isLeft, isUp, isRight, isDown;

    //坦克朝向
    private Dir dir = Dir.UP;

    //是否玩家
    private boolean isGamePlayer = false;

    //用來判斷是否碰撞
    private Rectangle rectangle = new Rectangle(getX(), getY(), getWidth(), getHeight());

    //是否存活
    private boolean living = true;

    //绑定的事件
    private KeyAdapter moveKeyAdapter;

    public BaseTank(boolean isGamePlayer) {
        this.isGamePlayer = isGamePlayer;
    }

    public BaseTank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public BaseTank(int x, int y, boolean isGamePlayer) {
        this(x, y);
        this.isGamePlayer = isGamePlayer;
    }

    public BaseTank(int x, int y, int width, int height) {
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
        movable();
    }

    public void draw(Graphics graphics) {
        graphics.drawImage(getImage(), getX(), getY(), null);
    }

    abstract Image getImage();

    public void movable(){
        if (isUp()) {
            setDir(Dir.UP);
            setY(getY() - getSpeed());
        } else if (isDown()) {
            setDir(Dir.DOWN);
            setY(getY() + getSpeed());
        } else if (isLeft()) {
            setDir(Dir.LEFT);
            setX(getX() - getSpeed());
        } else if (isRight()) {
            setDir(Dir.RIGHT);
            setX(getX() + getSpeed());
        }
        getRectangle().setLocation(getX(), getY());
    }

    public void fire(){
        if (!isLiving()) {
            return;
        }
        getFire().Fire(this);
    }

    public void die(){
        setLiving(false);
    }

    public void setDir(Dir dir) {
        getRectangle().setSize(getWidth(), getHeight());
        this.dir = dir;

    }

    public void setX(int x) {
        if (x < 0 || x > TankFrame.WINDOW_WIDTH - getWidth() * 2) {
            return;
        }
        this.x = x;
    }

    public void setY(int y) {
        if (y < 0 + getHeight() || y > TankFrame.WINDOW_HEIGHT - getHeight() * 2) {
            return;
        }
        this.y = y;
    }

    //装载的子弹
    private IFire fire;

    public IFire getFire() {
        if (fire == null) {
            synchronized (this) {
                if (fire == null) {
                    fire = new DefaultFire();
                }
            }
        }
        return fire;
    }
}
