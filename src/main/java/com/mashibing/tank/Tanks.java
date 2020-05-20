package com.mashibing.tank;

import com.mashibing.config.PropertiesMgr;
import com.mashibing.factory.BadTankFactory;
import com.mashibing.factory.GoodTankFactory;
import com.mashibing.factory.TankFactory;
import com.mashibing.fire.AroundFire;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 敌军tank
 */
public class Tanks {

    //所有坦克
    private static List<BaseTank> tanks = new ArrayList<>();

    //自己的坦克
    private static BaseTank myTank;

    //敌方坦克数量
    private static Integer initTankNumber = PropertiesMgr.getInstance().getInt("initTankNumber");

    //坦克工厂
    private static TankFactory badTankFactory = new BadTankFactory();

    private static TankFactory goodTankFactory = new GoodTankFactory();


    /**
     * 初始化所有坦克
     */
    static {
        myTank = goodTankFactory.create();
        myTank.setFire(new AroundFire());
        tanks.add(myTank);
        for (int i = 0; i < initTankNumber; i++) {
           tanks.add(badTankFactory.create(100 + i * 100, 100));
        }
    }


    public static void initTanks(Graphics graphics){
        drawTanks(graphics);
    }

    public static void drawTanks(Graphics graphics){
        Iterator<BaseTank> iterator = tanks.iterator();

        while (iterator.hasNext()) {
            BaseTank tank = iterator.next();
            tank.initTank(graphics);
            if (!tank.isLiving()) {
                iterator.remove();
            }
        }
    }

    public static List<BaseTank> getTanks() {
        return tanks;
    }

    public static void addGamePlayer(GoodTank goodTank){
        getTanks().add(goodTank);
    }

    public static BaseTank getMyTank() {
        return myTank;
    }
}
