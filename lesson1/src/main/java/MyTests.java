
import java.util.ArrayList;
import java.util.Collection;

public class MyTests {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Box<Apple> dropBox = new Box<>();

        for (int i = 0; i < 4; i++) {
            orangeBox.addFruit(new Orange());
        }
        for (int i = 0; i < 6; i++) {
            appleBox.addFruit(new Apple());
            dropBox.addFruit(new Apple());
        }

        appleBox.dropFruits(dropBox);
        //orangeBox.dropFruits(appleBox);

        System.out.println("appleBox.compareTo(dropBox): " + appleBox.compareTo(orangeBox));
        System.out.println("orangeBox.compareTo(dropBox): " + orangeBox.compareTo(dropBox));
        System.out.println("orangeBox: " + orangeBox.getWeight() + ", appleBox: " + appleBox.getWeight() + ", dropBox: " + dropBox.getWeight());
    }
}
