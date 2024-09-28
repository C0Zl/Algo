import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        // 총 친구들의 수 n
        int n = friends.length;
        // 총 선물 받은 결과 리스트 result
        int[] result = new int [n];
        
        // 친구들의 index를 담을 map
        Map<String, Integer> friendMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            // 이름, index로 map에 추가 
            friendMap.put(friends[i], i);  
        }
        
        // 선물 지수 리스트
        int[] count = new int[n];
        // 선물 주고받은 내용 담을 map
        int[][] map = new int[n][n];
        
        // gift를 돌며 선물 표와 지수 count
        for (String gift: gifts) {
            // 선물 준 사람 이름 gName, index gIndex
            String gName = gift.split(" ")[0];
            int gIndex = friendMap.get(gName);
            
            // 선물 받은 사람 이름 rName, index rIndex
            String rName = gift.split(" ")[1];
            int rIndex = friendMap.get(rName);
            
            // 선물 지수 count
            count[gIndex]++;
            count[rIndex]--;
            
            // 선물 주고 받은 표 count
            map[gIndex][rIndex]++;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 자기 자신에게 선물 줄 수 없음
                if (i >= j) continue;
                
                // 1. 선물 기록이 없거나 선물 개수가 같은 경우
                if (map[i][j] == map[j][i]) {
                    // 선물 지수가 같은 경우
                    if (count[i] == count[j]) continue;
                    
                    // 선물 지수가 다른 경우
                    if (count[i] > count[j])
                        result[i]++;
                    else 
                        result[j]++;
                    
                    continue;
                }
                
                // 2. 선물 기록이 있고 개수가 다른 경우
                if (map[i][j] > map[j][i])
                    result[i]++;
                else 
                    result[j]++;
            }
        }
        
        // 가장 선물 많은 받을 친구 찾고 선물 수 return
        int maxCount = 0;
        for (int k : result) {
            // 선물 더 많이 받을 친구면 갱신 
            maxCount = maxCount < k ? k : maxCount;
        }
        
        return maxCount;
    }
}