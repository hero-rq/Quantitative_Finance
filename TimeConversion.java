package cookie;

import java.util.Scanner;
/*. Design and implement a Java program that asks the user to enter an integer number
of seconds and converts it into the format of hours, minutes and seconds. Print
this equivalent number of seconds using a formatting rule like HH:MM:SS. Save the
program as TimeConversion.java.*/

import java.util.Scanner;
/*. Design and implement a Java program that asks the user to enter an integer number
of seconds and converts it into the format of hours, minutes and seconds. Print
this equivalent number of seconds using a formatting rule like HH:MM:SS. Save the
program as TimeConversion.java.*/

public class TimeConversion {
    public static void main(String[] args) {
        int total;
        Scanner user = new Scanner(System.in);
        int h, m, s;

        System.out.print("please tell me the total seconds : ");
        total = user.nextInt();

        h = total / 3600;
        total -= h * 3600;
        m = total / 60;
        total -= m * 60;
        s = total;
        System.out.printf("%02d:%02d:%02d%n", h, m, s);
    }
}
