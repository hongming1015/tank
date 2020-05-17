package com.mashibing.tank;

import javafx.scene.input.InputMethodTextRun;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Explodes {

    private static List<Explode> explodes = new ArrayList<>();

    public static void add(Explode explode) {
        explodes.add(explode);
    }

    public static void booms(Graphics graphics){
        Iterator<Explode> iterator = explodes.iterator();
        while (iterator.hasNext()) {
            Explode explode = iterator.next();
            explode.boom(graphics);
            if (!explode.isLiving()) {
                iterator.remove();
            }
        }
    }

}
