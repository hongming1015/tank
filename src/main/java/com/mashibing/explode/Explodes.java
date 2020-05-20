package com.mashibing.explode;

import com.mashibing.bullet.BaseBullet;
import com.mashibing.explode.Explode;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Explodes {

    private static List<BaseExplode> explodes = new ArrayList<>();

    public static void add(BaseExplode explode) {
        explodes.add(explode);
    }

    public static void booms(Graphics graphics){
        Iterator<BaseExplode> iterator = explodes.iterator();
        while (iterator.hasNext()) {
            BaseExplode explode = iterator.next();
            explode.boom(graphics);
            if (!explode.isLiving()) {
                iterator.remove();
            }
        }
    }

}
