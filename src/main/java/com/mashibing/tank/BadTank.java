package com.mashibing.tank;

import com.mashibing.bullet.BaseBullet;
import com.mashibing.bullet.Bullet;
import com.mashibing.resource.ResourceMgr;
import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.Random;

@Data
public class BadTank extends BaseTank {

    //满能量
    private int maxEnergy = 15;

    //自动需要充能
    private int energy = maxEnergy;

    //随机移动随机数
    Random random = new Random();

    public Image getImage() {
        BufferedImage image = null;
        switch (getDir()) {
            case UP:
                image = ResourceMgr.badTankUp;
                break;
            case DOWN:
                image = ResourceMgr.badTankDown;
                break;
            case LEFT:
                image = ResourceMgr.badTankLeft;
                break;
            case RIGHT:
                image = ResourceMgr.badTankRight;
                break;
        }
        setWidth(image.getWidth());
        setHeight(image.getHeight());
        return image;
    }

    public BadTank(boolean isGamePlayer) {
        super(isGamePlayer);
    }

    public BadTank(int x, int y) {
        super(x,y);
    }

    @Override
    public void movable() {
        setAutoMovable();
        super.movable();
    }

    @Override
    public void initTank(Graphics graphics) {
        super.initTank(graphics);
        fire();
    }


    public void setAutoMovable() {
        if (this.energy++ >= getMaxEnergy()) {
            int num = random.nextInt(4);
            setUp(false);
            setLeft(false);
            setRight(false);
            setDown(false);
            switch (num) {
                case 0:
                    setUp(true);
                    break;
                case 1:
                    setDown(true);
                    break;
                case 2:
                    setLeft(true);
                    break;
                case 3:
                    setRight(true);
                    break;
            }
            energy = 0;
        }
        super.movable();
    }

    @Override
    public void fire() {
        int num = random.nextInt(10);
        if (num > 8) {
            super.fire();
        }
    }

}
