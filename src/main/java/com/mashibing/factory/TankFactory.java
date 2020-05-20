package com.mashibing.factory;

import com.mashibing.Dir;
import com.mashibing.bullet.BaseBullet;
import com.mashibing.explode.BaseExplode;
import com.mashibing.tank.BaseTank;

public abstract class TankFactory {

    public abstract BaseTank create(int x, int y);

    public abstract BaseTank create();

}
