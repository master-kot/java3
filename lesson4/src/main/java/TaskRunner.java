import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskRunner implements Runnable {
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
                Thread.sleep(1000);
                int cnt = 0;
                int [] arr = new int[]{1,2,3};
                //Когда строка ниже раскоментирована, происходит ConcurrentModificationException
                //System.out.println(TaskRunner.list);
                for (int i = 0; i < 9; i++) {
                    System.out.println(String.valueOf(arr[cnt]).equals(TaskRunner.list.get(i))); //здесь true
                    cnt = (cnt + 1) % 3;
                }
            } catch (InterruptedException e) {
                e.printStackTrace(); } } }

	final Object mutex;	        //монитор синхронизации
    String message;	            //сообщение которое поток будет добавлять в список
    volatile int cnt;
    static volatile int inc;    //переменная позволяющая менять очередность выполнения потоками

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
                try {
                    if (cnt == 0) {
                        while (inc != 0) {mutex.wait();}
                        list.add(message);
                        inc = 1;
                        mutex.notifyAll();
                    }
                    if (cnt == 1) {
                        while (inc != 1) {mutex.wait();}
                        list.add(message);
                        inc = 2;
                        mutex.notifyAll();
                    }
                    if (cnt == 2) {
                        while(inc != 2) {mutex.wait();}
                        list.add(message);
                        inc = 0;
                        mutex.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}