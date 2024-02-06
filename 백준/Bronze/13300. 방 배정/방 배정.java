import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] student = new int[2][7];
        int room = 0;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int grade = Integer.parseInt(st.nextToken());

            student[gender][grade]++;
        }

        for (int j = 0; j < 2; j++) {
            for(int l = 1; l < 7; l++) {
                if (student[j][l] > 0) {
                    if (student[j][l] % M == 0) {
                        room += (student[j][l] / M);
                    } else {
                        room += (student[j][l] / M) + 1;
                    }
                }
            }
        }

        System.out.print(room);
    }
}