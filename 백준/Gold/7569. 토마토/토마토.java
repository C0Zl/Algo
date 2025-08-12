import java.io.*;
import java.util.*;

public class Main {
    // 위, 아래, 왼쪽, 오른쪽, 앞, 뒤
    static int[] dr = {0, 0, 0, -1, 0, 1}, dc = {0, 0, -1, 0, 1, 0}, dh = {1, -1, 0, 0, 0, 0};

    public static class Node {
        int r, c, h, day;

        Node(int h, int r, int c, int day) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.day = day;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // m, n, z 입력 받기
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int z = Integer.parseInt(st.nextToken());

        // 정답을 답을 변수
        int answer = 0;

        // 익은 토마토를 넣을 queue
        Queue<Node> ripeQueue = new LinkedList<>();

        int[][][] box = new int[z][n][m];
        boolean[][][] visited = new boolean[z][n][m];

        // 토마토 상자 입력 받기
        for (int h = 0; h < z; h++) {
            for (int r = 0; r < n; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < m; c++) {
                    box[h][r][c] = Integer.parseInt(st.nextToken());

                    // 익은 토마토 queue에 넣기
                    if (box[h][r][c] == 1) {
                        visited[h][r][c] = true;
                        ripeQueue.offer(new Node(h, r, c, 0));
                    }
                }
            }
        }

        // bfs
        while(!ripeQueue.isEmpty()) {
            Node curTomato = ripeQueue.poll();
            answer = Math.max(curTomato.day, answer);

            for (int d = 0; d < 6; d++) {
                int nh = curTomato.h + dh[d];
                int nr = curTomato.r + dr[d];
                int nc = curTomato.c + dc[d];

                // 범위 밖이거나, 안익은 토마토가 아니거나, 이미 방문한 적 있다면 pass
                if (nh < 0 || nh  >= z || nr < 0 || nr >= n || nc < 0 || nc >= m
                        || box[nh][nr][nc] != 0 || visited[nh][nr][nc]) continue;

                // 안익은 토마토인 경우
                box[nh][nr][nc] = 1;
                visited[nh][nr][nc] = true;
                ripeQueue.offer(new Node(nh, nr, nc, curTomato.day + 1));
            }
        }

        // 토마토 상자 확인하기
        for (int h = 0; h < z; h++) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    // 안익은 토마토가 있다면 불가능
                    if (box[h][r][c] == 0) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        System.out.println(answer);
    }
}
