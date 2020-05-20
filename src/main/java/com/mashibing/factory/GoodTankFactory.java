package com.mashibing.factory;

import com.mashibing.Dir;
import com.mashibing.fire.AroundFire;
import com.mashibing.tank.BaseTank;
import com.mashibing.tank.GoodTank;

public class GoodTankFactory extends TankFactory{


    @Override
    public BaseTank create(int x, int y) {
        GoodTank tank = new GoodTank(x,y);
        tank.setGamePlayer(true);
        setFire(tank);
        return tank;
    }

    @Override
    public BaseTank create() {
        GoodTank tank = new GoodTank(true);
        setFire(tank);
        return tank;
    }

    private void setFire(BaseTank tank){
        tank.setFire(new AroundFire());
    }
}
