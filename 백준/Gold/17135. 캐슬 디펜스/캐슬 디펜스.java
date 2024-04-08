import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map;
	static int[] arr;
	static int N, M, D, maxEnemy;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		arr = new int[3];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				// 1이면 true, 0이면 false
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0, 0);
		System.out.println(maxEnemy);
	}
	
	// 조합만들 메서드 comb
	static void comb(int dep, int s) {
		// 3이 되면 조합 그만 만들기
		if (dep == 3) {
			int a = attack(arr);
			if (a > maxEnemy) {
				maxEnemy = a;
			}
			return;
		}
		
		for (int i = s; i < M; i++) {
			arr[dep] = i;
			comb(dep + 1, i + 1);
		}
	}
	
	// 공격 시작
	static int attack(int[] arr) {
		int enemy = 0;
		int[][] copy = new int[N][M];
		clone(map, copy);
		
		for (int i = N - 1; i >= 0; i--) {
//			System.out.println(Arrays.toString(arr));
			for (int n : arr) {
				int lowDist = Integer.MAX_VALUE, row = -1, col = -1;
				
				for (int r = i - D + 1; r <= i; r++) {
					for (int c = n - D + 1; c <= n + D - 1; c++) {
						// 범위를 벗어나면 pass
						int dist = Math.abs(c - n) + Math.abs(i - r + 1);
						if (dist > D || r < 0 || c < 0 || c >= M) 
							continue;

						// 적이 있는 칸일 때 최소거리이면 저장
						if (copy[r][c] != 0) {
							if (dist < lowDist) {
								lowDist = dist;
								row = r; col = c;
							} else if (dist == lowDist) {
								if (c < col) {
									row = r; col = c;
								}
							}
//							System.out.println(row +  " " + col + " " + dist);
						}
						
					}
				}
				
				// 공격할 궁수가 있었고, 이미 제거 되지 않았으면 count +1
				if (row != -1 && copy[row][col] == 1) {
					// 공격받은 적은 2로 변경
					copy[row][col] = 2;
					enemy++;
				}
				
			}
			for (int r = 0; r < copy.length; r++) {
				for (int c = 0; c < copy[0].length; c++) {
					if (copy[r][c] == 2) copy[r][c] = 0;
				}
			}
//			for (int k = 0; k < N; k++) {
//				System.out.println(Arrays.toString(copy[k]));
//			}
		}
//		System.out.println("###############");
//		System.out.println("적 : " + enemy);
		return enemy;
	}
	
	static void clone(int[][] arr, int[][] copy) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				copy[i][j] = arr[i][j];
			}
		}
	}
}
