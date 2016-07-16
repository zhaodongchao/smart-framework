package org.smart4j.framework.thread.impl;

import org.smart4j.framework.thread.ClientThread;
import org.smart4j.framework.thread.Sequence;

/**
 * 使用ThreadLocal，将变量存入，可以保证其线程的安全
 * Created by DongChao on 2016/7/15.
 */
public class SequenceB implements Sequence {
    private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    public int getNumber(){
        numberContainer.set(numberContainer.get()+1);
        return  numberContainer.get();
    }
    public static void main(String[] args){
        Sequence sequence = new SequenceB();
        ClientThread thread1 = new ClientThread(sequence);
        ClientThread thread2 = new ClientThread(sequence);
        ClientThread thread3 = new ClientThread(sequence);

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
