import java.util.*;

class Solution {
    int[][] computers;
    boolean[] visited;

    public int solution(int n, int[][] computers) {
        int answer = 0;        
        this.computers = computers;
        this.visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
                answer++;
            }
        }
        
        return answer;
    }
    
    public void dfs(int i) {
        visited[i] = true;
        
        for (int j = 0; j < computers.length; j++) {
            if (i != j && computers[i][j] == 1 && !visited[j]) {
                dfs(j);
            }
        }
        
        return;
    }
}