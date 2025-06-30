import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Stack<Integer> stack = new Stack<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int countOfCommand = Integer.parseInt(st.nextToken());

        for (int i = 0; i < countOfCommand; i++) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();
            int x = 0;
            if (st.hasMoreTokens()) x = Integer.parseInt(st.nextToken());

            switch (command) {
                case "push" :
                    stack.push(x);
                    break;
                case "pop" :
                    bw.write((stack.isEmpty() ? "-1" : stack.pop()) + "\n");
                    break;
                case "size" :
                    bw.write(stack.size() + "\n");
                    break;
                case "empty" :
                    bw.write((stack.isEmpty() ? "1" : "0") + "\n");
                    break;
                case "top" :
                    bw.write((stack.isEmpty() ? "-1" : stack.peek()) + "\n");
            }
        }
        bw.flush();
    }
}
