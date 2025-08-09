import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int goodNumCnt = 0;

        st = new StringTokenizer(br.readLine());

        // 최초 배열
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 배열 정렬
        Arrays.sort(arr);

        int cur, left, right, sum;

        for (int i = 0; i < n; i++) {
            cur = arr[i];

            left = 0;
            right = n - 1;

            while (left < right) {
                if (left == i) {
                    left++;
                    continue;
                }
                if (right == i) {
                    right--;
                    continue;
                }

                sum = arr[left] + arr[right];

                // 두 수의 합이 현재 값과 같으면, good Number
                if (sum == cur) {
                    goodNumCnt++;
                    break;
                } else if (sum < cur) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(goodNumCnt);
    }
}