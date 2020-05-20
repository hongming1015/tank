package com.mashibing.explode;

import com.mashibing.bullet.BaseBullet;
import com.mashibing.resource.ResourceMgr;
import com.mashibing.tank.BaseTank;
import lombok.Data;

import java.awt.*;
import java.util.Objects;
import java.util.Random;

@Data
public class Explode extends BaseExplode {

    public Explode(BaseBullet bullet) {
        super(bullet);
    }

}
