import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[][] initArr, arr, rotArr, tmp;
	static int[] permArr; 
	static boolean visited[];
	static int min = Integer.MAX_VALUE;

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N, M, K 입력 받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 초기 배열 initArr, 회전 연산 후에 저장할 배열 arr
		initArr = new int[N][M];
		arr = new int[N][M];
		tmp = new int[N][M];
		
		// 회전 연산을 담을 배열
		rotArr = new int[K][3];
		visited = new boolean[K];
		permArr = new int[K];
		
		// 초기 배열과 회전 연산 모두 입력 받기
		for (int r = 0; r < initArr.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < initArr[0].length; c++) {
				initArr[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int k = 0; k < rotArr.length; k++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < rotArr[0].length; i++) {
				rotArr[k][i] = Integer.parseInt(st.nextToken());
			}
		}
		
		copy(arr, initArr);
		perm(0);
		
		System.out.println(min);
		
	}
	
	static void copy(int[][] a1, int[][] a2) {
		for (int r = 0; r < a1.length; r++) {
			for (int c = 0; c < a1[0].length; c++) {
				a1[r][c] = a2[r][c];
			}
		}
	}

	static void perm(int d) {
		if (d == K) {
			for (int n : permArr) {
				int rf = rotArr[n][0] - rotArr[n][2] - 1;
				int rl = rotArr[n][0] + rotArr[n][2] - 1;
				int cf = rotArr[n][1] - rotArr[n][2] - 1;
				int cl = rotArr[n][1] + rotArr[n][2] - 1;
				
				copy(tmp, arr);
				rotate(rf, rl, cf, cl);
			}
			calMinRow(arr);
			copy(arr, initArr);
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if (!visited[i]) {
				visited[i] = true;
				permArr[d] = i;
				perm(d + 1);
				visited[i] = false;
			}
		}
	}
	
	static void rotate(int rf, int rl, int cf, int cl) {
		if (rl - rf <= 1) {
			return;
		}
		
		for (int i = cf + 1; i <= cl; i++) {
			arr[rf][i] = tmp[rf][i - 1];
		}
		for (int i = rf + 1; i <= rl; i++) {
			arr[i][cl] = tmp[i - 1][cl];
		}
		for (int i = cf; i < cl; i++) {
			arr[rl][i] = tmp[rl][i + 1];
		}
		for (int i = rf; i < rl; i++) {
			arr[i][cf] = tmp[i + 1][cf];
		}
		
		rotate(rf + 1, rl - 1, cf + 1, cl - 1);
	}
	
	static void calMinRow(int[][] arr) {
		for (int r = 0; r < arr.length; r++) {
			int tmp = 0;
			for (int c = 0; c < arr[0].length; c++) {
				tmp += arr[r][c];
			}
			if (min > tmp) {
				min = tmp;
			}
		}
	}
}
