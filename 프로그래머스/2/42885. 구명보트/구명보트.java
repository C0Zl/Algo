import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people); // 몸무게 오름차순 정렬
        int light = 0;               // 가장 가벼운 사람
        int heavy = people.length - 1; // 가장 무거운 사람
        int boats = 0;
        
        while (light <= heavy) {
            // 가장 가벼운 사람과 가장 무거운 사람의 합이 limit 이하라면 함께 태움
            if (people[light] + people[heavy] <= limit) {
                light++;
            }
            // 무거운 사람은 무조건 태워야 하므로 보트 사용
            heavy--;
            boats++;
        }
        
        return boats;
    }
}
