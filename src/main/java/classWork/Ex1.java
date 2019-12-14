package classWork;

public class Ex1 {

    //Generics

    static class Glass<T> {

        //T - linked type

        private T entity;

        //alt + insert

        public Glass(T entity) {
            this.entity = entity;
        }

        public T getEntity() {
            return entity;
        }

        public void setEntity(T entity) {
            this.entity = entity;
        }
    }

    public static void main(String[] args) {

        Glass<int []> obj = new Glass<>(new int[] {1,2,3,4,5});
        Glass<Exception> obj1 = new Glass<>(new Exception());
        Glass<String> obj2 = new Glass<>("123124");
        Glass<Integer> obj3 = new Glass<>(7);

        int a = obj.getEntity()[2];
        System.out.println(obj.getEntity()[2]);
        obj1.getEntity().printStackTrace();
        System.out.println(obj2.getEntity());
        System.out.println(obj3.getEntity());

        System.out.println(func(1, 123124124241241234L));

    }

    private static <T extends Number, K extends Number> T func(T arg1, K arg2){
        //alt + enter - win, linux
        //cmd + n - mac
        T ans = (T) new Double(arg1.doubleValue() + arg2.doubleValue());
        return ans;
    }

}
