import java.util.*;
import java.io.*;

public class Main {
    static int N, M, maxSafeArea = 0;
    static int[][] originalMap;
    static List<int[]> emptySpaces = new ArrayList<>();
    static List<int[]> virusList = new ArrayList<>();
    static int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        originalMap = new int[N][M];

        // 입력 및 빈칸, 바이러스 위치 저장
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                originalMap[r][c] = Integer.parseInt(st.nextToken());

                if (originalMap[r][c] == 0) {
                    emptySpaces.add(new int[]{r, c});
                } else if (originalMap[r][c] == 2) {
                    virusList.add(new int[]{r, c});
                }
            }
        }

        // 빈칸 중 3개 선택해서 벽 세우기 (조합)
        int size = emptySpaces.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                for (int k = j + 1; k < size; k++) {
                    int[][] mapCopy = deepCopyMap(originalMap);

                    int[] w1 = emptySpaces.get(i);
                    int[] w2 = emptySpaces.get(j);
                    int[] w3 = emptySpaces.get(k);

                    mapCopy[w1[0]][w1[1]] = 1;
                    mapCopy[w2[0]][w2[1]] = 1;
                    mapCopy[w3[0]][w3[1]] = 1;

                    spreadVirus(mapCopy);
                    countSafe(mapCopy);
                }
            }
        }

        System.out.println(maxSafeArea);
    }

    // 깊은 복사
    static int[][] deepCopyMap(int[][] map) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            copy[i] = map[i].clone();
        }
        return copy;
    }

    // 바이러스 퍼뜨리기 (BFS, visited 없이 2로 감염 표시)
    static void spreadVirus(int[][] map) {
        Queue<int[]> q = new LinkedList<>();

        for (int[] virus : virusList) {
            q.offer(new int[]{virus[0], virus[1]});
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr >= 0 && nr < N && nc >= 0 && nc < M && map[nr][nc] == 0) {
                    map[nr][nc] = 2;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }

    // 안전 구역 세기
    static void countSafe(int[][] map) {
        int safe = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 0) safe++;
            }
        }
        maxSafeArea = Math.max(maxSafeArea, safe);
    }
}
