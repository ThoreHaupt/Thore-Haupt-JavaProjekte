package PracticeProjects;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SigmaSum {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        System.out.println(sum("f3", 0, 100000, new Double[] { 0.99 }));
    }

    public static double sum(String methodName, int start, int end, Double[] params) throws NoSuchMethodException,
            SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?>[] parameterTypes = new Class[params.length + 1];
        parameterTypes[0] = Double.class;
        for (int i = 1; i < params.length; i++) {
            parameterTypes[i] = params[i].getClass();
        }
        parameterTypes[1] = Double.class;

        Method sigmaMethod = SigmaSum.class.getMethod(methodName, parameterTypes);
        SigmaSum main = new SigmaSum();
        double retrunvalue = SigmaCalc(main, sigmaMethod, start, end, new Double[] { 2.0 });
        return retrunvalue;
    }

    public double f1(Double k) {
        return k.doubleValue();
    }

    public double f2(Double k, Double a) {
        return a / (k * k);
    }

    public double f3(Double k, Double x) {
        return Math.pow((-x), k);
    }

    public static double SigmaCalc(Object object, Method method, int start, int end, Double[] params)
            throws IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        double sum = 0;
        Double[] parameters = new Double[1 + params.length];
        for (int i = 0; i < params.length; i++) {
            parameters[i + 1] = params[i];
        }

        for (double i = start; i < (end + 1); i++) {
            parameters[0] = (Double) i;
            sum = sum + (double) method.invoke(object, parameters);
        }
        System.out.println(sum);
        return sum;
    }
}
