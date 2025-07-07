import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N 입력
        char[] word = br.readLine().toCharArray();
        int strLen = word.length;

        for (int i = 0; i < word.length; i++) {
            char c = word[i];

            if (c < 'a' || c > 'z') {
                strLen--;
            } else if (c == 'j' && i > 0) {
                char pre = word[i - 1];
                if (pre == 'n' || pre == 'l') strLen--;
            } else if (c == 'd' && i < word.length - 2) {
                if (word[i + 1] == 'z' && word[i + 2] == '=') strLen--;
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(strLen));
        bw.flush();
    }
}