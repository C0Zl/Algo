import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int n = citations.length;
        int maxH = 0;
        
        Arrays.sort(citations);
        
        for (int i = 1; i <= n; i++) {
            if (citations[n - i] >= i && maxH < i) {
                maxH = i;
            }
        }
        
        return maxH;
    }
}