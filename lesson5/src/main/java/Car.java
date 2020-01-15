import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {
    private CyclicBarrier cb;
    private CountDownLatch cdl;
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;

    public Car(Race race, int speed, CyclicBarrier cb, CountDownLatch cdl) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT + speed;
        this.cb = cb;
        this.cdl = cdl;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void run() {
        try {
            ShowNotify.gettingPrepared(this);
            Thread.sleep(500 + (int)(Math.random() * 800));
            ShowNotify.isReady(this);
            //Сигнализирует о готовности автомобиля
            cb.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                Thread.sleep(50);
                race.getStages().get(i).go(this);
            }
            //Синхронизирует завершение гонки
            cdl.countDown();
            if (cdl.getCount() == CARS_COUNT -1) ShowNotify.wonRace(this);
        } catch (Exception e) {
            ShowNotify.showExeption(e.getMessage());
        }
    }
}