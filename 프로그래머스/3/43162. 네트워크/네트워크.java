import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            if (!visited[i]) { // 방문하지 않은 네트워크 찾기
                bfs(i, n, computers, visited);
                answer++; // 새로운 네트워크 발견 시 증가
            }
        }
        return answer;
    }

    private void bfs(int start, int n, int[][] computers, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for(int j = 0; j < n; j++) { // 모든 노드 검사
                if (computers[node][j] == 1 && !visited[j]) {
                    queue.add(j);
                    visited[j] = true;
                }
            }
        }
    }
}
