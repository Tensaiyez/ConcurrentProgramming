/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConcurrentProgrammingProject;

import java.util.concurrent.Semaphore;

/**
 *
 * @author zaidm
 */
public class Main {

    public static void main(String[] args) {

        final int size = 5;
        Semaphore s = new Semaphore(size - 1);

        Bank[] b = new Bank[size];

        for (int i = 0; i < b.length; i++) {
            b[i] = new Bank("" + i);
        }
        Manufacturer[] m = new Manufacturer[size];
        m[0] = new Manufacturer(b[0], b[1], s);
        m[1] = new Manufacturer(b[1], b[2], s);
        m[2] = new Manufacturer(b[2], b[3], s);
        m[3] = new Manufacturer(b[3], b[4], s);
        m[4] = new Manufacturer(b[4], b[0], s);

        for (Manufacturer m1 : m) {
            Thread t = new Thread(m1);
            t.start();
        }

    }

}
