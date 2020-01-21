package annotation;

public class Tests {

    Utility util;

    @Before
    public void starter(){
        System.out.println("Test starts!");
        util = new Utility(25, 13);
    }

    @Test(priority = 1)
    public void sumTest(){
        System.out.println("Sum test");
        if (util.sum() == 38){
            System.out.println("OK");
        } else {
            System.out.println("WRONG");
        }
    }

    @Test(priority = 2)
    public void subTest(){
        System.out.println("Sub test");
        if (util.sum() == 12){
            System.out.println("OK");
        } else {
            System.out.println("WRONG");
        }
    }

    @Test(priority = 3)
    public void mulTest(){
        // TODO: 18/11/2019  
    }

    @Test(priority = 4)
    public void divTest(){
        // TODO: 18/11/2019  
    }

    @Test(priority = 5)
    public void modTest(){
        // TODO: 18/11/2019  
    }

    @After
    public void end() {
        System.err.println("All tests passed!");
    }
}
