
import java.util.ArrayList;
import java.util.Collection;

public class Box<T extends Fruit> {

    //используте ArrayList для хранения фруктов
    /*
    * a. Есть классы Fruit -> Apple, Orange;(больше фруктов не надо)
    b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
    c. Для хранения фруктов внутри коробки можете использовать ArrayList;
    d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
    e. Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра,
.    * true - если их веса равны, false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
    f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку фруктов,
    * нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
    g. Не забываем про метод добавления фрукта в коробку.
    * */

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

    private ArrayList<T> fruits;

    public Box (){
        fruits = new ArrayList<>();
    }

    public Box (T fruit){
        fruits = new ArrayList<>();
        fruits.add(fruit);
    }

    public boolean compareTo(Box<?> other) {
        //TODO
        //if this.summary weight == other.summary weight
        //then returns true
        return this.getWeight() == other.getWeight();
    }

    public void addFruit(T fruit) {
        //TODO
        fruits.add(fruit);
    }

    public void dropFruits(Box<?> otherBox) {
        //TODO
        //otherBox -> this
        ArrayList<?> fruitsOfOtherBox = otherBox.getFruit();
        for (Object ofOtherBox : fruitsOfOtherBox) {
            if (ofOtherBox.getClass() == fruits.get(0).getClass()) {
                fruits.add((T) ofOtherBox);
            } else {
                throw new DropException();
            }
        }
    }

    public float getWeight() {
        float weight = 0;
        for (int i = 0; i < fruits.size(); i++) {
            weight += fruits.get(i).getWeight();
        }
        return weight;
    }

    public ArrayList<T> getFruit () {
        ArrayList<T> tmp = new ArrayList<>(fruits);
        fruits.clear();
        return tmp;
    }
}