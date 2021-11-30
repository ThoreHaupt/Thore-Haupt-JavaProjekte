package PracticeProjects;

public class myGenerics<T> {
    
    public static void main(String[] args) {
       
        myGenerics<Integer> ob = new myGenerics<>();
        ob.hello(128);
    }

    public <T> void hello(T i){
        System.out.println(i.getClass().getName());
    }
}
