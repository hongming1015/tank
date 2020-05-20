package com.mashibing.explode;

import com.mashibing.bullet.BaseBullet;
import com.mashibing.resource.ResourceMgr;
import com.mashibing.tank.BaseTank;
import lombok.Data;
import lombok.ToString;

import java.awt.*;

@Data
public abstract class BaseExplode {
    //当前图片位置
    private int location = 0;

    //位置
    private int x, y;

    //存在
    private boolean living = false;

    //属于的子弹
    private BaseBullet bullet;

    //中弹的坦克
    private BaseTank tank;

    public BaseExplode(BaseBullet bullet) {
        this.bullet = bullet;
    }

    public void draw(Graphics graphics) {
        if (!isLiving()) return;
        graphics.drawImage(ResourceMgr.explodes[location++], getX(), getY(), null);
    }

    public BaseExplode boomStart(int x, int y, BaseTank tank){
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

    @Override
    public String toString() {
        return "BaseExplode{" +
                "location=" + location +
                ", x=" + x +
                ", y=" + y +
                ", living=" + living +
                ", bullet=" + bullet +
                ", tank=" + tank +
                '}';
    }
}
