package classWork.liquid;

public class Test {
    public static void main(String[] args) {

        Glass<Watter> gw = new Glass<>(new Watter());
        Glass<Metal> gm = new Glass<>(new Metal());
        System.out.println(gw.getRo());
        System.out.println(gm.getRo());

    }
}
