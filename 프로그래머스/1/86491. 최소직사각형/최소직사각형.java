import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int min = Integer.MIN_VALUE;
        int max = Integer.MIN_VALUE;
        
        for (int[] size :sizes) {
            if (size[0] < size[1]) {
                min = Math.max(min, size[0]);
                max = Math.max(max, size[1]);
            } else {
                min = Math.max(min, size[1]);
                max = Math.max(max, size[0]);
            }
        }
        
        return min * max;
    }
}