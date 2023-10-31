import java.util.Arrays;
import java.util.Scanner;

public class CombiningArray {
    public static void main(String[] args) {
        Scanner user_input = new Scanner(System.in);

        int s = user_input.nextInt();
        int k = user_input.nextInt();

        int[] array1 = new int[s];
        int[] array2 = new int[k];

        // Reading values for array1
        for(int i = 0; i < array1.length; i++){
            array1[i] = user_input.nextInt();
        }

        // Reading values for array2
        for(int i = 0; i < array2.length; i++){
            array2[i] = user_input.nextInt();
        }

        // Merging and sorting the arrays
        int[] array3 = new int[s + k];
        System.arraycopy(array1, 0, array3, 0, s);
        System.arraycopy(array2, 0, array3, s, k);
        Arrays.sort(array3);

        // Printing the sorted merged array
        for(int i = 0; i < array3.length; i++){
            System.out.print(array3[i] + " ");
        }
    }
}
