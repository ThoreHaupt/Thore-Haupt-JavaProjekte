package testStuff.tut10;

public interface myInterface {
    public void myMethodA();

    abstract void name();

    public static class test1 {
        public void myMethodC() {
            System.out.println("C");
        }
    }

    public static class test2 extends test1 implements myInterface {
        public void myMethodA() {
            System.out.println("A");
        }

        public void myMethodB() {
            System.out.println("B");
        }

        public static void main(String[] args) {
            myInterface obj = new test2();
            obj.myMethodA();
            // obj.myMethodB();
            // obj.myMethodC();
        }

        @Override
        public void name() {
            // TODO Auto-generated method stub

        }
    }
}