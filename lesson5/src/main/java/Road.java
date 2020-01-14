public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            ShowNotify.startedStage(c, this);
            Thread.sleep(length / c.getSpeed() * 1000);
            ShowNotify.finishedStage(c, this);
        } catch (InterruptedException e) {
            ShowNotify.showExeption(e.getMessage());
        }
    }
}