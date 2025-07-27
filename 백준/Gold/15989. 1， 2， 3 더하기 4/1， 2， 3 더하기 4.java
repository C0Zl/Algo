import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int testcase = Integer.parseInt(st.nextToken());

        for (int i = 0; i < testcase; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            // 경우의 수 : 1로만 만드는 경우의 수
            int caseCnt = 1;

            // 1) 1, 3으로만 만들 수 있는 경우의 수
            caseCnt += n / 3;

            // 2) 1, 2로만 만들 수 있는 경우의 수
            caseCnt += n / 2;

            // 3) 1, 2, 3으로 만들 수 있는 경우의 수
            int copyNum = n % 3;

            while (copyNum + 3 <= n) {
                caseCnt += copyNum / 2;
                copyNum += 3;
            }

            sb.append(caseCnt + "\n");
        }

        System.out.println(sb);
    }
}

