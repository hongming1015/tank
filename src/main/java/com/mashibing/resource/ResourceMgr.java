package com.mashibing.resource;

import com.mashibing.ImageUtil;
import org.omg.CORBA.PRIVATE_MEMBER;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {

    //坦克1号
    public static BufferedImage tankUp,tankDown,tankLeft,tankRight;

    //坦克2号
    public static BufferedImage badTankUp,badTankDown,badTankLeft,badTankRight;

    //子弹
    public static BufferedImage bulletUp,bulletDown,bulletLeft,bulletRight;

    //爆炸连环图片
    public static BufferedImage[] explodes = new BufferedImage[15];

    //爆炸音频
    public static String explodeAudioString = "resource/audio/explode.wav";

    static  {
        try {
            //坦克1号
            initTankImage();

            //坦克2号
            initBadTankImage();

            //子弹1号
            initBulletImage();

            //爆炸图
            initExplode();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void initTankImage() throws IOException {
        tankUp = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank2.png"));
        tankLeft = ImageUtil.rotateImage(tankUp,-90);
        tankRight = ImageUtil.rotateImage(tankUp,90);
        tankDown = ImageUtil.rotateImage(tankUp,180);
    }

    private static void initBadTankImage() throws IOException {
        badTankUp= ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
        badTankLeft = ImageUtil.rotateImage(badTankUp,-90);
        badTankRight = ImageUtil.rotateImage(badTankUp,90);
        badTankDown = ImageUtil.rotateImage(badTankUp,180);
    }

    private static void initBulletImage() throws IOException {
        bulletUp = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
        bulletLeft = ImageUtil.rotateImage(bulletUp,-90);
        bulletRight = ImageUtil.rotateImage(bulletUp,90);
        bulletDown = ImageUtil.rotateImage(bulletUp,180);
    }

    private static void initExplode() throws IOException {
        for (int i = 0; i < explodes.length; i++) {
            explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e"+(i+1)+".gif"));
        }
    }


}
