import java.io.*;
import java.util.*;

public class Main {
    static final char[] colors = {'R', 'B'};
    static int minMove = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        char[] balls = st.nextToken().toCharArray();

        for (char c : colors) {
            int move = 0;
            boolean diff = false;

            // 오른쪽에 모이는 경우
            for (int i = N - 1; i >= 0; i--) {
                if (balls[i] != c) {
                    diff = true;
                } else if (diff) {
                    move++;
                }
            }

            minMove = Math.min(minMove, move);
            // 초기화
            move = 0; diff = false;

            // 왼쪽에 모이는 경우
            for (int i = 0; i < N; i++) {
                if (balls[i] != c) {
                    diff = true;
                } else if (diff) {
                    move++;
                }
            }

            minMove = Math.min(minMove, move);
        }

        if (minMove == Integer.MAX_VALUE) minMove = 0;
        System.out.println(minMove);
    }
}

