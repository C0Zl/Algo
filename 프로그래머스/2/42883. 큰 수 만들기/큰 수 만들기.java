import java.util.*;

class Solution {
    public String solution(String number, int k) {
        int n = number.length();
        int numSize = n - k;
        StringBuilder sb = new StringBuilder();
        
        int start = -1;
        
        while (sb.length() != numSize) {
            int max = 0;
            for(int i = start+1; i < n - (numSize - sb.length()) + 1; i++) {
                int current = number.charAt(i) - '0';
                if (max < current) {
                    max = current;
                    start = i;
                }
            }
            sb.append(max);
        }
        
        return sb.toString();
    }
}