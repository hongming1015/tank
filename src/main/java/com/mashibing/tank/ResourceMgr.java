package com.mashibing.tank;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {

    //坦克
    public static BufferedImage tankUp,tankDown,tankLeft,tankRight;

    //子弹
    public static BufferedImage bulletUp,bulletDown,bulletLeft,bulletRight;

    //爆炸连环图片
    public static BufferedImage[] explodes = new BufferedImage[15];

    //爆炸音频
    public static String explodeAudioString = "resource/audio/explode.wav";

    static  {
        try {
            System.out.println(ResourceMgr.class.getClassLoader().getResource("").getPath());
            tankUp = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            tankLeft = ImageUtil.rotateImage(tankUp,-90);
            tankRight = ImageUtil.rotateImage(tankUp,90);
            tankDown = ImageUtil.rotateImage(tankUp,180);

            bulletUp = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletLeft = ImageUtil.rotateImage(bulletUp,-90);
            bulletRight = ImageUtil.rotateImage(bulletUp,90);
            bulletDown = ImageUtil.rotateImage(bulletUp,180);

            for (int i = 0; i < explodes.length; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
