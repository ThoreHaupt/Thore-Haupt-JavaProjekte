package PracticeProjects;

public class Terminaloutputtests {
    public static void main(String[] args){
        
        Progressbart pb = new Progressbart("PracticeProjects/Textfiles/Console.txt", "Test");
        pb.update(55);
        Progressbart pb1 = new Progressbart("PracticeProjects/Textfiles/Console.txt", "Test");
        pb1.update(50);
        
    }
    
    public static void deletelastterminaloutputline(){
        int count = 1;
        System.out.print(String.format("\033[%dA", count)); // Move up
        System.out.print("\033[2K"); // Erase line content
    }
}
