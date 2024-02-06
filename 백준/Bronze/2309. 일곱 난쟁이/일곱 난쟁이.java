import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> height = new ArrayList<>();
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            height.add(Integer.parseInt(br.readLine()));
        }

        for (int i: height) {
            sum += i;
        }

        out : for (int i : height) {
            for (int j : height) {
                if (i == j) {
                    continue;
                }
                if ((sum - i) - j == 100) {
                    height.remove(Integer.valueOf(i));
                    height.remove(Integer.valueOf(j));
					break out;
                }
            }
        }

        Collections.sort(height);

        for (Integer i : height) {
            System.out.println(i);

        }
    }
}