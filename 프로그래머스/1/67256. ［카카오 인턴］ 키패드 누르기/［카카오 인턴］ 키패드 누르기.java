class Solution {
    public String solution(int[] numbers, String hand) {
        int leftNum = 10;
        int rightNum = 12;
        StringBuilder sb = new StringBuilder();
        
        for (int num : numbers) {
            // 1, 4, 7인 경우
            if(num % 3 == 1) {
                sb.append("L");
                leftNum = num;
            } else if (num % 3 == 0 && num != 0) { // 3, 6, 9인 경우
                sb.append("R");
                rightNum = num;
            } else { // 그 외의 경우
                if (num == 0) num = 11;
                int leftDist = Math.abs(leftNum - num) / 3 + Math.abs(leftNum - num) % 3;
                int rightDist = Math.abs(rightNum - num) / 3 + Math.abs(rightNum - num) % 3;
                // 엄지손가락부터의 거리가 같은 경우
                if(leftDist == rightDist) {
                    if (hand.equals("right")) {
                        sb.append("R");
                        rightNum = num;
                    } else {
                        sb.append("L");
                        leftNum = num;
                    }
                } else {
                    if (leftDist < rightDist) {
                        sb.append("L");
                        leftNum = num;
                    } else {
                        sb.append("R");
                        rightNum = num;
                    }
                }
            }
        }
        
        return sb.toString();
    }
    
}