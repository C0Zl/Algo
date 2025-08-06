import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int r, c, cost;

        public Node(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int testcase = 1;

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            int[][] map = new int[n][n];
            int[][] dist = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            PriorityQueue<Node> pq = new PriorityQueue<>();
            dist[0][0] = map[0][0];
            pq.offer(new Node(0, 0, dist[0][0]));

            while (!pq.isEmpty()) {
                Node cur = pq.poll();

                if (cur.cost > dist[cur.r][cur.c]) continue;

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                        int newCost = dist[cur.r][cur.c] + map[nr][nc];
                        if(newCost < dist[nr][nc]) {
                            dist[nr][nc] = newCost;
                            pq.offer(new Node(nr, nc, newCost));
                        }
                    }
                }
            }
            sb.append("Problem ").append(testcase++).append(": ").append(dist[n-1][n-1]).append("\n");
        }

        System.out.print(sb);
    }
}
