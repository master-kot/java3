public class MainClass {

    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        //TODO
        //стартовать в одно время
        //гонка заканчивается когда все потоки закончатся

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        ShowNotify.readyRace();
        Thread[] carThreads = new Thread[CARS_COUNT];
        for (int i = 0; i < CARS_COUNT; i++) {
            carThreads[i] = new Thread(cars[i]);
            carThreads[i].start();
        }

        ShowNotify.startRace();
        for (int i = 0; i < CARS_COUNT; i++) {
            try {
                carThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ShowNotify.finishRace();
    }
}