package com.mashibing.bullet;

import com.mashibing.explode.BaseExplode;
import com.mashibing.tank.BaseTank;
import lombok.Data;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Bullets {

    private static List<BaseBullet> bullets = new ArrayList<>();

    public static void add(BaseBullet bullet) {
        bullets.add(bullet);
    }

    /**
     * 绘制所有坦克子弹
     *
     * @param graphics
     */
    public static void initBullet(Graphics graphics) {
        Iterator<BaseBullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            BaseBullet bullet = iterator.next();
            bullet.initBullet(graphics);
            if (!bullet.isLiving()) {
                iterator.remove();
            }
        }
    }
}
