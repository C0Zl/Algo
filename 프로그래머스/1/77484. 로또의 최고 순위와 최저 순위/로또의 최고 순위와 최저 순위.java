class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        int undef = 0;
        int same = 0;
        
        lottoLoop : for (int i = 0; i < lottos.length; i++) {
            int lotto = lottos[i];
            // 알 수 없는 번호인 경우
            if (lotto == 0) {
                undef++;
                continue;
            }
            
            for(int j = 0; j < win_nums.length; j++)  {
                int num = win_nums[j];
                // 1. lotto 번호와 같은 경우
                if (lotto == num) {
                    same++;
                    continue lottoLoop;
                } else {
                    continue;
                }
            }
        }
        
        answer[0] = (same + undef) == 0 ? 6 : 7 - (same + undef);
        answer[1] = same == 0 ? 6 : 7 - same;
        return answer;
    }
}