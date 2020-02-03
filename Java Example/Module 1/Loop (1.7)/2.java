package com.company;
import java.util.Scanner;

// Напишите программу, которая вводит с клавиатуры номер месяца и день, и определяет, сколько дней осталось до Нового года.
// При вводе неверных данных должно быть выведено сообщение об ошибке. Считается, что год невисокосный.

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int month = in.nextInt();
        int day = in.nextInt();

        if(!dayMonthValid(month,day)) {
            System.out.printf("%d", -1);
            in.close();
        }
        else {
            int res = 0;
            for (int i = 12; i >= month; i--) res += getDayByMonth(i);

            System.out.printf("%d", res-day);
            in.close();
        }
    }
    private static int getDayByMonth(int month) {
        int res = 0;
        switch(month) {
            case 2: res = 28;
                break;

            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: res = 31;
                break;

            case 4:
            case 6:
            case 9:
            case 11: res = 30;
                break;
        }
        return res;
    }
    private static boolean dayMonthValid(int month, int day) {
        switch(month) {
            case 2: {
                if (day < 1 || day > 28)
                    return false;
            } break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: {
                if (day < 1 || day > 31)
                    return false;
            } break;
            case 4:
            case 6:
            case 9:
            case 11:{
                if (day < 1 || day > 30)
                    return false;
            } break;
            default:
                return false;
        }
        return true;
    }
}