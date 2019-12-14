package classWork;

public class Ex2 {

    public static void main(String[] args) {
        Pair<String, int []> pair1 =
                new Pair<>("key", new int[3]);
        System.out.println(pair1);
        pair1.getValue()[2] = 5;
        System.out.print("(" + pair1.getKey() + ",  [");
        for (int i : pair1.getValue()) {
            System.out.print(i + ", ");
        }
        System.out.println("])");
        //System.out.println(new char[] {'1','2','3'});
    }

}
