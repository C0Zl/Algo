import java.io.*;
import java.util.*;

public class Main {
    public static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int moveCnt = 0;

        int[] dr = {-1, 0, 1, 0}, dc = {0, -1, 0, 1};

        int n = Integer.parseInt(st.nextToken());
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        boolean[][] visited;
        boolean move;
        Queue<Node> queue;

        // map 입력
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            move = false;
            visited = new boolean[n][n];
            queue = new LinkedList<>();

            // 배열 탐색
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    // 방문한적 있으면 pass
                    if (visited[r][c]) continue;

                    // 연합 정보
                    int share = 1, sharePeopleCnt = map[r][c];
                    List<Node> shareCountryList = new ArrayList<>();
                    shareCountryList.add(new Node(r, c));

                    // 현재 위치 queue에 넣고 주변 탐색
                    queue.offer(new Node(r, c));
                    visited[r][c] = true;

                    // queue가 비었을 때까지 bfs
                    while (!queue.isEmpty()) {
                        Node cur = queue.poll();

                        for (int d = 0; d < 4; d++) {
                            int nr = cur.r + dr[d];
                            int nc = cur.c + dc[d];

                            if (nr >= 0 && nr < n && nc >= 0 && nc < n && !visited[nr][nc]) {
                                int diff = Math.abs(map[cur.r][cur.c] - map[nr][nc]);
                                if (left <= diff && diff <= right) {
                                    queue.offer(new Node (nr, nc));
                                    visited[nr][nc] = true;
                                    share++;
                                    sharePeopleCnt += map[nr][nc];
                                    shareCountryList.add(new Node(nr, nc));
                                }

                            }
                        }
                    }

                    // 연합이 있는 경우
                    if (share > 1) {
                        move = true;
                        int meanPeople = sharePeopleCnt / share;

                        for (Node country : shareCountryList) {
                            map[country.r][country.c] = meanPeople;
                        }
                    }
                }
            }

            if (!move) {
                break;
            }
            moveCnt++;
        }

        System.out.println(moveCnt);
    }
}
