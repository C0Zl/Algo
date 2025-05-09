class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        for (int i = 1; i <= yellow; i++) {
            if (yellow % i == 0) {
                int j = yellow / i;
                if (2 * (i + j) + 4 == brown) {
                    answer[0] = i >= j ? i + 2 : j + 2;
                    answer[1] = i >= j ? j + 2 : i + 2;
                    break;
                }
            }
        }
        
        return answer;
    }
}