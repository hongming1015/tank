package com.mashibing.tank;

import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 敌军tank
 */
public class Tanks {

    //所有坦克
    private static List<Tank> tanks = new ArrayList<>();

    //自己的坦克
    private static Tank myTank = new Tank(true);

    /**
     * 初始化所有坦克
     */
    static {
        tanks.add(myTank);
        for (int i = 0; i < 5; i++) {
           tanks.add(new Tank(100 + i * 100, 100));
        }
    }


    public static void initTanks(Graphics graphics){
        drawTanks(graphics);
    }

    public static void drawTanks(Graphics graphics){
        Iterator<Tank> iterator = tanks.iterator();

        while (iterator.hasNext()) {
            Tank tank = iterator.next();
            tank.initTank(graphics);
            tank.remove(iterator);
        }
    }

    public static List<Tank> getTanks() {
        return tanks;
    }

    public static void addGamePlayer(Tank tank){
        getTanks().add(tank);
    }

    public static Tank getMyTank() {
        return myTank;
    }
}
