import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 정수의 개수 n
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        // 각 정수 입력받기
        st = new StringTokenizer(br.readLine());

        // stack 선언
        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());

            // 스택이 비어있지 않고, 스택의 높이보다 현재가 더 높은 경우
            while (!stack.isEmpty() && stack.peek()[0] < height) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                sb.append(0).append(" ");
            } else {
                sb.append(stack.peek()[1]).append(" ");
            }
            stack.push(new int[] {height, i + 1});
        }

        System.out.println(sb);
    }
}

