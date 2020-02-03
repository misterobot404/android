import java.util.Scanner;

// Дано число X. Требуется перевести это число в римскую систему счисления.

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String number = in.nextLine();
        String res = "";

        if(number.length() == 3)
            if (number.charAt(0) > 0) {
                if (number.charAt(0) == '1'){
                    res+="C";
                }
                if (number.charAt(0) == '2'){
                    res+="CC";
                }
                if (number.charAt(0) == '3'){
                    res+="CCC";
                }
                if (number.charAt(0) == '4'){
                    res+="CD";
                }
                if (number.charAt(0) == '5'){
                    res+="D";
                }
                if (number.charAt(0) == '6'){
                    res+="DC";
                }
                if (number.charAt(0) == '7'){
                    res+="DCC";
                }
                if (number.charAt(0) == '8'){
                    res+="DCCC";
                }
                if (number.charAt(0) == '9'){
                    res+="CM";
                }
                number = number.substring(1);
            }

        if(number.length() == 2)
            if (number.charAt(0) > 0) {
                if (number.charAt(0) == '1'){
                    res+="X";
                }
                if (number.charAt(0) == '2'){
                    res+="XX";
                }
                if (number.charAt(0) == '3'){
                    res+="XXX";
                }
                if (number.charAt(0) == '4'){
                    res+="XL";
                }
                if (number.charAt(0) == '5'){
                    res+="L";
                }
                if (number.charAt(0) == '6'){
                    res+="LX";
                }
                if (number.charAt(0) == '7'){
                    res+="LXX";
                }
                if (number.charAt(0) == '8'){
                    res+="LXXX";
                }
                if (number.charAt(0) == '9'){
                    res+="XC";
                }
                number = number.substring(1);
            }

        if(number.length() == 1)
            if (number.charAt(0) > 0) {
                if (number.charAt(0) == '1'){
                    res+=("I");
                }
                if (number.charAt(0) == '2'){
                    res+=("II");
                }
                if (number.charAt(0) == '3'){
                    res+=("III");
                }
                if (number.charAt(0) == '4'){
                    res+=("IV");
                }
                if (number.charAt(0) == '5'){
                    res+=("V");
                }
                if (number.charAt(0) == '6'){
                    res+=("VI");
                }
                if (number.charAt(0) == '7'){
                    res+=("VII");
                }
                if (number.charAt(0) == '8'){
                    res+=("VIII");
                }
                if (number.charAt(0) == '9'){
                    res+=("IX");
                }
            }
        System.out.printf("%s", res);
        in.close();
    }
}