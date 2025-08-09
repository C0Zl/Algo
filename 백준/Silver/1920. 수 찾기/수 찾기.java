import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 시작 배열
        int[] initArr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            initArr[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        // 정수 배열
        int[] numArr = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int j = 0; j < m; j++) {
            numArr[j] = Integer.parseInt(st.nextToken());
        }

        // 시작 배열 정렬
        Arrays.sort(initArr);

        for (int num : numArr) {
            int left = 0, right = n - 1;
            boolean found = false;

            while (left <= right) {
                int mid = (left + right) / 2;
                int target = initArr[mid];

                if (target == num) {
                    found = true;
                    break;
                } else if (target < num) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }

            System.out.println(found ? 1 : 0);
        }
    }
}
