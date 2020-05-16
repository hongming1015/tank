package com.mashibing.tank;

public class T {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        while (true){
            Thread.sleep(50);

            tankFrame.repaint();
        }
    }

}
