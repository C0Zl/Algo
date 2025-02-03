class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        boolean[][] wall = new boolean[n][n];
        String[] answer = new String[n];
        for(int i = 0; i < n; i++) {
            answer[i] = "";
        }

        // 각 10진수 숫자들을 순회
        for (int i = 0; i < n; i++) {
            int num = arr1[i];
            
            // 10진수 숫자들을 2진수로 계산
            for(int j = n - 1; j >= 0; j--) {
                if (num % 2 == 1) {
                    wall[i][j] = true;
                }
                num /= 2;
            }
        }
        
        // 각 10진수 숫자들을 순회
        for (int i = 0; i < n; i++) {
            int num = arr2[i];
            
            // 10진수 숫자들을 2진수로 계산
            for(int j = n - 1; j >= 0; j--) {
                if (num % 2 == 1) {
                    wall[i][j] = true;
                }
                num /= 2;
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(wall[i][j]) answer[i] += "#";
                else answer[i] += " ";
            }
        }
        
        
        return answer;
    }
    
    // public void 
}