package RP.W6.A1;

/**
 * Task 6.1
 */
public class MyMath {
    
    /** random
     * @param args
     */
    public static void main(String[] args) {
        int[] tests = {-1,1,-1337,2017};
        for (int i : tests) {
            System.out.println(Math.abs(i));
        }
    }

    /**
     * returns absolute value of an integer
     * @param i number
     * @return  absolute value of that number
     */
    public static int abs(int i){
        return (i >= 0)?i:-i;
    }

    /**
     * returns absolute value of a double
     * Sasts to int, so all decimal spaces are lost.
     * @param i number
     * @return absolute value of that number
     */
    public static int abs(double i) {
        return ((int)i >= 0) ? (int)i : (int) -i;
    }

}
