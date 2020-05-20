package com.mashibing.bullet;

import com.mashibing.Dir;
import com.mashibing.tank.BaseTank;
import lombok.Data;

/**
 * 子弹
 */
@Data
public class Bullet extends BaseBullet {

    public Bullet(int x, int y, Dir dir, BaseTank tank) {
        super(x,y,dir,tank);
    }

}
