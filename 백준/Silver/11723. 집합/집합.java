import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int bitSet = 0;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numOfCalculation = Integer.parseInt(st.nextToken());

        for (int i = 0; i < numOfCalculation; i++) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();
            int targetNum = 0;
            if (st.hasMoreTokens()) targetNum = Integer.parseInt(st.nextToken());

            if (command.equals("add")) {
                bitSet |= (1 << (targetNum - 1));
            } else if (command.equals("remove")) {
                bitSet &= ~(1 << (targetNum - 1));
            } else if (command.equals("check")) {
                bw.write((bitSet & (1 << (targetNum - 1))) != 0 ? "1" : "0");
                bw.newLine();
            } else if (command.equals("toggle")) {
                bitSet ^= (1 << (targetNum - 1));
            } else if (command.equals("all")) {
                bitSet = (1 << 20) - 1;
            } else if (command.equals("empty")) {
                bitSet = 0;
            }
        }

        bw.flush();
    }
}
