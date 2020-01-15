import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    //Блокировшик входа потоков сверх определенного их числа
    private final Semaphore smp;

    public Tunnel(int maxCarsCount) {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        this.smp = new Semaphore(maxCarsCount);
    }

    /**
     *
     * Разрешить вход в тоннель не более половине участников одновременно
     */
    @Override
    public void go(Car c) {
        ShowNotify.waitingStage(c, this);
        try {
            //вход в секцию блокировки одновременного входа
            smp.acquire();
            ShowNotify.startedStage(c, this);
            Thread.sleep(length / c.getSpeed() * 1000);
        } catch (Exception e) {
            ShowNotify.showExeption(e.getMessage());
        } finally {
            ShowNotify.finishedStage(c, this);
            //выход из секции блокировки
            smp.release();
        }
    }
}