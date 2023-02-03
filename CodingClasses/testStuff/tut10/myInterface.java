package CodingClasses.testStuff.tut10;

public interface myInterface {
    public void myMethodA();

    abstract void name();

    static void kill() {
    }

    public static void getFucked() {
        System.out.println("fuck");
    }

    public static class test1 {
        public void myMethodC() {
            System.out.println("C");
        }
    }

    public static class test2 extends test1 implements myInterface {
        public void myMethodA() {
            System.out.println("A");
        }

        public static void getFucked() {
            System.out.println("Fuck");
        }

        public void myMethodB() {
            System.out.println("B");
        }

        public static void main(String[] args) {
            myInterface obj = new test2();
            test2 t = new test2();
            myInterface.getFucked();
            test2.getFucked();
            /* obj.getFucked(); */
            obj.myMethodA();
            // obj.myMethodB();
            // obj.myMethodC();
        }

        @Override
        public void name() {
            // TODO Auto-generated method stub
            System.out.println("hahhasd");
        }
    }
}