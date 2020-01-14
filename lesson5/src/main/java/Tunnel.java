import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    Semaphore smp;

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
        smp = new Semaphore(2);
    }

    //TODO
    //Разрешить вход в тоннель не более 2 потокам одновременно
    @Override
    public void go(Car c) {
        try {
            ShowNotify.waitingStage(c, this);
            smp.acquire();
            Thread.sleep(50);
            ShowNotify.startedStage(c, this);
            Thread.sleep(length / c.getSpeed() * 1000);
        } catch (Exception e) {
            ShowNotify.showExeption(e.getMessage());
        } finally {
            ShowNotify.finishedStage(c, this);
            smp.release();
        }
    }
}