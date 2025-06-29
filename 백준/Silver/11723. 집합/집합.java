import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        boolean[] set = new boolean[20];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfCalculation = Integer.parseInt(st.nextToken());

        for (int i = 0; i < numOfCalculation; i++) {
            String[] line = br.readLine().split(" ");

            String command = line[0];
            int targetNum = 0;
            if (line.length > 1) targetNum = Integer.parseInt(line[1]) - 1;

            // 명령어가 add인 경우
            if (command.equals("add")) {
                set[targetNum] = true;
            } else if (command.equals("remove")) {
                set[targetNum] = false;
            } else if (command.equals("check")) {
                bw.write(String.valueOf(set[targetNum] ? 1 : 0));
                bw.newLine();
            } else if (command.equals("toggle")) {
                set[targetNum] = !set[targetNum];
            } else if (command.equals("all")) {
                for (int n = 0; n < 20; n++) {
                    set[n] = true;
                }
            } else if (command.equals("empty")) {
                set = new boolean[20];
            }
        }

        bw.close();
    }
}
