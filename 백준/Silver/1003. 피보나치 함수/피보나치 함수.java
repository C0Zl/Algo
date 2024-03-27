import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		// 배열에 N 입력 받기
		int[] arr = new int[T];
		int max = Integer.MIN_VALUE;
		for (int tc = 0; tc < T; tc++) {
			arr[tc] = Integer.parseInt(br.readLine());
			if (max < arr[tc]) max = arr[tc];
		}
		
		// 피보나치 함수 실행 시 0, 1의 호출 횟수를 dp로 계산
		int[][] fiv = new int[max + 1][2];
		
		// 초기 호출 횟수 입력
		fiv[0][0] = 1;
		if (max > 0) fiv[1][1] = 1;
		
		// fiv dp 채워넣기
		if (max >= 2) {
			for (int i = 2; i < fiv.length; i++) {
				fiv[i][0] = fiv[i-1][0] + fiv[i-2][0];
				fiv[i][1] = fiv[i-1][1] + fiv[i-2][1];
			}
		}
		
		for (int n : arr) {
			sb.append(fiv[n][0]).append(" ").append(fiv[n][1]).append("\n");
		}
		
		System.out.println(sb);
	}
}
