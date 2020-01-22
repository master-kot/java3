package annotation;

public class Tests {

    Utility util;

    @Before
    public void starter(){
        System.out.println("Test starts!");
        util = new Utility(26, 13);
    }

    @Test(priority = 1)
    public void sumTest(){
        System.out.println("Sum test");
        if (util.sum() == 39){
            System.out.println("OK");
        } else {
            System.out.println("WRONG");
        }
    }

    // TODO: 23/01/2020
    @Test(priority = 2)
    public void subTest(){
        System.out.println("Sub test");
        if (util.sum() == 13){
            System.out.println("OK");
        } else {
            System.out.println("WRONG");
        }
    }

    // TODO: 23/01/2020
    @Test(priority = 3)
    public void mulTest(){
        System.out.println("Multiply test");
        if (util.mul() == 338){
            System.out.println("OK");
        } else {
            System.out.println("WRONG");
        }
    }

    // TODO: 23/01/2020
    @Test(priority = 4)
    public void divTest(){
        System.out.println("Divide test");
        if (util.div() == 2){
            System.out.println("OK");
        } else {
            System.out.println("WRONG");
        }
    }

    // TODO: 23/01/2020
    @Test(priority = 5)
    public void modTest(){
        System.out.println("Mod test");
        if (util.div() == 0){
            System.out.println("OK");
        } else {
            System.out.println("WRONG");
        }
    }

    @After
    public void end() {
        System.err.println("All tests passed!");
    }
}
