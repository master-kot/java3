import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskRunner implements Runnable {

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
                        System.out.println("Thread 0 added message!");
                        inc = 1;
                        mutex.notifyAll();
                    }
                    if (cnt == 1) {
                        while (inc != 1) {mutex.wait();}
                        list.add(message);
                        System.out.println("Thread 1 added message!");
                        inc = 2;
                        mutex.notifyAll();
                    }
                    if (cnt == 2) {
                        while(inc != 2) {mutex.wait();}
                        list.add(message);
                        System.out.println("Thread 2 added message!");
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