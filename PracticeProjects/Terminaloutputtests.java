package PracticeProjects;

public class Terminaloutputtests {
    public static void main(String[] args){
        
        Progressbart pb = new Progressbart("PracticeProjects/Textfiles/Console.txt", 3, "Test");
        pb.update(10);
        
    }
    public static void deletelastterminaloutputline(){
        int count = 1;
        System.out.print(String.format("\033[%dA", count)); // Move up
        System.out.print("\033[2K"); // Erase line content
    }
}
