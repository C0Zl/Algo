import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        List<Integer> answer = new ArrayList<>();
        int[] answerCnt = {0, 0, 0};
        int[][] way = {{1,2,3,4,5}, 
                       {2, 1, 2, 3, 2, 4, 2, 5}, 
                       {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
        
        for (int i = 0; i < answers.length; i++) {
            // 각 번호가 찍는 방법대로 채점
            for (int j = 0; j < way.length; j++) {
                if (answers[i] == way[j][i % way[j].length]) {
                    answerCnt[j]++;
                }
            }
        }
        
        int maxCnt = 0;
        
        for(int p = 0; p < 3; p++) {
            if (maxCnt < answerCnt[p]) maxCnt = answerCnt[p];
        }
        
        for(int p = 0; p < 3; p++) {
            if (maxCnt == answerCnt[p]) answer.add(p+1);
        }
        
        int[] arr = answer.stream().mapToInt(i -> i).toArray();
        return arr;
    }
}