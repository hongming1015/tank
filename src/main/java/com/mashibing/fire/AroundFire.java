package com.mashibing.fire;

import com.mashibing.bullet.Bullet;
import com.mashibing.Dir;
import com.mashibing.bullet.Bullets;
import com.mashibing.resource.ResourceMgr;
import com.mashibing.tank.BaseTank;

public class AroundFire implements IFire {
    @Override
    public void Fire(BaseTank tank) {
        for (Dir dir : Dir.values()) {
            Bullet bullet = new Bullet(getBulletX(tank.getX()), getBulletY(tank.getY()), dir , tank);
            Bullets.add(bullet);
        }

    }

    private int getBulletX(int x) {
        return x + ResourceMgr.tankUp.getWidth() / 2 - ResourceMgr.bulletUp.getWidth() / 2;
    }

    private int getBulletY(int y) {
        return y + ResourceMgr.tankDown.getWidth() / 2 - ResourceMgr.bulletDown.getHeight() / 2;
    }
}
