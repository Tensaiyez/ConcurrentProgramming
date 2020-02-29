/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConcurrentProgrammingProject;

/**
 *
 * @author zaidm
 */
public class Bank {

    private boolean connected = false;
    private String name = "";

    public Bank(String name) {
        this.name = name;
    }

    public synchronized void connect() {
        System.out.println(Thread.currentThread().getName() + " connected to: " + name);
        connected = true;
    }

    public synchronized void disconnect() {
        System.out.println(Thread.currentThread().getName() + " disconnected from: " + name);
        connected = false;
    }

    public synchronized boolean isConnected() {
        return connected;
    }

}
