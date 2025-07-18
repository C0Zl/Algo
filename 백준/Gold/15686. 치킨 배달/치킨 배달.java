import java.util.*;
import java.io.*;

public class Main {
    static List<int[]> chickenList = new ArrayList<>();
    static List<int[]> houseList = new ArrayList<>();
    static int n, m, minDist = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 집과 치킨집 정보 입력 받기
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());

            for (int c = 0; c < n; c++) {
                int currentNum = Integer.parseInt(st.nextToken());

                // 집
                if (currentNum == 1) {
                    houseList.add(new int[] {r, c});
                }
                // 치킨집
                else if (currentNum == 2) {
                    chickenList.add(new int[] {r, c});
                }
            }
        }

        // 조합으로 m개 치킨집 고르기
        comb(0, new ArrayList<>());

        System.out.println(minDist);
    }

    static void comb(int start, List<int[]> selected) {
        if (selected.size() == m) {
            // 치킨거리 구하기
            calDist(selected);
            return;
        }

        for (int i = start; i < chickenList.size(); i++) {
            selected.add(chickenList.get(i));
            comb(i + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }

    static void calDist(List<int[]> selected) {
        int sum = 0;

        for (int[] house : houseList) {
            int min = Integer.MAX_VALUE;
            for (int[] chicken : selected) {
                int dist = Math.abs(chicken[0] - house[0]) + Math.abs(chicken[1] - house[1]);
                min = Math.min(min, dist);
            }
            sum += min;
        }

        minDist = Math.min(minDist, sum);
    }
}