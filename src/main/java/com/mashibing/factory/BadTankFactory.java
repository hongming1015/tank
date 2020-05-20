package com.mashibing.factory;

import com.mashibing.fire.DefaultFire;
import com.mashibing.tank.BadTank;
import com.mashibing.tank.BaseTank;

public class BadTankFactory extends TankFactory{


    @Override
    public BaseTank create(int x, int y) {
        BadTank tank = new BadTank(x,y);
        setFire(tank);
        return tank;
    }

    @Override
    public BaseTank create() {
        BadTank tank = new BadTank(false);
        setFire(tank);
        return tank;
    }

    public void setFire(BaseTank tank){
        tank.setFire(new DefaultFire());
    }
}
