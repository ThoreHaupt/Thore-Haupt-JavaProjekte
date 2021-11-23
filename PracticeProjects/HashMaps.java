package PracticeProjects;

import java.util.HashMap;

public class HashMaps {
    
    public static HashMap<String, Person> peopleHash = new HashMap<String,Person>();

    public static void doPersonStuff(){
        
        HashMap<Integer, String> map = new HashMap<Integer, String>(); 

        map.put(3,"GutenTag!");
        Person[] people = new Person[4];
        people[0] = new Person();
        people[1] = new Person(new String[] {"Herman","Biel"});
        people[2] = new Person(new String[] { "Anke", "Haupt" });
        people[3] = new Person(new String[] { "Thore", "Haupt" });
        
        addPersonHashfromArray(people);

        for (String name : peopleHash.keySet()) {
            System.out.println(name + ": "+ peopleHash.get(name));
        }
    }

    public static HashMap<String, Person> addPersonHashfromArray(Person[] list){
        for (Person person : list) {
           HashMaps.peopleHash.put(person.getStandardizedName(), person);
        }
        return HashMaps.peopleHash;
    }

}