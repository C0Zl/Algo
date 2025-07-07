import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N 입력
        int N = Integer.parseInt(br.readLine());
        int n = N, minCnt = n / 5;
        n %= 5;

        while (n > 0) {
            // 현재 3으로 나누어 떨어지는지 확인
            if (n % 3 == 0) {
                minCnt += n / 3;
                System.out.println(minCnt);
                return;
            } else {
                n += 5;
                minCnt--;

                if (n > N) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(minCnt);
    }
}