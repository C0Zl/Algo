import java.util.*;
import java.io.*;

public class Main {
    static boolean[][] visited;
    static int[][] map;
    static int maxValue = 0, n, m;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 세로 크기 : n, 가로 크기 : m
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        // map 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // dfs
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(1, map[i][j], i, j);
                visited[i][j] = false;
            }
        }

        System.out.println(maxValue);
    }

    static void dfs(int count, int sum, int r, int c) {
        // 기저조건
        if (count == 4) {
            maxValue = Math.max(sum, maxValue);
            return;
        }

        int nr, nc;

        for (int d = 0; d < 4; d++) {
            nr = r + dr[d];
            nc = c + dc[d];

            // 배열 범위를 벗어났거나 이미 방문한 경우 예외처리
            if (nr < 0 || nr >= n || nc < 0 || nc >= m || visited[nr][nc]) continue;

            // 'ㅗ' 모양 블럭을 위해 3번째 칸에서 한번 더 검사
            if (count == 2) {
                visited[nr][nc] = true;
                dfs(count + 1, sum + map[nr][nc], r, c);
                visited[nr][nc] = false;
            }

            visited[nr][nc] = true;
            dfs(count + 1, sum + map[nr][nc], nr, nc);
            visited[nr][nc] = false;
        }
    }
}