import java.util.*;

class Solution {
    Set<Integer> numberSet = new HashSet<>();
    boolean[] visited;
    String[] arr;

    public int solution(String numbers) {
        int n = numbers.length();
        visited = new boolean[n];
        arr = numbers.split("");

        // 1자리부터 n자리까지 순열 생성
        for (int r = 1; r <= n; r++) {
            perm("", r);
        }

        int count = 0;
        for (int num : numberSet) {
            if (isPrime(num)) count++;
        }

        return count;
    }

    // 순열 생성
    private void perm(String current, int r) {
        if (current.length() == r) {
            int num = Integer.parseInt(current);
            numberSet.add(num);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                perm(current + arr[i], r);
                visited[i] = false;
            }
        }
    }

    // 소수 판별
    private boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
