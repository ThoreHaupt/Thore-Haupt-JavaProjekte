package RP.W6.A2;

public class Zutat {
    private String name;
    private int weightGramm;
    
    public Zutat(String name, int weightGramm) {
        this.name = name;
        this.weightGramm = weightGramm;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getWeightGramm() {
        return weightGramm;
    }
    public void setWeightGramm(int weightGramm) {
        this.weightGramm = weightGramm;
    }

}
