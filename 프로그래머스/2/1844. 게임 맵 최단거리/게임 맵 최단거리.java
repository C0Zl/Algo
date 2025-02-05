import java.util.*;

class Solution {
    class Position {
        int r;
        int c;
        int dist;
        
        Position(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, -1, 0, 1};
        visited[0][0] = true;
        
        // 갈 수 있는 곳이고, 방문한 적 없는 경우
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(0, 0, 1));

        // queue가 비어있지 않은 경우
        while(!queue.isEmpty()) {
            Position node = queue.poll();
            
            if (node.r == n - 1 && node.c == m - 1) return node.dist;

            // 4방향 탐색하며 방문 가능한 경우 queue에 넣기
            for(int d = 0; d < 4; d++) {
                int nr = node.r + dr[d];
                int nc = node.c + dc[d];

                // 방문 가능한 경우
                if (nr >= 0 && nr < n && nc >= 0 && nc < m && maps[nr][nc] == 1 && !visited[nr][nc]) {
                    queue.offer(new Position(nr, nc, node.dist + 1));
                    visited[nr][nc] = true;
                }
            }
        }
        
        return -1;
    }
}