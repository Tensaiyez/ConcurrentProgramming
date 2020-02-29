/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConcurrentProgrammingProject;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zaidm
 */
public class Manufacturer implements Runnable {

    private int randomNumberVar = 100;

    private Bank leftBank;
    private Bank rightBank;
    long startTime;
    long endTime;
    private int count;

    private Semaphore permits;

    public Manufacturer(Bank left, Bank right, Semaphore s) {
        count = 0;
        leftBank = left;
        rightBank = right;
        permits = s;
    }

    public void manufacture() {
        System.out.println(Thread.currentThread().getName() + " : is manufacturing");
        try {
            Thread.sleep((int) (Math.random()) * randomNumberVar); //needs to random eventually
        } catch (InterruptedException ex) {
            Logger.getLogger(Manufacturer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sell() {
        try {
            permits.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Manufacturer.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (!leftBank.isConnected()) {
            leftBank.connect();
            //System.out.println("Left Bank");

            if (!rightBank.isConnected()) {
                rightBank.connect();
                //System.out.println("Right Bank");

                System.out.println(Thread.currentThread().getName() + " : is selling");
                ++count;
                //manufacture();
                try {
                    Thread.sleep(1000); //needs to be random eventually

                } catch (InterruptedException ex) {
                    Logger.getLogger(Manufacturer.class.getName()).log(Level.SEVERE, null, ex);
                }
                rightBank.disconnect();

            }
            leftBank.disconnect();
        }
        permits.release();
        manufacture();

    }

    @Override
    public void run() {
        manufacture();
        for (int i = 0; i < 5; i++) {

            try {
                while (true) {
                    sell();
                }
            } catch (Exception ex) {
                Logger.getLogger(Manufacturer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        manufacture();
//        while (true) {
//            sell();
//        }
    }

    public int getCount() {
        return count;
    }

}
