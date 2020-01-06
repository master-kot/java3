package classwork;

public class WaitNotify {
    public static void main(String[] args) {
        Object lock = new Object();
        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    System.out.print("A");
                    try {
                        lock.notify();
                        lock.wait();
                        Thread.sleep(500);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    System.out.print("B");
                    try {
                        lock.notify();
                        lock.wait();
                        Thread.sleep(500);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
