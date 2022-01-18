package RP.W8.A5;

public class PalindromClass {

    public static void main(String[] args) {
        char[] test1 =  new char[ ]{'B', 'N', 'N', 'A'};
        System.out.println(istPalindrom(test1));
    }



    public static boolean istPalindrom(char[] inputArray){
        if(inputArray == null){
            return false;
        } else {
            return isPalindromCalculation(inputArray);
        }
    }



    public static boolean isPalindromCalculation(char[] input) {
        char[] lowerInputArray = lowerCharactersArray(input.clone());
        char[] inverseArray = inverseCharArray(lowerInputArray);
        return compareCharArrays(inverseArray, lowerInputArray);
    }

    private static char[] lowerCharactersArray(char[] clone) {
        for (int i = 0; i < clone.length; i++) {
            clone[i] = Character.toLowerCase(clone[i]);
        }
        return clone;
    }



    public static char[] inverseCharArray(char[] arr) {
        char[] arr2 = new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr2[arr2.length - 1 - i] = arr[i];
        }
        return arr2;
    }

    public static boolean compareCharArrays(char[] arr1, char[] arr2) {
        for (int i = 0; i < arr2.length; i++) {
            if (arr1[i] != arr2[i])
                return false;
        }
        return true;
    }
}
