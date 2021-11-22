package TryingNewThings;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person {

    String[] name;
    int age;
    LocalDate birthday;
    LocalDateTime createdProfile;
    int hight; 
    String department = "Forschung";

    public static void main(String[] args){
        Person Yannick = new Person(new String[]{"Yannick", "Nocus"});
        System.out.println(Yannick);
    }


    public Person(String[] name, int age, LocalDate birthday, int hight, String department) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.hight = hight;
        this.department = department;
        this.createdProfile = LocalDateTime.now();
        HashMaps.peopleHash.put(getStandardizedName(), this);
    }

    public Person(String[] name) {
        this.name = name;
    }

    public Person(){
        this.name = new String[]{"John", "Dough"};
        this.age = 0;
        this.birthday = null;
        this.hight = 0;
        this.department = "";}
    
     
    @Override
    public String toString(){
        return (this.name[0] +" " + this.name[name.length-1] + ": " + age + " years old.");
    }

    public String getStandardizedName(){
        return (this.name[0]+this.name[this.name.length - 1]);
    }
}
