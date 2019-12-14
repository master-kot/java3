

public class DropException extends RuntimeException {

    @Override
    public String getMessage() {
        return "невозможно добавить фрукты в коробку";
    }

}
