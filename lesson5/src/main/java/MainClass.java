import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class MainClass {
    /**
     * Организуем гонки: Все участники должны стартовать одновременно,
     * несмотря на то, что на подготовку у каждого из них уходит разное время.
     * В туннель не может заехать одновременно больше половины участников (условность).
     * Попробуйте всё это синхронизировать.
     * Только после того как все завершат гонку, нужно выдать объявление об окончании.
     * Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты классов из пакета util.concurrent.
     */
    //Количество автомобилей
    public static final int CARS_COUNT = 4;
    //Выполняет синхронизацию заданного количества потоков
    private static final CyclicBarrier cb = new CyclicBarrier(CARS_COUNT+1);
    //Позволяет потоку main ожидать завершения операций потоками автомобилей
    private static final CountDownLatch cdl = new CountDownLatch(CARS_COUNT);

    public static void main(String[] args) {
        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT/2), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 15 + (int) (Math.random() * 10), cb, cdl);
        }

        ShowNotify.readyRace();
        Thread[] carThreads = new Thread[CARS_COUNT];
        for (int i = 0; i < CARS_COUNT; i++) {
            carThreads[i] = new Thread(cars[i]);
            carThreads[i].start();
        }

        try {
            //Автомобили должны стартовать одновременно
            cb.await();
            ShowNotify.startRace();
            //Гонка должна заканчиваеться когда все доберутся до финиша
            cdl.await();
            ShowNotify.finishRace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}