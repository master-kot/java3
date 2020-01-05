
import java.util.ArrayList;

public class Box<T extends Fruit> {
    /**
     * a. Есть классы Fruit -> Apple, Orange;
     * b. Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
     * c. Для хранения фруктов внутри коробки можете использовать ArrayList;
     * d. Сделать метод getWeight() который высчитывает вес коробки, зная количество фруктов и вес одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
     * e. Сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра,
     * true - если их веса равны, false в противном случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
     * f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку(помним про сортировку фруктов,
     * нельзя яблоки высыпать в коробку с апельсинами), соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
     * g. Не забываем про метод добавления фрукта в коробку.
    * */

    private ArrayList<T> fruits;
    private float weight;
    private T instance;

    public Box (){
        fruits = new ArrayList<>();
        this.weight = 0;
    }

    public boolean compareTo(Box<?> other) {
        //TODO
        //if this.summary weight == other.summary weight, returns true
        return this.getWeight() == other.getWeight();
    }

    public void addFruit(T fruit) {
        //TODO
        instance = fruit;
        weight += fruit.getWeight();
        fruits.add(fruit);
    }

    public void dropFruits(Box<? extends Fruit> otherBox) {
        //TODO
        //otherBox -> this
        if (instance.getClass() != otherBox.instance.getClass()) throw new DropException();
        for (Fruit fruit : otherBox.fruits) {
            addFruit((T) fruit);
        }
        otherBox.fruits.clear();
        otherBox.weight = 0;
    }

    public float getWeight() {
        return weight;
    }
}
