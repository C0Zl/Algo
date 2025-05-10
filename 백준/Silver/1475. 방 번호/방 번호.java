import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        char[] numArr = String.valueOf(sc.nextInt()).toCharArray();

        int[] numCnt = new int[10];

        for (char num : numArr) {
            int n = num - '0';
            numCnt[n]++;
        }

        int max = (int) Math.ceil((double) (numCnt[6] + numCnt[9]) / 2);

        for (int i = 0; i < numCnt.length; i++) {
            if (i == 6 || i == 9) continue;
            max = Math.max(max, numCnt[i]);
        }

        System.out.println(max);
    }
}