/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prx.thread;

/**
 *
 * @author Gia Bảo Hoàng
 */
public class BaseThread extends Thread {

    private static boolean suspended = false;
    private final static Object LOCK = new Object();
    private static BaseThread instance;

    public BaseThread() {
    }

    public static BaseThread getInstance() {
        synchronized (LOCK) {
            if (instance == null) {
                instance = new BaseThread();
            }
        }
        return instance;
    }

    public static boolean isSuspended() {
        return suspended;
    }

    public static void setSuspended(boolean suspended) {
        BaseThread.suspended = suspended;
    }
    
    public void suspendThread(){
        setSuspended(true);
        System.out.println("Thread Suspended");
    }
    
    public synchronized void resumeThread(){
        setSuspended(false);
        notifyAll();
        System.out.println("Thread resumed");
    }

   
    

}
