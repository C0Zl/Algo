class Solution {
    public int solution(String[][] board, int r, int c) {
        int n = board.length;
        int answer = 0;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        for (int d = 0; d <= 3; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            
            if (nr < 0 || nr >= n || nc <0 || nc >= n) continue;
            if (board[nr][nc].equals(board[r][c])) answer++;
        }
        
        return answer;
    }
}