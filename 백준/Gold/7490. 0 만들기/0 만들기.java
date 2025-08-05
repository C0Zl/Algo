import java.io.*;

public class Main {
    static char[] operator = {' ', '+', '-'};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 전체 테스트 케이스 개수
        int testcase = Integer.parseInt(br.readLine());

        // 테스트 케이스만큼 반복
        for (int i = 0; i < testcase; i++) {
            // 자연수 n
            int n = Integer.parseInt(br.readLine());
            char[] c = new char[n - 1];
            insert(0, n - 1, c);

            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void insert(int cur, int n, char[] operArr) {
        if (cur == n) {
            // 계산 결과가 0인 경우 출력
            if (calculate(operArr) == 0) {
                for(int i = 1; i <= n; i++) {
                    sb.append(i).append(operArr[i - 1]);
                }
                sb.append(n+1).append("\n");
            }

            return;
        }

        for (char o : operator) {
            operArr[cur] = o;
            insert(cur+1, n, operArr);
        }
    }

    static int calculate(char[] operArr) {
        // 1 ~ n까지 배열 생성
        int[] numArr = new int[operArr.length + 1];
        for (int n = 0; n < numArr.length; n++) {
            numArr[n] = n+1;
        }

        // 연산자 확인하면서 숫자 배열 부호 더하기
        for (int i = 0; i < operArr.length; i++) {
            // 현재 연산자
            char oper = operArr[i];

            if (oper == '-') {
                numArr[i+1] = -numArr[i+1];
            } else if (oper == ' ') {
                int temp = i;
                while (temp >= 0 && operArr[temp] == ' ') {
                    numArr[temp] *= 10;
                    temp--;
                }
                if (temp >= 0 && operArr[temp] == '-') numArr[i+1] = -numArr[i+1];
            }
        }

        int sum = 0;
        for (int num : numArr) {
            sum += num;
        }

        return sum;
    }
}