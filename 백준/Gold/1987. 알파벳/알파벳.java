import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static char[][] arr;
	static int N, M, max = 1;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
	static List<Character> list = new ArrayList<>(); // 문자를 담을 list

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] NM = br.readLine().split(" ");
		N = Integer.parseInt(NM[0]);
		M = Integer.parseInt(NM[1]);

		// 배열 크기 설정
		arr = new char[N][M];

		for (int i = 0; i < N; i++) {
			char[] charArr = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				arr[i][j] = charArr[j];
			}
		}
		
		list.add(arr[0][0]);
		dfs(0, 0, 1);
		System.out.println(max);
	}

	static void dfs(int r, int c, int n) {
		// 최대 칸 수보다 n이 커진다면 갱신하기
		if (n > max) {
			max = n;
		}
		
		
		for (int d = 0; d < dr.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 배열 범위를 벗어나거나 이미 해당 문자가 list에 있다면 continue
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || list.contains(arr[nr][nc])) continue;
			
			// list에 없는 문자라면 추가해주기
			list.add(arr[nr][nc]);
//			System.out.println(arr[nr][nc]);
			dfs(nr, nc, n + 1);
			
			// 원상 복귀
			list.remove(Character.valueOf(arr[nr][nc]));
		}
		
	}
}
