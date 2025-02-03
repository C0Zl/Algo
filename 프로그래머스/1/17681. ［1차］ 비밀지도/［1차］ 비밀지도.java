class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            int num = arr1[i] | arr2[i];
            
            for (int j = 0; j < n; j++) {
                sb.insert(0, (num % 2 == 1 ? "#" : " "));
                num /= 2;
            }
            
            answer[i] = sb.toString();
        }        
        
        return answer;
    }
}