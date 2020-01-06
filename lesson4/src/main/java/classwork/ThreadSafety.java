package classwork;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSafety {
    public static void main(String[] args) {
        int cnt = 0;
        ConcurrentLinkedDeque d;
        AtomicInteger ai = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            System.out.println(ai.incrementAndGet());
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i+1);
        }
        Object mutex = new Object();

        new Thread(() -> {
            synchronized (mutex) {
                for (Integer i : list) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }
            synchronized (mutex) {
                for (int i = 0; i < 5; i++) {
                    list.add(11 + i);
                }
                System.out.println(list);
            }
        }).start();

        new Thread(() -> {
            synchronized (mutex) {
                for (int i = 0; i < 5; i++) {
                    list.add(0, -4 + i);
                }
            }
            synchronized (mutex) {
                for (Integer i : list) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
            }
        }).start();
    }
}
