public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                ShowNotify.waitingStage(c, this);
                ShowNotify.startedStage(c, this);
                //TODO
                //Semaphore CAR COUNT
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                ShowNotify.showExeption(e.getMessage());
            } finally {
                ShowNotify.finishedStage(c, this);
            }
        } catch (Exception e) {
            ShowNotify.showExeption(e.getMessage());
        }
    }
}