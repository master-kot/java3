import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskRunner implements Runnable {
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
                Thread.sleep(1000);
                int cnt = 0;
                int [] arr = new int[]{1,2,3};
                System.out.println(TaskRunner.list);
                for (int i = 0; i < 9; i++) {
                    System.out.println(String.valueOf(arr[cnt]) + ":" + TaskRunner.list.get(i) + ":" + String.valueOf(arr[cnt]).equals(TaskRunner.list.get(i)));
                    cnt = (cnt + 1) % 3;
                }
            } catch (InterruptedException e) {
                e.printStackTrace(); } } }

	final Object mutex;	//монитор синхронизации
    String message;	//сообщение которое поток будет добавлять в список
    volatile int cnt;
    volatile int inc;
    volatile int iter = 0;

    /**
     * этот список будет проверяться на предмет синхронизации данных,
     * а именно на соответствие: [1, 2, 3, 1, 2, 3, 1, 2, 3....]
	 */
    static volatile LinkedList<String> list = new LinkedList<>();
	
	public TaskRunner(Object mutex, String msg, int cnt) {
        this.mutex = mutex;
        message = msg; //Каждый поток должен писать в список только свое сообщение
        this.cnt = cnt;
        inc = 0;
    } 
    
	//TODO: 26.12.2019
	/**
	 * реализовать запись сообщений в list одновременно из
	 * трех запущенных потоков с соблюдением синхронизации записываемых данных
	 */
    @Override
    public void run() {


        while (true) {
            synchronized (mutex) {
                if (cnt == 0 & iter == 0) {
                   //mutex.notifyAll();
                   list.add(message);
                   iter = Integer.parseInt(message);
                   //mutex.wait();
               } else if (cnt == 1 & iter == 1) {
                   //mutex.notifyAll();
                   list.add(message);
                   iter = Integer.parseInt(message);
                   //mutex.wait();
               } else if (cnt == 2 & iter == 2) {
                   //mutex.notifyAll();
                   list.add(message);
                   iter = 0;
                   //mutex.wait();
               }
            }
        }
    }
}