import java.util.*;
import java.io.*;

public class Main {
    static List<int[]> virusList = new ArrayList<>();
    static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};
    static int width, height, maxSafeArea = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 세로 길이, 가로 길이 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        int[][] map = new int[height][width];

        // map 입력 받기
        for (int r = 0; r < height; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < width; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());

                // map[r][c] == 2인 경우 virusList에 추가
                if (map[r][c] == 2) {
                    virusList.add(new int[] {r, c});
                }

            }
        }

        makeWall(0, map);

        System.out.println(maxSafeArea);
    }

    static void makeWall(int wallCnt, int[][] map) {
        // 벽 3개를 모두 만들었을 때 기저조건
        if (wallCnt == 3) {
            countSafeArea(map);
            return;
        }

        // 2중 for문으로 벽 지을 수 있는 곳 찾기
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                // 벽을 지을 수 있는 공간
                if (map[r][c] == 0) {
                    map[r][c] = 1;
                    makeWall(wallCnt + 1, map);
                    map[r][c] = 0;
                }
            }
        }
    }

    static void countSafeArea(int[][] map) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[height][width];

        for (int[] virus : virusList) {
            visited[virus[0]][virus[1]] = true;
            queue.add(virus);
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // 더 이상 이동할 수 없는 경우
                if (nr < 0 || nr >= height || nc < 0 || nc >= width || map[nr][nc] == 1 || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                queue.add(new int[] {nr, nc});
            }
        }

        int area = 0;

        // 최대 안전 영역 구하기
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                // map[r][c] == 0인 경우 안전 영역
                if (map[r][c] == 0 && !visited[r][c]) {
                    area++;
                }

            }
        }

        maxSafeArea = Math.max(maxSafeArea, area);
    }
}