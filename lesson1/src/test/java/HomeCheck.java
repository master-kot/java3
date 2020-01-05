import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HomeCheck {

    Box<Apple> appleBox;
    Box<Orange> orangeBox;
    Box<Apple> dropBox;

    @Before
    public void starter() {
        appleBox = new Box<>();
        orangeBox = new Box<>();
        dropBox = new Box<>();
        for (int i = 0; i < 6; i++) {
            appleBox.addFruit(new Apple());
        }
        for (int i = 0; i < 4; i++) {
            orangeBox.addFruit(new Orange());
            dropBox.addFruit(new Apple());
        }
    }

    @Test
    public void testCompare() {
        boolean res = orangeBox.compareTo(appleBox);
        Assert.assertTrue(res);
    }

    @Test
    public void dropTest() {
        appleBox.dropFruits(dropBox);
        double weight = dropBox.getWeight();
        Assert.assertEquals(0, weight, 0.00000001);
        weight = appleBox.getWeight();
        Assert.assertEquals(10, weight, 0.00000001);
    }

    @Test(expected = DropException.class)
    public void testFailDrop() {
        appleBox.dropFruits(orangeBox);
    }
}