import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// testCase 갯수 입력 받고, N과 M 선언
		int T = Integer.parseInt(br.readLine());
		int N, M;
		
		// 최댓값 찾기 위한 배열 arr 만들기 (인덱스 : 합, 값 : 합이 나온 개수)
		int[] arr;
		String[] str;
		
		// 테스트케이스만큼 반복
		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase);
			str = br.readLine().split(" ");
			
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			
			arr = new int[N + M];
			
			int max = 0;
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					arr[i + j - 1]++;
				}
			}
			
			// 최댓값 찾기
			for (int n : arr) {
				max = (n > max) ? n : max;
			}
			
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == max) {
					sb.append(" " + (i + 1));
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
