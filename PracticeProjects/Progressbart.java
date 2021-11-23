package PracticeProjects;

/**
 *  Progressbar implementet in a file specific line
 * 
 */
public class Progressbart {
    
    /**
     * Hello!
     * @param path
     * @param progress
     * @param line
     * @param prefix
     * @param prefixlength = 32
     */
    public Progressbart(String path, int line, String prefix) {
        this.path = path;
        this.progress = 0;
        this.line = line;
        this.prefix = prefix;
        this.prefixlength = 32;
    }

    String path;
    double progress;
    int progressint;
    int line;
    String prefix;
    int prefixlength;
    String currentline;

    public void update(double update){
        int oldint = progressint;
        progress += update;
        progressint = (int) (progress / 10);
        if (progressint != oldint){
            Filemanager.writeToFileLine(path, line, generateLine());
        }
        
    }

    public String generateLine(){
        String format = "%-" + prefixlength + "s%" + prefixlength + "s";
        String fillstate = ""; 
        for (int i = 0; i < 10; i++) {
           fillstate +=  i >= progressint ? " " : "\u2592";
        }

        String bar = "|" + fillstate + "|";
        currentline = String.format(format, prefix, bar); 
        return currentline;
    }
}
