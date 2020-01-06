package classwork;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Ex1 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        class Runner implements Runnable {

            boolean running;

            public Runner() {
                running = true;
            }

            synchronized public void stop() {
                running = false;
            }

            @Override
            public void run() {
                while (running) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print("*");
                }
            }
        }

        Runner run = new Runner();

        class CustomThread extends Thread {

            int cnt;

            public CustomThread() {
                cnt = 0;
            }

            synchronized int getCounter() {
                return cnt;
            }

            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    cnt++;
                }
            }
        }

        CustomThread ct = new CustomThread();
        ct.start();

        new Thread(run).start();

        new Thread(() -> {
            try {
                Thread.sleep(3000);
                run.stop();
                while (true) {
                    Thread.sleep(500);
                    System.out.println(ct.getCounter());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
