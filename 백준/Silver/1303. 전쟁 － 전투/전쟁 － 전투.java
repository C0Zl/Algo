import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
		
	// 방문 처리해줄 boolean 배열과 어떤 병사인지 담을 char 배열 생성
	static boolean[][] visited;
	static char[][] soldier;
	
	// n, m 그리고 병사 수를 세어 줄 cnt 변수, 아군과 적군의 변수를 담을 변수 생성
	static int N, M, cnt, ourCnt, enemyCnt;
	
	// 델타 배열 생성
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// split을 이용해 N, M 입력 받기
		String[] str = br.readLine().split(" ");
		N = Integer.parseInt(str[1]);
		M = Integer.parseInt(str[0]);
		
		visited = new boolean[N][M];
		soldier = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			// 띄어쓰기가 없기 때문에 문자열로 받고 charAt
			String s = br.readLine();
			
			for (int j = 0; j < M; j++) {
				soldier[i][j] = s.charAt(j);
			}
		}
		
		for (int i = 0; i < soldier.length; i++) {
			for (int j = 0; j < soldier[0].length; j++) {
				// 이미 방문했으면 건너 뛰기
				if(visited[i][j]) continue;
				
				// 방문 안했으면 cnt 초기화 해주고 powerCnt
				cnt = 0;
				powerCnt(i, j, soldier[i][j]);
				
				// 아군인지 적군인지 식별해 알맞게 더해줌
				if (soldier[i][j] == 'W') {ourCnt += cnt*cnt;}
				else {enemyCnt += cnt*cnt;}
			}
		}
		
		System.out.println(ourCnt + " " + enemyCnt);
	}
	
	static void powerCnt(int i, int j, char c) {
		// 방문 처리 및 cnt 해주기
		visited[i][j] = true;
		cnt++;
		
		for (int d = 0; d < 4; d++) {
			int nr = i + dr[d];
			int nc = j + dc[d];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= M) {
				continue;
			}
			
			if (soldier[nr][nc] == c && !visited[nr][nc]) {
				powerCnt(nr, nc, c);
			}
		}
	}
	
}
