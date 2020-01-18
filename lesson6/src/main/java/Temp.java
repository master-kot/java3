import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.*;

public class Temp {
    public static void main(String[] args) throws IOException {
        Logger log = Logger.getLogger("");
        //log4j
        //log.setFilter(record -> record.getMessage().contains("mes"));
        log.info(Arrays.toString(log.getHandlers()));
        log.addHandler(new FileHandler("log.txt", true));
        log.info(Arrays.toString(log.getHandlers()));
        Handler [] handlers = log.getHandlers();
        handlers[0].setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                Date date = new Date(record.getMillis());
                SimpleDateFormat f = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
                return  f.format(date) +
                        " " + record.getLevel() + " : " +
                        record.getMessage() + "\n";
            }
        });
        handlers[0].setFilter(new Filter() {
            @Override
            public boolean isLoggable(LogRecord record) {
                return record.getMessage().contains(" ");
            }
        });
        for (Handler h : handlers) {
            log.info(h.getFormatter().toString());
        }
        LogRecord record;
        handlers[1].setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return record.getLevel() + " : " + record.getMessage();
            }
        });
        log.info("new message");
    }
}
