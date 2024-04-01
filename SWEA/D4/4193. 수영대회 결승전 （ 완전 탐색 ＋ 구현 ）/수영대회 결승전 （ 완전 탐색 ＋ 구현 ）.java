import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static class player {
		int r, c, time;

		public player(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

	static int T, N, time, er, ec;
	static int[] dr = { -1, 1, 0, 0 }, dc = { 0, 0, -1, 1 };
	static int[][] sea;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			sea = new int[N][N];
			visit = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					sea[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			st = new StringTokenizer(br.readLine());

			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());

			er = Integer.parseInt(st.nextToken());
			ec = Integer.parseInt(st.nextToken());

			sb.append("#").append(tc).append(" ").append(swim(sr, sc) ? time : -1).append("\n");
		}
		System.out.println(sb);
	}

	static boolean swim(int r, int c) {
		Queue<player> que = new LinkedList<>();
		que.offer(new player(r, c, 0));
		visit[r][c] = true;

		while (!que.isEmpty()) {
			player p = que.poll();

			for (int d = 0; d < 4; d++) {
				int nr = p.r + dr[d];
				int nc = p.c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;

				if (nr == er && nc == ec) {
					time = p.time + 1;
					return true;
				}

				if (visit[nr][nc] || sea[nr][nc] == 1)
					continue;

				// 토네이도가 있으면 없어질 때까지 대기
				if (sea[nr][nc] == 2) {
					if (p.time % 3 == 2) {
						visit[nr][nc] = true;
						que.offer(new player(nr, nc, p.time + 1));
					} else {
						visit[p.r][p.c] = true;
						que.offer(new player(p.r, p.c, p.time + 1));
					}
				} else {
					// 방문하는 경우
					visit[nr][nc] = true;
					que.offer(new player(nr, nc, p.time + 1));

				}

			}
		}
		return false;
	}
}
