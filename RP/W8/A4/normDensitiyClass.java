package RP.W8.A4;

public class normDensitiyClass {
    public static double normDensitiy(double x, double my, double sigma){
        double factor1 = 1/(sigma*Math.sqrt(2*Math.PI));
        double factor2 = Math.exp(-0.5*Math.pow((x-my)/sigma,2));
        double result = factor1 * factor2;
        double resultRounded = Math.round(result * 10000)/10000.0;
        return resultRounded;
        
    }
    public static void main(String[] args) {
        System.out.println(normDensitiy(0, 0, 1));
        System.out.println(normDensitiy(4, 12, 4));
        System.out.println(normDensitiy(0, 0, 0));
        System.out.println(normDensitiy(0, 0, -1));
    }
}
