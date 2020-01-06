import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskRunner implements Runnable {
    
	Object mutex;	//монитор синхронизации
    String message;	//сообщение которое поток будет добавлять в список
    volatile int cnt;
    volatile int inc;
    static volatile int iter = 0;
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
	 * реализовать логику записи сообщений в list одновременно из 
	 * трех запущенных потоков с соблюдением синхронизации записываемых данных
	 */
    @Override
    public void run() {
        while (true) {
            list.add(message);
        }
    }
}
