package com.mashibing.tank;

public class T {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        while (true){
            Thread.sleep(50);
            if(tankFrame.isUp()){
                tankFrame.setGamePlayerTankY(tankFrame.getGamePlayerTankY() - 10);
            }
            if(tankFrame.isDown()){
                tankFrame.setGamePlayerTankY(tankFrame.getGamePlayerTankY() + 10);
            }
            if(tankFrame.isLeft()){
                tankFrame.setGamePlayerTankX(tankFrame.getGamePlayerTankX() - 10);
            }
            if(tankFrame.isRight()){
                tankFrame.setGamePlayerTankX(tankFrame.getGamePlayerTankX() + 10);
            }
            tankFrame.repaint();
        }
    }

}
