import java.io.*;
import java.util.*;

public class Main {
    static int shortcutCnt;
    static int totalLen;
    static int minLen;

    static int[][] shortcut;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 지름길 개수, 최소 길이 변수
        shortcutCnt = Integer.parseInt(st.nextToken());
        totalLen = Integer.parseInt(st.nextToken());
        minLen = totalLen;

        // 지름길 입력
        shortcut = new int[shortcutCnt][3];

        for (int i = 0; i < shortcutCnt; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            shortcut[i][0] = start;
            shortcut[i][1] = end;
            shortcut[i][2] = len;
        }

        Arrays.sort(shortcut, Comparator.comparingInt(a -> a[0]));

        // 모든 경우의 수 완전탐색
        for (int n = 0; n < shortcutCnt; n++) {
            bruteForce(n, 0, 0);
        }

        System.out.println(minLen);
    }

    public static void bruteForce(int n, int current, int sum) {

        // 기저 조건
        if (n == shortcutCnt) {
            if (current < totalLen) sum += totalLen - current;
            minLen = Math.min(minLen, sum);
            return;
        }

        // 현재 지름길을 지나친 경우
        int s = shortcut[n][0];
        int e = shortcut[n][1];
        int sl = shortcut[n][2]; // 지름길 길이
        int l = shortcut[n][1] - shortcut[n][0]; // 도착 위치 - 시작 위치
        if (s < current || e > totalLen) {
            bruteForce(n + 1, current, sum);
        } else {
            int shortcutStart = shortcut[n][0];
            int shortcutEnd = shortcut[n][1];
            int shortcutLen = shortcut[n][2];

            bruteForce(n + 1, shortcutEnd, sum + shortcutLen + shortcutStart - current);
            bruteForce(n + 1, current, sum);
        }
    }
}

