package RP.alteAufgaben.J1718;

public class A1 {
    public static void main(String[] args) {
        System.out.println(5*3);
        A3();
    }

    public static String verdoppel(String input){
        return input + input;
    }

    public static void A3(){
        int i = 0;
        while(i<50){
            System.out.println((i+1) + "java");
            i++;
        }
    }

    public static void VocalChecker(char input){
        String result = switch(input){
            case 'A': yield ("true");
            case 'E': yield ("true");
            case 'I': yield ("true");
            case 'O': yield ("true");
            case 'U': yield ("true");
            default: yield "false";
        };
        System.out.println(result);
    }
}
