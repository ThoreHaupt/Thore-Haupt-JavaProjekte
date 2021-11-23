package PracticeProjects;

public class Terminaloutputtests {
    public static void main(String[] args){
        int count = 1;
        System.out.println("hello my dude");
        System.out.println("Sup my mans");
        System.out.print(String.format("\033[%dA", count)); // Move up
        System.out.print("\033[2K"); // Erase line content
        System.out.println("Sup my bananas");
    }
}
