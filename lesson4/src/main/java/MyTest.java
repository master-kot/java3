public class MyTest {
    /**
     * Ничего, кроме указанного ниже, внутри потока main изменять нельзя!!!!!
     * Класс написан верно, когда в консоль выводится list и сообщения true
     */
    public static void main(String[] args) {
        ThreadTask task = new ThreadTask();
        Object mon = new Object();
        for (int j = 0; j < 3; j++) {
            TaskRunner [] runners = new TaskRunner[3];
            Thread [] threads = new Thread[3];
            for (int i = 0; i < 3; i++) {
                runners[i] = new TaskRunner(mon, String.valueOf(i+1), i);
                threads[i] = new Thread(runners[i]);
            }
            task.multiThreadRecord(threads);
            try {
                Thread.sleep(10000);
                int cnt = 0;
                int [] arr = new int[]{1,2,3};
                //Когда строка ниже раскоментирована, происходит ConcurrentModificationException
                System.out.println(TaskRunner.list);
                for (int i = 0; i < 9; i++) {
                    System.out.println(String.valueOf(arr[cnt]).equals(TaskRunner.list.get(i))); //здесь true
                    cnt = (cnt + 1) % 3;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}