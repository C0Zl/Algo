import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] strArr = new String[numbers.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(strArr, (a, b) -> (b + a).compareTo(a + b));
        
        if (strArr[0].equals("0")) return "0";
        
        StringBuilder sb = new StringBuilder();
        for(String s : strArr) {
            sb.append(s);
        }
        
        return sb.toString();
    }
}