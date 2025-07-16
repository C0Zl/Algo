import java.util.*;
import java.io.*;

public class Main {
    static int minCnt, numLen, channel;
    static char[] numArr;
    static boolean[] usable;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // n, m 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        channel = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int wrongButtonCount = Integer.parseInt(st.nextToken());

        // 사용가능한 숫자 리스트
        usable = new boolean[10];
        Arrays.fill(usable, true);

        minCnt = Math.abs(channel - 100);

        if (wrongButtonCount > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < wrongButtonCount; i++) {
                int wrongIndex = Integer.parseInt(st.nextToken());
                usable[wrongIndex] = false;
            }

            if (wrongButtonCount == 10) {
                System.out.println(minCnt);
                return;
            }
        }

        // 목표 채널을 문자 배열로 변환
        numArr = Integer.toString(channel).toCharArray();
        // 채널 길이
        numLen = numArr.length;

        // +,-로만 이동했을 경우의 최솟값

        for (int i = 0; i <= 9; i++) {
            if (usable[i]) {
                makeNum(1, i); // 첫 자릿수에서 시작
            }
        }

        System.out.println(minCnt);
    }

    
    static void makeNum(int len, int num) {
        if (len > 6) return; // 최대 6자리까지만

        // 숫자를 하나라도 만들었으면 비교
        if (len > 0) {
            int pressCount = len + Math.abs(channel - num);
            minCnt = Math.min(minCnt, pressCount);
        }

        for (int i = 0; i <= 9; i++) {
            if (usable[i]) {
                makeNum(len + 1, num * 10 + i);
            }
        }
    }

}