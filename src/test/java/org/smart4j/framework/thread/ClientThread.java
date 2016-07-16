package org.smart4j.framework.thread;

/**
 * Created by DongChao on 2016/7/15.
 */
public class ClientThread extends Thread {
    private Sequence sequence ;

    public ClientThread(Sequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for (int i=0 ;i<30 ;i++){
            System.out.println(Thread.currentThread().getName()+"----------->"+sequence.getNumber());
        }
    }
}
