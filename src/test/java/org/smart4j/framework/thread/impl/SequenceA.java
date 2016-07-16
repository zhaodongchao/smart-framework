package org.smart4j.framework.thread.impl;

import org.smart4j.framework.thread.ClientThread;
import org.smart4j.framework.thread.Sequence;

/**
 * 线程之间共享的static变量无法保证线程的安全性
 * Created by DongChao on 2016/7/15.
 */
public class SequenceA implements Sequence {
    private static int  number = 0 ;
    public int getNumber() {
        number = number + 1 ;
        return number;
    }
    public static void main(String[] args){
        Sequence sequence = new SequenceA();
        ClientThread thread1 = new ClientThread(sequence);
        ClientThread thread2 = new ClientThread(sequence);
        ClientThread thread3 = new ClientThread(sequence);

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
