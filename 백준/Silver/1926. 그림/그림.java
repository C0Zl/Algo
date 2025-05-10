import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr;
    static boolean[][] visited;
    static int n, m, max;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m];

        int answer = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // BFS
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    answer++;
                    max = Math.max(max, bfs(i, j));
                }
            }
        }

        System.out.println(answer);
        System.out.println(max);
    }

    static public int bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {r, c});
        int cnt = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            cnt++;
            int cr = cur[0];
            int cc = cur[1];
            visited[cr][cc] = true;

            for (int d = 0; d < 4; d++) {
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= m || arr[nr][nc] != 1 || visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;
                queue.add(new int[] {nr, nc});
            }
        }

        return cnt;
    }
}