import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] solution = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            solution[i] = Integer.parseInt(st.nextToken());
        }

        int[] minDiff = {0, 0, Integer.MAX_VALUE};
        int left = 0, right = n - 1;

        while (left < right) {
            int sum = solution[left] + solution[right];
            int sumAbs = Math.abs(sum);

            // 현재 최소 절댓값보다 합의 절댓값이 더 작은 경우
            if (minDiff[2] > sumAbs) {
                minDiff[0] = solution[left];
                minDiff[1] = solution[right];
                minDiff[2] = sumAbs;
            }

            int leftAbs = Math.abs(solution[left]);
            int rightAbs = Math.abs(solution[right]);

            if (leftAbs < rightAbs) right--;
            else left++;
        }

        System.out.println(minDiff[0] + " " + minDiff[1]);
    }
}